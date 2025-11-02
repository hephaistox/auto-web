(ns auto-web.middleware.rate-limit
  "Rate limit middleware to prevent attacks."
  (:import [java.time Instant Duration]
           [java.util.concurrent Executors TimeUnit])
  (:require
   [auto-core.uuid     :refer [unguessable]]
   [clojure.string     :as str]
   [ring.util.response :as response]))

(defn make-rate-limiter
  "Returns a newly created limiter, a map with:
  - `middleware-fn` function wrapping the handler to add this behavior, add it early in the chain so unncessary computation.
  - `executor` the single thread scheduled excecutor that is used to track limiter and update it.
  - `id` an id generated for each instance.
  - `stop` function to call with parameter `rate-limiter` to stop. 

   Options are `:limit`, `:window-ms`"
  [{:keys [limit window-ms cleanup-interval-ms name]
    :as pars}]
  (let [state (atom {})
        executor (Executors/newSingleThreadScheduledExecutor)
        id (unguessable)
        out *out*
        err *err*]
    ;; Async cleanup thread
    (.scheduleAtFixedRate
     executor
     (fn []
       (binding [*out* out
                 *err* err]
         (println (format "Rate limiter cleanup thread: `%s-%s` (%d IPs): %s"
                          name
                          id
                          (count @state)
                          (str/join ", " (keys @state))))
         (let [now (Instant/now)]
           (swap! state (fn [m]
                          (->> m
                               (remove (fn [[_ {:keys [start]}]]
                                         (> (.toMillis (Duration/between start now)) window-ms)))
                               (into {}))))
           (flush))))
     cleanup-interval-ms
     cleanup-interval-ms
     TimeUnit/MILLISECONDS)
    (println (format "`%s-%s` is a new rate limiter. (%d queries in %d ms, cleanup every %d)"
                     name
                     id
                     limit
                     window-ms
                     cleanup-interval-ms))
    (flush)
    {:middleware-fn
     (fn [handler]
       (fn [req]
         (let [{:keys [remote-addr]} req
               ip remote-addr
               now (Instant/now)
               {:keys [count start]
                :or {count 0
                     start now}}
               (get @state ip)
               elapsed (.toMillis (Duration/between start now))
               header (format "Middleware `%s-%s`, request (ip %s) at time %s" name id ip now)]
           (if (< elapsed window-ms)
             (if (< count limit)
               (do (println (format "%s, request accepted in window [%s, +%d], [%d < %d]"
                                    header
                                    start
                                    window-ms
                                    count
                                    limit))
                   (swap! state update
                     ip
                     #(-> %
                          (update :count (fnil inc 0))
                          (assoc :start start)))
                   (handler req))
               (do (println (format "%s, request refused in window [%s, +%d], [%d >= %d]"
                                    header
                                    start
                                    window-ms
                                    count
                                    limit))
                   (-> (response/response "Too many requests")
                       (response/status 429))))
             (do (println (format "%s, is beyond window [%s, +%d]" header start window-ms))
                 (swap! state assoc
                   ip
                   {:count 1
                    :start now})
                 (handler req))))))
     :executor executor
     :state state
     :pars (assoc pars :id id)
     :stop (fn [{:keys [executor id]
                 :as _rate-limiter}]
             (println (format "Stopping rate limit middleware `%s-%s`:" name id))
             (flush)
             (.shutdownNow executor))
     :id id}))

(defn stop-rate-limit [rate-limiter] (let [{:keys [stop]} rate-limiter] (stop rate-limiter)))

(comment
  (def limiter
    (make-rate-limiter {:limit 2
                        :window-ms 10000}))
  (defn handler-test [_] {:response :hey})
  ((-> handler-test
       limiter)
   {:remote-addr "192.168.1.1"})
  ;;
  ;
)


(defn rate-limiter-desc
  [rate-limiter]
  (let [{:keys [limit id window-ms name cleanup-interval-ms]} (:pars rate-limiter)]
    (format "Endpoint `%s-%s`authorizes %d queries every %d. Cleaned every `%d` ms.\n%s"
            name
            id
            limit
            window-ms
            cleanup-interval-ms
            (pr-str (:pars rate-limiter)))))

(comment
  (-> {:limit 3
       :window-ms 7000
       :name "test"
       :cleanup-interval-ms 5000}
      make-rate-limiter
      rate-limiter-desc)
  ;;
)
