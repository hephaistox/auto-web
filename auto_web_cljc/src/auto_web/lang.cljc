(ns auto-web.lang
  "Language."
  (:require
   [clojure.string :as str]))

(def languages
  {:en {:label {:fr "Anglais"
                :en "English"}
        :accept-lang "en"}
   :fr {:label {:fr "Français"
                :en "French"}
        :accept-lang "fr"}})

(def possible-langs (set (keys languages)))

(defn tr
  "Translate the key `k` based on `dic` entries and langauge `l`.

  The `default` is used if not found, otherwise nil is returned."
  ([dic l k] (tr dic l k nil))
  ([dic l k default] (get-in dic [k l] default)))

(defn validate
  "Check if all elements have both all languages"
  [dic langs]
  (let [langs (set langs)]
    (->> dic
         (filter (fn [[_k dic-entry]] (not= langs (set (keys dic-entry)))))
         (into {}))))

(defn into-languages
  "A query string like `en-US,en;q=0.9` returns a collection of language maps:
  ```
  [{:weight 100
    :base-lang \"en\"
    :subtag \"US\"}
   {:weight 0.9
    :base-lang \"en\"
    :subtag nil}]
  ```"
  [accept-language-str]
  (when (string? accept-language-str)
    (->> (str/split accept-language-str #",")
         (map (fn [language-definition-str]
                (let [[lang params] (str/split language-definition-str #";")
                      [_q value] (when-not (str/blank? params) (str/split params #"="))
                      [base-lang subtag] (when-not (str/blank? lang) (str/split lang #"-"))
                      base-lang (keyword base-lang)
                      weight (if (str/blank? value)
                               100
                               #?(:clj (Double/parseDouble value)
                                  :cljs (parse-double value)))]
                  {:weight weight
                   :base-lang base-lang
                   :subtag subtag}))))))

(defn accepted-lang
  "The higher weight language from the `query-langs` matchin one of the `possible-langs`"
  [query-langs possible-langs]
  (->> query-langs
       (filter (fn [{:keys [base-lang]}] (contains? possible-langs base-lang)))
       first
       :base-lang))
