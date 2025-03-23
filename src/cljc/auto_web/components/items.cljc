(ns auto-web.components.items "Various helpers to feed components.")

(defn apply-dic
  "Use `dic` as a dictionnary to translate all values matching keys `kws` in submaps of `items`.

  `dic` is a function that expects a `keyword` parameter, and return the translated value. If the parameter is not a keyword, it is left unmodified."
  [items kws dic]
  (->> kws
       (reduce (fn [items kw]
                 (update-vals items
                              (fn [item]
                                (let [kw-val (get item kw)]
                                  (cond-> item
                                    (keyword? kw-val)
                                    (assoc kw
                                           (if (or (map? dic) (fn? dic)) (dic kw-val) kw-val)))))))
               items)))

(defn translate
  "Returns a map of each `lang` associated to the `items` translated with `tr`"
  [items kws langs tr]
  (->> langs
       (mapv (fn [lang] [lang (apply-dic items kws (partial tr lang))]))
       flatten
       (apply array-map)))
