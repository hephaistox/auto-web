(ns auto-web.components.v-images
  "Show image cells than are mobile compliant."
  (:require
   [auto-web.components.v-labelled-image :as vclabelled-image]))

(defn image-cells
  [items tr w]
  (reduce (fn [hiccup {:keys [img-link name href]}]
            (conj hiccup
                  [:div.w3-cell.w3-mobile
                   [vclabelled-image/labelled-image img-link href (tr name) w]]))
          [:div.w3-cell-row]
          (vals items)))
