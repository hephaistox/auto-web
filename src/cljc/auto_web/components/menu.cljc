(ns auto-web.components.menu
  (:require
   [auto-web.components.link :refer [clink]]))

(defn chorizontal-text-menu
  "A menu made of text elements only, organized horizontally."
  [opts links]
  (let [opts* (if (coll? links) opts {})
        links* (if (coll? links) links opts)]
    (vec (concat [:div.w3-flex (assoc-in opts* [:style :justify-content] "center")]
                 (map (fn [link]
                        (clink {:class "w3-bar-item w3-button w3-mobile"} link (:text link)))
                      links*)))))

(defn cvertical-text-menu
  "A menu made of text elements only, organized vertically."
  [opts links]
  (let [opts* (if (coll? links) opts {})
        links* (if (coll? links) links opts)]
    (vec (concat [:div.w3-bar-block opts*]
                 (map (fn [link]
                        (clink {:class "w3-bar-item w3-button w3-mobile"} link (:text link)))
                      links*)))))
