(ns auto-web.components.button
  "Buttons"
  (:require
   [auto-web.components.link :refer [clink]]))

(defn cbutton* [opts text link] (clink {} link [:button.w3-button opts text]))

(defn cbutton
  "A button from a `link`"
  [opts text link]
  (if (nil? link) (cbutton* {} opts text) (cbutton* opts text link)))

(defn clink-button* [opts text link] (clink (update opts :class #(str "w3-button " %)) link text))

(defn clink-button
  "A link visually like a button"
  [opts text link]
  (if (map? opts) (clink-button* opts text link) (clink-button* {} opts text)))
