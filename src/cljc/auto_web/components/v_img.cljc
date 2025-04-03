(ns auto-web.components.v-img
  "An img is made of:

  * `url` is where the img is stored
  * `alt` what is displayed if image can't display or for accessibility
  * `name`
  * `href` where click will lead to, could be nil
  * `label` text to display below the img (optional).
  * `width-kw` width of the picture, `:full` is taking the whole width, other are using `predefined-size`
  * `class` is a string that can be added to the img's class."
  (:require
   [auto-core.schema          :refer [validate-data-humanize]]
   [auto-web.components.sizes :as wsizes]))

;; ********************************************************************************
;; Definition
(defn validate-img-schema
  [img]
  (validate-data-humanize [:map
                           [:url :string]
                           [:alt :string]
                           [:name :keyword]
                           [:label {:optional true}
                            :string]]
                          img))

(defn- img-meta
  "Metadata of an img html element."
  [img]
  (let [{:keys [url target alt name]
         :or {url "imgs/no_img.png"}}
        img]
    {:src url
     :target target
     "data-img-name" name
     :alt alt}))

(defn v-img
  "Display an `img` with its label:

  See [[auto-web.components.v-img]] for options."
  [img width-kw & opts]
  [:img.w3-img
   (merge (assoc (img-meta img)
                 :style
                 (cond
                   (and (some? width-kw) (not= width-kw :full))
                   {:max-width (wsizes/predefined-size width-kw)
                    :width "80%"
                    :min-width (wsizes/min-size width-kw)}
                   (= :full width-kw) {:width "100%"}
                   :else {}))
          (first opts))])

(defn v-labelled-img
  "Display an `img` with a label.

  * `url` is where the img is stored
  * `href-link` where click will lead to, could be nil
  * `label` text to display below the img.
  * `width-kw` width of the picture, `:full` is taking the whole width, other are using `predefined-size`
  * `class` is a string that can be added to the img's class. "
  [img width-kw & opts]
  [:div.w3-center
   (apply v-img img width-kw opts)
   (when-let [label (:label img)] [:p.w3-small label])])

(defn v-icon-img
  "An img that looks like an icon"
  [img]
  (let [width (wsizes/predefined-size :icon)]
    [:img.w3-img
     (assoc (img-meta img)
            :style
            {:max-width width
             :width width
             :min-width width
             :margin-block-start "1em"
             :margin-block-end "1em"
             :margin-inline-start "0px"
             :margin-inline-end "0px"
             :unicode-bidi "isolate"})]))
