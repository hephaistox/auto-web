(ns auto-web.components.v-table "Display elements of the table")

(defn simple
  "Simple stripped and bordered table for elements in `name` `cells`."
  [items]
  [:table.w3-table.w3-bordered
   (->> items
        (reduce (fn [hiccup-item [_ {:keys [label cells]}]]
                  (conj hiccup-item
                        [:tr [:td (when label [:p.text label])] [:td [:p.text (first cells)]]]))
                [:tbody]))])
