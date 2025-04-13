(ns auto-web.components.badge-cards
  (:require
   [auto-web.components.badge :refer [cbadge cinvalid copyright-str cspinner cunknown cvalid]]
   [devcards.core             :refer [defcard]]
   [reagent.core              :as reagent]))

(defcard badge
         "## Badge with custom bullets and tooltip message"
         (reagent/as-element
          [:ul
           [:li "Exclamation " [cbadge "fa-exclamation" "fa-question" true "Tooltip message"]]
           [:li "Question " [cbadge "fa-exclamation" "fa-question" false "Tooltip message"]]]))


(defcard
 status-badges
 "## Predefined status badges"
 (reagent/as-element
  [:div.w3-row
   [:ul [:li "Unknown " [cunknown]] [:li "Valid: " [cvalid]] [:li "Invalid: " [cinvalid]]]]))

(defcard copyright "## Copyright" (reagent/as-element [copyright-str]))

(defcard spinner "## Spinner to symbolize a waiting time" (reagent/as-element [cspinner]))
