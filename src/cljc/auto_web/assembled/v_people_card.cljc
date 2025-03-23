(ns auto-web.assembled.v-people-card
  "A card to display people."
  (:require
   [auto-web.components.v-img  :refer [v-img]]
   [auto-web.components.v-list :refer [v-small-icons]]))

(defn people-description-card
  "What is displayed when the modal opens"
  [img people-name subtitle card-content]
  [:div.w3-padding.w3-margin.w3-panel
   [:div.w3-row [:div.w3-xxlarge.w3-bold.w3-left people-name] [:div.w3-right subtitle]]
   [:br]
   [:div
    (v-img img
           :small
           {:class "w3-left w3-margin-right w3-image"
            :style {:width "15em"}})
    card-content]])

(defn people-card
  [id icons img people-name subtitle desc]
  [:div.w3-round-xxlarge.w3-col.m4.w3-card.w3-padding
   [:div.w3-modal {:id (name id)
                   :onclick (str "document.getElementById('" (name id) "').style.display='none';")}
    [:div.w3-modal-content.w3-animate-top.w3-round-xxlarge {:style {:z-index "-9999!important"}}
     desc]]
   [:div.w3-padding-24.w3-row {:onclick (str "document.getElementById('"
                                             (name id)
                                             "').style.display='block';")}
    [:div.w3-center.w3-row (v-img img :xsmall {:class "w3-circle w3-center w3-margin"})]
    [:div.w3-row.w3-center
     [:div.w3-large.w3-bold people-name]
     [:p.w3-row.w3-center.w3-small subtitle]
     (v-small-icons icons)]]])

(defn people-card-with-description
  [id icons img people-name subtitle card-content]
  (people-card id
               icons
               img
               people-name
               subtitle
               (people-description-card img people-name subtitle card-content)))
