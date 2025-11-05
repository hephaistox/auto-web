(ns auto-web.portfolio.selector-cards
  (:require
   [auto-web.components.selector :refer [cselector]]
   [portfolio.react-18           :refer-macros [defscene]]
   [reagent.core                 :as reagent]))

(def page-selected
  (atom {:selected 1
         :n 10}))

(defscene page-selected-card "# Page selected" (reagent/as-element [:p (pr-str @page-selected)]))

(defscene
 selector
 "# A selector, opt is tested with the w3-orange class

Note that
- Currently selected element is disactivated.
- Next and before buttons are disactivated respectively when on the first or last item."
 :params
 page-selected
 [page-selected]
 (let [{:keys [n selected]} @page-selected]
   (reagent/as-element
    [:div.w3-cell
     [cselector {:class "w3-orange"}
      {:n n
       :selected selected}
      (fn [target-page] {:on-click #(swap! page-selected assoc :selected target-page)})
      (fn [rel-page]
        {:on-click (fn [_] (swap! page-selected update :selected (fn [page] (+ page rel-page))))})]
     [:p "Page " (pr-str selected) "/" n]])))


