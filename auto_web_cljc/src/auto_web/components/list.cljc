(ns auto-web.components.list
  "Visual components for lists.

  A list is described with a collection of:

   * `fa-icon` is a font awesome icon
   * `react-key` is used to add a react key
   * `link` address to point (applied to the rouding box)
   * `label` the name to add on the right of the icon
   * `tooltip`
   * `desc` the description to continue the label explanation"
  (:require
   [auto-core.uuid           :refer [unguessable]]
   [auto-web.components.img  :refer [cicon cicon-img]]
   [auto-web.components.link :refer [link-opts]]
   [clojure.string           :refer [blank?]]))

(defn cbullet
  "Simple bullet list of `items`, that should define `fa-icon`, `react-key`, `link`, `label`, and `desc`"
  [opts items]
  (let [opts* (if (nil? items) {} opts)
        items* (if (nil? items) opts items)]
    (reduce (fn [hiccup
                 {:keys [fa-icon react-key link label tooltip desc]
                  :or {fa-icon "fa-circle"
                       react-key (unguessable)}}]
              (conj hiccup
                    ^{:key react-key}
                    [:li.text.w3-tooltip {:style {:overflow "visible"}}
                     [:i.fa {:class (str fa-icon " w3-center")
                             :style {:width "1em"}}]
                     (if-not (blank? label)
                       [:b.w3-margin.w3-button.w3-hover-opacity
                        [:a (link-opts link) (str label ":")]]
                       [:b.w3-margin-right])
                     (when tooltip
                       [:span.w3-text.w3-tag {:style {:position "absolute"
                                                      :left "0"
                                                      :bottom "2em"}}
                        tooltip])
                     desc]))
            [:ul.w3-ul opts*]
            items*)))

(defn cbar-item
  "Display an icon, as in a bar"
  [opts item]
  (let [opts* (if (nil? item) {} opts)
        item* (if (nil? item) opts item)
        {:keys [fa-icon link tooltip]
         :or {fa-icon "fa-circle"}}
        item*]
    [:div.w3-tooltip
     (assoc-in opts* [:style :overflow] "visible")
     [:a
      (link-opts link)
      [:i.fa.w3-btn.w3-center {:class fa-icon
                               :style {:overflow "visible"}}]]
     (when tooltip
       [:span.w3-text.w3-tag {:style {:position "absolute"
                                      :left "0"
                                      :bottom "2.5em"}}
        tooltip])]))

(defn cbar
  "Display as a bar of icons"
  [opts items]
  (reduce (fn [hiccup item]
            (if (some? item)
              (conj hiccup
                    (cbar-item {:class "w3-bar-item"
                                :style {:padding "0px"}}
                               item))
              hiccup))
          [:div.w3-bar
           (-> opts
               (assoc-in [:style :overflow] "visible"))]
          items))

(defn cbar-img
  "Display as an image, as in a bar"
  [opts
   {:keys [img react-key link tooltip]
    :or {react-key (unguessable)
         img "images/not_found.jpg"}}]
  ^{:key react-key}
  [:div.w3-tooltip
   (assoc-in opts [:style :overflow] "visible")
   [:a (link-opts link) [:i.fa.w3-btn.w3-center (cicon-img {} img)]]
   (when tooltip
     [:span.w3-text.w3-tag {:style {:position "absolute"
                                    :left "0"
                                    :bottom "2em"}}
      tooltip])])

(defn csmall-imgs
  "List `items` to display as small buttons all on the same row."
  [opts items]
  (let [opts (if (nil? items) {} opts)
        items (if (nil? items) opts items)]
    (reduce (fn [hiccup item]
              (let [{:keys [fa-icon link label]} item]
                (conj hiccup
                      (when (seq item)
                        [:a
                         (link-opts link)
                         [:div.w3-tooltip.w3-button {:style {:overflow "visible"}}
                          [:div.w3-hover-opacity (cicon {} fa-icon)]
                          (when label
                            [:div.w3-text.w3-tag {:style {:bottom "-2em"
                                                          :left "0em"
                                                          :position "absolute"}}
                             label])]]))))
            [:div.w3-container.w3-content.w3-flex
             (assoc-in opts [:style :justify-content] "center")]
            items)))
