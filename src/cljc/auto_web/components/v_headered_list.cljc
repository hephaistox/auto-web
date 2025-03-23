(ns auto-web.components.v-headered-list
  "A long lasting list of elements, each separated with a hr, has its icon, short and long text."
  (:require
   [auto-web.components.sizes            :as wsizes]
   [auto-web.components.v-labelled-image :as wvlabelled-image]
   [auto-web.components.v-space          :as wv-space]
   [auto-web.links                       :as wlinks]
   [clojure.string                       :as str]))

(defn header
  "Show the header of the items.

  Is a collection maps, each has `name` and `img-url` keywords."
  [items]
  [:div.w3-row.w3-center
   (reduce (fn [hiccup {:keys [name img-url href]}]
             (conj hiccup
                   [:div.w3-container.w3-cell.w3-mobile
                    [wvlabelled-image/labelled-image img-url href name :tiny]]))
           [:span]
           items)])

(defn detailed-list
  "List elements of `items`

  * Start with the image of `img-url` (on top on small screen, on left on medium and wide screens)
  * `href`
  * The `label` as a title
  * Then a small `desc` highlighted
  * And a `long-desc`

  All that box is linked into `href` link."
  [items image-width-kw]
  (let [actual-size (wsizes/predefined-size image-width-kw)]
    [:table.w3-table.detailed-list
     (->> items
          (reduce
           (fn [hiccup-item {:keys [img-url href label desc long-desc]}]
             (conj
              hiccup-item
              [:tr
               [:td.w3-centered.w3-hide-small.w3-hide-medium {:style {:max-width actual-size
                                                                      :width actual-size}}
                (when img-url [wvlabelled-image/labelled-image img-url href "" image-width-kw])]
               [:td]
               [:td
                (wlinks/a href [:h1.text label])
                [:div.w3-centered.w3-hide-large {:style {:max-width actual-size
                                                         :width actual-size}}
                 (when img-url [wvlabelled-image/labelled-image img-url href "" image-width-kw])]
                (when-not (str/blank? desc)
                  [:div.w3-row-padding [:div.w3-panel.w3-leftbar.text.light-bg.adaptative desc]])
                (when-not (str/blank? long-desc) [:div.text long-desc])
                [wv-space/vertical-spacing]]]))
           [:tbody]))]))
