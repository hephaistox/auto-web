(ns auto-web.components.v-selector "Selector of items")

(defn- clamp
  "Clamp `v ` between `min` and `max`"
  [v min max]
  (cond
    (< v min) min
    (> v max) max
    :else v))

(defn relative-jump
  "Current element `v` is move to `val` page forward (if positive, backward if negative).

  The resulting value is clamped between `0` and `n`"
  [n val v]
  (-> v
      (+ val)
      (clamp 0 n)))

(defn v-selector
  "A selector showing all the `n` items, all clickable to jump to that position.

  That buttons are rounded with previous and next buttons.

  * `n` is the number of items
  * `selected` is the number of the item to show
  * `opts-go-to` returns opts to the create a tag moving to an absolute position
  * `opts-go-rel` returns opts to create a tag relatively moving"
  [n selected opts-go-to opts-go-rel]
  (let [selected (clamp selected 0 n)]
    (conj (->> (range 1 (inc n))
               (reduce (fn [hiccup-item page]
                         (conj hiccup-item
                               ^{:key page}
                               [:span.w3-button.w3-small
                                (cond-> (when (= (dec page) selected)
                                          {:class "w3-disabled"
                                           :style {:cursor :default}})
                                  (fn? opts-go-to) (merge (opts-go-to (dec page))))
                                page]))
                       [:div
                        [:i.w3-button.fa.fa-angle-left
                         (cond-> {}
                           (fn? opts-go-rel) (merge (opts-go-rel -1))
                           (zero? selected) (assoc :class "w3-disabled"))]]))
          [:i.w3-button.fa.fa-angle-right
           (cond-> {}
             (fn? opts-go-rel) (merge (opts-go-rel 1))
             (= (dec n) selected) (assoc :class "w3-disabled"))])))
