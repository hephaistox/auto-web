(ns auto-web.components.lang-cards
  (:require
   [auto-web.components.lang :refer [clang-bar]]
   [devcards.core            :refer [defcard]]
   [reagent.core             :as reagent]))

(defcard lang-bar-test
         "### A lang bar"
         (fn [data-atom _owner]
           (let [{:keys [lang opts]} @data-atom]
             (reagent/as-element
              [:div.w3-cell [clang-bar opts lang [:fr :pl :en] #(swap! data-atom assoc :lang %)]])))
         {:opts {:class "w3-orange"}
          :lang :fr}
         {:inspect-data true})
