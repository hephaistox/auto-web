(ns auto-web.assembled.people-card
  "A card to display people."
  (:require
   [auto-web.components.img  :refer [cimg]]
   [auto-web.components.list :refer [csmall-imgs]]))

(defn- on-close-modal
  [modal-kw]
  #?(:cljs (fn [_] (set! (.-style.display (.getElementById js/document modal-kw)) "none"))
     :clj (str "document.getElementById(\"" modal-kw "\").style.display = \"none\";")))

(defn- on-open-modal
  [modal-kw]
  #?(:cljs (fn [_] (set! (.-style.display (.getElementById js/document modal-kw)) "block"))
     :clj (str "document.getElementById(\"" modal-kw "\").style.display = \"block\";")))

(defn cpeople-modal*
  "What is displayed when the modal opens"
  [opts img people-name subtitle card-content]
  [:div.w3-margin.w3-panel
   opts
   [:div.w3-row [:div.w3-xxlarge.w3-bold.w3-left people-name] [:div.w3-right subtitle]]
   [:br]
   [:div
    (cimg {:class "w3-left w3-margin-right w3-image"
           :style {:width "15em"}}
          :small
          img)
    card-content]])

(defn cpeople-modal
  "What is displayed when the modal opens"
  [opts img people-name subtitle card-content]
  (if (nil? card-content)
    (cpeople-modal* {} opts img people-name subtitle)
    (cpeople-modal* opts img people-name subtitle card-content)))

(defn cpeople-card*
  [opts modal-kw social-media-imgs img people-name subtitle modal-content]
  [:div.w3-round-xxlarge.w3-col.m4.w3-card
   opts
   [:div.w3-modal {:id modal-kw
                   :on-click (on-close-modal modal-kw)}
    [:div.w3-modal-content.w3-animate-top.w3-round-xxlarge {:style {:z-index "-9999!important"}}
     modal-content]]
   [:div.w3-padding-24.w3-row {:on-click (on-open-modal modal-kw)}
    [:div.w3-center.w3-row (cimg {:class "w3-circle w3-center w3-margin"} :xsmall img)]
    [:div.w3-row.w3-center
     [:div.w3-large.w3-bold people-name]
     [:p.w3-row.w3-center.w3-small subtitle]
     (csmall-imgs {} social-media-imgs)]]])

(defn cpeople-card
  "Describe a people"
  [opts modal-kw social-media-imgs img people-name subtitle modal-content]
  (if (keyword? opts)
    (cpeople-card* {} opts modal-kw social-media-imgs img people-name subtitle)
    (cpeople-card* opts modal-kw social-media-imgs img people-name subtitle modal-content)))

(defn cpeople-card-with-modal
  [opts modal-kw social-media-imgs img people-name subtitle card-content]
  (cpeople-card opts
                modal-kw
                social-media-imgs
                img
                people-name
                subtitle
                (cpeople-modal {} img people-name subtitle card-content)))
