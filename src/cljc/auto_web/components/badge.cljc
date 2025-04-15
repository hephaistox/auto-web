(ns auto-web.components.badge
  (:require
   [auto-web.components.img :refer [cimg images]]
   [auto-web.page.builder   :refer [merge-opts]]))

(defn badge*
  [opts fa-valid fa-invalid valid? text]
  [:span.w3-tooltip
   opts
   [:i.fa.w3-hover-opacity {:class (if valid? fa-valid fa-invalid)
                            :style {:height "1em"
                                    :width "1em"}}]
   (when text
     [:span.w3-text.w3-tag {:style {:position "absolute"
                                    :left "0"
                                    :bottom "1.5em"}}
      text])])

(defn cbadge
  "Display a badge as an icon,

  `opts` is an optional map,
  `fa-valid` and `fa-invalid` are classes"
  [opts fa-valid fa-invalid valid? text]
  (if (map? opts)
    (badge* opts fa-valid fa-invalid valid? text)
    (badge* {} opts fa-valid fa-invalid valid?)))

(defn cunknown
  [opts]
  (let [opts (or opts {})]
    [:div.w3-hover-opacity.w3-tooltip
     opts
     [:i.fa.fa-question.w3-text-grey {:style {:height "1em"
                                              :width "1em"}}]]))

(defn cinvalid
  [opts]
  (let [opts (or opts {})]
    [:div.w3-hover-opacity.w3-tooltip
     opts
     [:i.fa.fa-exclamation-triangle.w3-text-red {:style {:height "1em"
                                                         :width "1em"}}]]))

(defn cvalid
  [opts]
  (let [opts (or opts {})]
    [:div.w3-hover-opacity.w3-tooltip
     opts
     [:i.fa.fa-check.w3-text-green {:style {:height "1em"
                                            :width "1em"}}]]))
(defn copyright-str
  "String with current year Hephaistox copyright"
  []
  (str "Hephaistox © "
       #?(:clj (.getYear (java.time.LocalDateTime/now))
          :cljs (.getFullYear (js/Date.)))))

(defn cspinner
  [opts]
  (let [opts (or opts {})]
    (cimg (merge-opts {:style {:user-drag "none"
                               :-webkit-user-drag "none"
                               :-moz-user-select "none"
                               :-ms-user-select "none"}}
                      opts)
          :big
          (:spinner images))))
