(ns auto-web.components.v-list
  "Component to list values."
  (:require
   [auto-web.components.v-img  :refer [v-icon-img]]
   [auto-web.components.v-link :as wlink]))

(defn v-one-per-row
  "List of elements to display, one per row.

  Each `item` is a map with:
    * `fa-icon` is a font awesome icon
    * `label` the name to add on the right of the icon
    * `link` address to point (applied to the rounding box)"
  [items]
  (reduce (fn [hiccup item]
            (let [{:keys [fa-icon label link]
                   :or {fa-icon "fa-circle"}}
                  item]
              (if (seq item)
                (conj hiccup
                      (wlink/v-a link
                                 [:div.w3-block.w3-left-align {:class
                                                               [(if link
                                                                  "w3-button w3-hover-opacity"
                                                                  "w3-padding w3-section-small")]}
                                  [:i.fa.w3-margin-right {:class fa-icon}]
                                  label]
                                 {:class "w3-hover-opacity"}))
                hiccup)))
          [:div.w3-container]
          items))

(defn v-small-icon
  "Display an icon, `item` is a map with:
    * `fa-icon` is a font awesome icon
    * `label` the name to add on the right of the icon
    * `link` address to point (applied to the rounding box)"
  [{:keys [link fa-icon label]
    :or {fa-icon "fa-circle"}}
   &
   opts]
  [:span.w3-hover-opacity.w3-tooltip
   (first opts)
   (wlink/v-a link
              [:i.fa {:class fa-icon
                      :style {:height "1em"
                              :width "1em"}}])
   (when label
     [:div.w3-text.w3-tag {:style {:position "absolute"
                                   :left "0"
                                   :bottom "2em"}}
      label])])

(defn v-small-icons
  "List `items` to display as icons all on the same row.

  See [[auto-web.components.v-list/v-small-icon]] for item details."
  [items & opts]
  (reduce (fn [hiccup item]
            (if (map? item)
              (conj hiccup
                    (v-small-icon item (update (second opts) :class #(str % " w3-bar-item"))))
              hiccup))
          [:div.w3-bar (merge (first opts) {:style {:overflow "visible !important"}})]
          items))

(defn v-small-imgs
  "List `items` to display as small buttons all on the same row.

  Each `item` is a map with:
    * `img`
    * `label` the name to add on the right of the icon
    * `link` address to point (applied to the rounding box)"
  [items]
  (reduce (fn [hiccup item]
            (let [{:keys [img link label]} item]
              (conj hiccup
                    (when (seq item)
                      [:div.w3-tooltip.w3-button.w3-hover-opacity {:style {:overflow "visible"}}
                       (wlink/v-a link (v-icon-img img))
                       (when label
                         [:div.w3-text.w3-tag {:style {:bottom "-1em"
                                                       :left "-1em"
                                                       :position "absolute"}}
                          label])]))))
          [:div.w3-container.w3-content]
          items))
