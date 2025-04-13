(ns auto-web.components.selector
  "A selector show an unnamed list of items, they are presented with their numbers.

  If their number exceed"
  (:require
   [auto-web.page.builder :refer [merge-opts]]))

(defn- clamp
  "Clamp `v ` between `min` and `max`"
  [v min max]
  (cond
    (< v min) min
    (> v max) max
    :else v))

(defn cselector
  "A selector showing all the `n` items, all clickable to jump to that position.

  That buttons are rounded with previous and next buttons.

  * `pars` is a map with:
   ** `n` is the number of items
   ** `selected` is the number of the item to show (default to 1)
   ** `max-before` minimum number of element to show (default to 10)
   ** `max-after` maximum number of element to show (default to 10)
  * `opts-go-to` a function transforming an item number to an option map added on button going direclty to a number.
  * `opts-rel` a function transforming a number of position to an option map added on button going eate a tag relatively moving"
  [opts pars opts-go-to opts-rel]
  (let [{:keys [n selected max-before max-after]
         :or {max-before 10
              max-after 10}}
        pars
        selected (clamp selected 0 n)
        start-n (max 1 (- selected max-before))
        end-n (min n (+ selected max-after))]
    (vec
     (concat [:div
              opts
              [:i.w3-button.fa.fa-angle-left
               (cond-> {}
                 (= 1 selected) (merge-opts {:class "w3-disabled w3-hover-none"
                                             :style {:cursor "default"}})
                 (not= 1 selected) (merge-opts (opts-rel -1)))]
              (when (< 1 (- selected max-before)) [:i.w3-tiny "..."])]
             (reduce (fn [hiccup-item page]
                       (conj hiccup-item
                             ^{:key page}
                             [:span.w3-button.w3-small
                              (cond-> (opts-go-to page)
                                (= page selected) (merge-opts {:class "w3-disabled w3-hover-none"
                                                               :style {:cursor :default}}))
                              page]))
                     []
                     (range start-n (inc end-n)))
             [(when (not= end-n n) [:i.w3-tiny "..."])
              [:i.w3-button.fa.fa-angle-right
               (cond-> {}
                 (not= n selected) (merge-opts (opts-rel 1))
                 (= n selected) (merge-opts {:class "w3-disabled w3-hover-none"
                                             :style {:cursor "default"}}))]]))))
