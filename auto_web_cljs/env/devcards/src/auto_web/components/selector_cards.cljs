(ns auto-web.components.selector-cards
  (:require
   [auto-web.components.selector :refer [cselector]]
   [devcards.core                :refer [defcard]]
   [reagent.core                 :as reagent]))

(def page-selected
  (atom {:selected 1
         :n 10}))

(defcard page-selected "### Page selected" page-selected)

(defcard
 selector
 "### A selector, opt is tested with the w3-orange class

Note that
- Currently selected element is disactivated.
- Next and before buttons are disactivated respectively when on the first or last item."
 (fn [data-atom _owner]
   (let [{:keys [selected n]} @data-atom]
     (reagent/as-element
      [:div.w3-cell
       [cselector {:class "w3-yellow"}
        {:n n
         :selected selected}
        (fn [target-page] {:on-click #(swap! page-selected assoc :selected target-page)})
        (fn [rel-page]
          {:on-click (fn [_]
                       (swap! page-selected update :selected (fn [page] (+ page rel-page))))})]])))
 page-selected
 {:inspect-data true})

(defcard
 selector
 "### A selector, opt is tested with the w3-orange class

Note that
- Currently selected element is disactivated.
- Next and before buttons are disactivated respectively when on the first or last item."
 (fn [data-atom _owner]
   (let [{:keys [selected n]} @data-atom]
     (reagent/as-element
      [:div.w3-cell
       [cselector {:class "w3-yellow"}
        {:n n
         :max-before 3
         :max-after 2
         :selected selected}
        (fn [target-page] {:on-click #(swap! page-selected assoc :selected target-page)})
        (fn [rel-page]
          {:on-click (fn [_]
                       (swap! page-selected update :selected (fn [page] (+ page rel-page))))})]])))
 page-selected
 {:inspect-data true})
