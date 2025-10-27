(ns auto-web.components.sizes-cards
  (:require
   [auto-web.components.sizes :refer [predefined-size]]
   [devcards.core             :refer [defcard]]
   [reagent.core              :as reagent]))

(defn- show-size
  [s]
  [:div.w3-blue.w3-display-container {:style {:width (predefined-size s)
                                              :height (predefined-size s)}}
   [:div.w3-display-middle.w3-text-black.w3-bold s]])

(defcard text-of-all-sizes
         "## Badge with customizable appearance and tooltip message"
         (reagent/as-element [:div.w3-flex {:style {:gap "8px"
                                                    :flex-wrap "wrap"}}
                              (show-size :icon)
                              (show-size :tiny)
                              (show-size :xsmall)
                              (show-size :small)
                              (show-size :default)
                              (show-size :medium)
                              (show-size :big)]))
