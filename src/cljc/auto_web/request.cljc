(ns auto-web.request
  "Common features on an http request"
  (:require
   [clojure.string :as str]))

(defn accept-languages-str
  "Returns the accepted language described in a request: e.g: `\"en-US,en;q=0.9\"`"
  [request]
  (get-in request [:headers "accept-language"]))

(defn str-to-languages
  "Returns a collection of language maps:
  ```
  [{:weight 100
    :base-lang \"en\"
    :subtag \"US\"}
   {:weight 0.9
    :base-lang \"en\"
    :subtag nil}]```"
  [query-string]
  (when (string? query-string)
    (->> (str/split query-string #",")
         (map (fn [x]
                (let [[lang params] (str/split x #";")
                      [_q v] (when-not (str/blank? params) (str/split params #"="))
                      [base-lang subtag] (when-not (str/blank? lang) (str/split lang #"-"))
                      weight (if (str/blank? v)
                               100
                               #?(:clj (Double/parseDouble v)
                                  :cljs (parse-double v)))]
                  {:weight weight
                   :base-lang base-lang
                   :subtag subtag}))))))

(defn match-lang
  "Returns the language matching one possible-lang among `query-langs`"
  [query-langs possible-langs]
  (->> query-langs
       (filter (fn [{:keys [base-lang]}] (contains? possible-langs base-lang)))
       first
       :base-lang))

(defn match-lang-request
  "Returns the language matching one possible-lang among `query-langs`"
  [request possible-langs]
  (-> request
      accept-languages-str
      str-to-languages
      (match-lang possible-langs)))
