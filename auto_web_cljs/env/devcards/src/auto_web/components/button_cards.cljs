(ns auto-web.components.button-cards
  (:require
   [auto-web.components.button :refer [cbutton clink-button]]
   [devcards.core              :refer [defcard]]
   [reagent.core               :as reagent]))

(defcard button
         "### A simple noop button, no on-click defined"
         (fn [data-atom _]
           (reagent/as-element [:div.w3-cell
                                [cbutton @data-atom "Testing" {:url "https://hephaistox.fr"}]]))
         {}
         {:inspect-data true})

(defcard
 button-with-options
 "### A simple button showing an alert when clicked

Note that:
- The orange background is added
- So is the the on-click behavior"
 (fn [data-atom _owner]
   (reagent/as-element [:div.w3-cell
                        [cbutton
                         @data-atom
                         "testing"
                         {:url "https://hephaistox.fr"
                          :link-id :foo}]]))
 {:on-click #(js/alert "Simple button alert")
  :class "w3-orange"}
 {:inspect-data true})

(defcard link-button
         "### A simple link-button, on-click is defined by the link"
         (fn [data-atom _owner]
           (reagent/as-element [:div.w3-cell [clink-button "Testing" @data-atom]]))
         {:url "https://hephaistox.fr"
          :link-id "bar"}
         {:inspect-data true})

(defcard link-button-with-options
         "### A simple button showing an alert when clicked"
         (fn [data-atom _owner]
           (let [{:keys [opts link]} @data-atom]
             (reagent/as-element [:div.w3-cell [clink-button opts "testing" link]])))
         {:link {}
          :opts {:on-click #(js/alert "Simple button alert")
                 :class "w3-orange"}}
         {:inspect-data true})
