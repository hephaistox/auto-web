(ns auto-web.components.v-headered-list
  "A long lasting list of elements, each separated with a its icon, short and long text."
  (:require
   [auto-web.components.sizes  :as wsizes]
   [auto-web.components.v-img  :refer [v-labelled-img]]
   [auto-web.components.v-link :refer [v-a]]
   [clojure.string             :as str]))

(defn v-list-header
  "Show the header of the items:

  * Start with the image of `image` (on top on small screen, on left on medium and wide screens), linked to `link`
  * `title`
  * Then a small `desc` highlighted
  * And a `long-desc`"
  [items & opts]
  [:div.w3-row.w3-center
   (first opts)
   (reduce (fn [hiccup {:keys [name img link]}]
             (conj hiccup
                   [:div.w3-container.w3-cell.w3-mobile
                    (second opts)
                    (v-a link (v-labelled-img img name :tiny))]))
           [:span]
           items)])

(defn v-list
  "List elements of `items`:

  * Start with the image of `img` (on top on small screen, on left on medium and wide screens), linked to `link`
  * `title`
  * Then a small `desc` highlighted
  * And a `long-desc`

  All that box is linked into `link` link."
  [items img-width-kw & opts]
  [:table.w3-table.detailed-list
   (first opts)
   (reduce (fn [hiccup-item {:keys [img link title desc long-desc]}]
             (let [actual-size (wsizes/predefined-size img-width-kw)]
               (conj hiccup-item
                     [:tr
                      [:td.w3-centered.w3-hide-small.w3-hide-medium {:style {:max-width actual-size
                                                                             :width actual-size}}
                       (when img (v-a link (v-labelled-img img img-width-kw)))]
                      [:td]
                      [:td.w3-padding
                       (when-not (str/blank? title) (v-a link [:h1.text title]))
                       [:div.w3-centered.w3-hide-large {:style {:max-width actual-size
                                                                :width actual-size}}
                        (when img (v-a link (v-labelled-img img img-width-kw)))]
                       (when-not (str/blank? desc)
                         [:div.w3-row-padding
                          [:div.w3-panel.w3-leftbar.text.light-bg.adaptative desc]])
                       (when-not (str/blank? long-desc) [:div.text long-desc])]])))
           [:tbody]
           items)])
