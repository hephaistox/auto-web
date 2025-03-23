(ns auto-web.lang "Multi language.")

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
