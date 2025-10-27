(ns auto-web.components.img
  "An img is made of:

  * `url` is where the img is stored
  * `alt` what is displayed if image can't display or for accessibility
  * `img-id` the name of the image
  * `label` (optional) text to display below the img

  An img is often associated with:
    * `width-kw` width of the picture, `:full` is taking the whole width, other are using `predefined-size`"
  (:require
   [auto-web.components.sizes :refer [min-size predefined-size]]
   [auto-web.page.builder     :refer [merge-opts]]))

;; ********************************************************************************
;; Definition

(def images
  (->> [{:url "/images/logos/hephaistox_logo.png"
         :alt "Hephaistox logo"
         :img-id :hephaistox-logo
         :label "Hephaistox"}
        {:url "/images/logos/javascript.png"
         :alt "Javascript logo"
         :img-id :javascript
         :label "Javascript"}
        {:url "/images/logos/not_def.png"
         :alt "Not defined"
         :img-id :not-def}
        {:url "/images/not_found.jpg"
         :alt "Not found"
         :img-id :not-found}
        {:url "/images/toolings/spinner.gif"
         :alt "Spinner"
         :img-id :spinner}]
       (mapv (fn [{:keys [img-id]
                   :as img}]
               [img-id img]))
       (into {})))

(defn img-schema
  [_img]
  [:map
   [:url :string]
   [:alt :string]
   [:img-id :keyword]
   [:label {:optional true}
    :string]])

(defn- img-meta
  "Metadata of an img html element."
  [img]
  (let [{:keys [url target alt img-id]
         :or {url "images/logos/not_def.png"}}
        img]
    {:src url
     :target target
     "data-img-id" img-id
     :alt alt}))

(defn cimg*
  "Display an `img` with its label:

  See [[auto-web.components.img]] for options."
  [opts width-kw img]
  [:img.w3-img
   (merge-opts (assoc (img-meta img)
                      :style
                      (cond
                        (and (some? width-kw) (not= width-kw :full))
                        {:max-width (predefined-size width-kw)
                         :width "80%"
                         :min-width (min-size width-kw)}
                        (= :full width-kw) {:width "100%"}
                        :else {}))
               opts)])

(defn cimg
  "Display an `img`

  See [[auto-web.components.img]] for options."
  [opts width-kw img]
  (if (map? opts) (cimg* opts width-kw img) (cimg* {} opts width-kw)))

(defn clabelled-img
  "Display an `img` with a label.

  See [[auto-web.components.img]] for options."
  [opts img width-kw]
  [:div.w3-center (cimg opts img width-kw) (when-let [label (:label img)] [:p.w3-small label])])

(defn cicon-img*
  "An img that looks like an icon"
  [opts img]
  (let [width (predefined-size :icon)]
    [:img.w3-img
     (merge-opts (assoc (img-meta img)
                        :style
                        {:max-width width
                         :width width
                         :min-width width
                         :margin-block-start "1em"
                         :margin-block-end "1em"
                         :margin-inline-start "0px"
                         :margin-inline-end "0px"
                         :unicode-bidi "isolate"})
                 opts)]))

(defn cicon-img
  "An img that looks like an icon"
  [opts img]
  (if (nil? img) (cicon-img* {} opts) (cicon-img* opts img)))

(defn cicon "A font awesome icon" [opts fa] [:i.fa (merge-opts opts {:class fa})])
