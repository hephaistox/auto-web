(ns auto-web.components.v-button
  "Buttons"
  (:require
   [auto-web.components.v-link :refer [link-meta]]))

(defn v-button
  "A button from a `link`"
  [link text & opts]
  [:button.w3-button (merge (link-meta link) (first opts)) text])

(defn v-link-button
  "A link visually like a button"
  [link text & opts]
  [:a.w3-button (merge (link-meta link) (first opts)) text])
