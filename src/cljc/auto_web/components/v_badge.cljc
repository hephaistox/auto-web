(ns auto-web.components.v-badge)

(defn v-badge
  "Display an icon"
  [fa-valid fa-invalid valid? text & opts]
  [:span.w3-hover-opacity.w3-tooltip
   (first opts)
   [:i.fa {:class (if valid? fa-valid fa-invalid)
           :style {:height "1em"
                   :width "1em"}}]
   (when text
     [:span.w3-text.w3-tag {:style {:position "absolute"
                                    :left "0"
                                    :bottom "2em"}}
      text])])

(defn v-enclosed-info
  "Display an icon"
  [fa-valid fa-invalid valid? text & opts]
  [:span.w3-hover-opacity.w3-tooltip
   (first opts)
   [:i.fa {:class (if valid? fa-valid fa-invalid)
           :style {:height "1em"
                   :width "1em"}}]
   (when text [:span.w3-text.w3-tag text])])
