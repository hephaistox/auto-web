(ns auto-web.page.be-structure
  (:require
   [hiccup2.core :as hiccup2]))

(defn js-script-raw-meta
  "A meta tag to insert `content` as javascript."
  [content]
  [:script {:type "text/javascript"}
   (hiccup2/raw content)])
