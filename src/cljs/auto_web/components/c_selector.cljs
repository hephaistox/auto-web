(ns auto-web.components.c-selector
  "A stateful selector displaying numbered elements.

  The reframe database stores in the `::selected-idx`"
  (:require
   [auto-web.components.v-selector :as wvselector]
   [re-frame.core                  :as rf]))

(rf/reg-sub ::selected-idx (fn [db _] (:selected-idx db)))

(rf/reg-event-db
 ::change-selected-idx
 (fn [db [_ sel-kw change-mode val n]]
   (case change-mode
     :abs (assoc-in db [:selected-idx sel-kw] val)
     :rel (update-in db [:selected-idx sel-kw] (partial wvselector/relative-jump n val)))))

(defn selected
  "Returns the selected element for selector called `sel-kw`"
  [sel-kw]
  (get @(rf/subscribe [::selected-idx]) sel-kw 0))

(defn opts-go-to
  [sel-kw n page]
  {:on-click #(rf/dispatch [::change-selected-idx sel-kw :abs page (dec n)])})

(defn opts-go-rel
  [sel-kw n page]
  {:on-click #(rf/dispatch [::change-selected-idx sel-kw :rel page (dec n)])})

(defn c-selector
  [sel-kw n]
  [wvselector/v-selector
   n
   (selected sel-kw)
   (fn [page] (opts-go-to sel-kw n page))
   (fn [page] (opts-go-rel sel-kw n page))])
