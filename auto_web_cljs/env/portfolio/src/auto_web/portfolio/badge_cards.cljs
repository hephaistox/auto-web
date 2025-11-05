(ns auto-web.portfolio.badge-cards
  (:require
   [auto-web.components.badge :refer [cbadge cinvalid copyright-str cspinner cunknown cvalid]]
   [portfolio.react-18        :refer-macros [defscene]]
   [reagent.core              :as reagent]))

(defscene badge
          "# Badge with custom bullets and tooltip message"
          (reagent/as-element
           [:ul
            [:li "Exclamation " [cbadge "fa-exclamation" "fa-question" true "Tooltip message"]]
            [:li "Question " [cbadge "fa-exclamation" "fa-question" false "Tooltip message"]]]))

(defscene
 status-badges
 "## Predefined status badges"
 (reagent/as-element
  [:div.w3-row
   [:ul [:li "Unknown " [cunknown]] [:li "Valid: " [cvalid]] [:li "Invalid: " [cinvalid]]]]))

(defscene copyright "## Copyright badge" (reagent/as-element [copyright-str]))

(defscene spinner "## Spinner to symbolize a waiting time" (reagent/as-element [cspinner]))

