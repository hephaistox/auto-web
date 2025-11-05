(ns auto-web.components.button
  "Buttons"
  (:require
   [auto-web.components.link :refer [link-opts]]))

(defn- clink-button*
  [opts link text]
  [:a
   (-> opts
       (update :class #(str "w3-button w3-ripple " %))
       (merge (link-opts link)))
   text])

(defn clink-button
  "A link visually like a button"
  [opts link text]
  (if (map? opts) (clink-button* opts link text) (clink-button* {} opts link)))


(defn- cbutton*
  [opts link text]
  [:a
   (-> opts
       (update :class #(str "w3-button w3-ripple " %))
       (merge (link-opts link)))
   text])

(defn cbutton
  "A link visually like a button"
  [opts link text]
  (if (map? opts) (cbutton* opts link text) (cbutton* {} opts link)))
