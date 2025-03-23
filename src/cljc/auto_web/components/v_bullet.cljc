(ns auto-web.components.v-bullet
  "Shows a bullet list.

  Each item is made of:

   * `fa-icon` is a font awesome icon
   * `react-key` is used to add a react key
   * `link` address to point (applied to the rouding box)
   * `label` the name to add on the right of the icon
   * `desc` the description to continue the label explanation"
  (:require
   [auto-core.uuid             :refer [unguessable]]
   [auto-web.components.v-link :as wlink]))

(defn v-bullet
  "Simple bullet list of `items`, that should defin `fa-icon`, `react-key`, `link`, `label`, and `desc`"
  [items & opts]
  (reduce (fn [hiccup
               {:keys [fa-icon react-key link label desc]
                :or {fa-icon "fa-circle"
                     react-key (unguessable)}}]
            (conj hiccup
                  ^{:key react-key}
                  [:li.text
                   (second opts)
                   [:i.fa.w3-margin-right {:class fa-icon}]
                   [:b.text.w3-margin-right (wlink/v-a link (str label ":"))]
                   desc]))
          [:ul.w3-ul (first opts)]
          (vals items)))
