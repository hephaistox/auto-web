(ns auto-web.portfolio.size-cards
  (:require
   [auto-web.components.sizes :refer [predefined-size]]
   [portfolio.react-18        :refer-macros [defscene]]
   [reagent.core              :as reagent]))

(defn- show-size
  [s]
  [:div.w3-blue.w3-display-container {:style {:width (predefined-size s)
                                              :height (predefined-size s)}}
   [:div.w3-display-middle.w3-text-black.w3-bold s]])

(defscene text-of-all-sizes
          "# Badge with customizable appearance and tooltip message"
          (reagent/as-element [:div.w3-flex {:style {:gap "8px"
                                                     :flex-wrap "wrap"}}
                               (show-size :icon)
                               (show-size :tiny)
                               (show-size :xsmall)
                               (show-size :small)
                               (show-size :default)
                               (show-size :medium)
                               (show-size :big)]))
