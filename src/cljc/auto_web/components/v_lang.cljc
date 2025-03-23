(ns auto-web.components.v-lang
  "Language based components"
  (:require
   [auto-web.components.items            :as wcitems]
   [auto-web.components.v-labelled-image :as vclabelled-image]))

(defn defaulting
  "Translates `img-link` and `href-link` with `image-dic` and `doc-dic`"
  [items image-dic doc-dic]
  (update-vals items
               (fn [doc]
                 (-> doc
                     (wcitems/apply-dic [:img-link] image-dic)
                     (wcitems/apply-dic [:href-link] doc-dic)))))

(def defaulting* (memoize defaulting))

(defn vclabelled-image
  "Show an image based on a language.

  * `l` is the language to display
  * ` doc-links` contains two keys
     * `img-link` what is shown
     * `href-link` destination link when actioned"
  ([l doc-links] (vclabelled-image l doc-links nil))
  ([l doc-links label] (vclabelled-image l doc-links label :medium))
  ([l doc-links label size]
   (let [{:keys [img-link href-link]} (get doc-links l)]
     [vclabelled-image/labelled-image img-link href-link label size])))

(defn vclabelled-rounded-image
  "Show an image based on a language.

  * `l` is the language to display
  * ` doc-links` contains two keys
     * `img-link` what is shown
     * `href-link` destination link when actioned"
  ([l doc-links] (vclabelled-image l doc-links nil))
  ([l doc-links label] (vclabelled-image l doc-links label :medium))
  ([l doc-links label size]
   (let [{:keys [img-link href-link]} (get doc-links l)]
     [vclabelled-image/labelled-image-circle img-link href-link label size])))

(defn lang-bar
  "Displays all lang in `langs`, and highlight the `current-lang`. When a lang is actioned it is calling `(switch-lang :pl)` to switch to polish language."
  [langs current-lang switch-lang]
  (reduce (fn [hiccup lang]
            (conj hiccup
                  [:button.w3-button.w3-right
                   (cond-> {}
                     (= current-lang lang) (assoc :class "secondary-bg secondary-text")
                     switch-lang (assoc :on-click #(switch-lang lang)))
                   lang]))
          [:<>]
          langs))
