(ns auto-web.components.v-menu
  (:require
   [auto-web.components.v-link :as wlink]))

(defn horizontal-text-menu
  "A menu made of text elements only"
  [links & opts]
  (vec (concat [:div.w3-bar (first opts)]
               (->> links
                    vals
                    (mapv (fn [link]
                            (wlink/v-a link
                                       (:text link)
                                       (update (second opts)
                                               :class
                                               #(str "w3-bar-item w3-button w3-mobile" %)))))))))
