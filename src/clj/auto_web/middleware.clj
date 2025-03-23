(ns auto-web.middleware
  "Webserver middlewares"
  (:require
   [auto-core.log              :as core-log]
   [auto-web.http-request.lang :refer [default-be-strategy]]
   [auto-web.lang              :refer [possible-langs]]
   [ring.util.response         :refer [set-cookie]]))

(defn wrap-exception-handling
  "Handles exceptions that were thrown along the line, handles both sync and async requests.

  If the exception happen, `body-fn` is called with `http-request` and `e`."
  [handler body-fn]
  (fn
    ([http-request]
     (try (handler http-request)
          (catch Exception e
            (core-log/error-exception e "Fail in sync middleware.")
            (body-fn http-request e))
          (catch Error e
            (core-log/fatal-exception e "Fail in sync middleware.")
            (body-fn http-request e))))
    ([http-request respond raise]
     (try (handler http-request respond raise)
          (catch Exception e
            (core-log/error-exception e "Fail in async middleware.")
            (respond (body-fn http-request e)))
          (catch Error e
            (core-log/fatal e "Fail in async middleware.")
            (respond (body-fn http-request e)))))))

(defn wrap-add-language
  "In the request, `:lang` is assigned with the `be-strategy`
  In the response, the cookie is updated to locally store that language in the customer side.

  See [[auto-web.http-request.lang/default-be-strategy]] to understand the default strategy applied."
  ([handler default-language] (wrap-add-language handler default-language default-be-strategy))
  ([handler default-language be-strategy]
   (fn [http-request]
     (let [l (or (be-strategy http-request possible-langs) default-language)]
       (-> http-request
           (assoc :lang l)
           handler
           (set-cookie :lang (str l) {:path "/"}))))))
