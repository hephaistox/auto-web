(ns auto-web.local-storage
  "Provide data persistance in local storage for clojurescript."
  (:require
   [re-frame.core :as rf]))

(defn set-ls!
  "Set `key` in browser's localStorage to `val`."
  [key val]
  (.setItem js/localStorage key (js/JSON.stringify (clj->js val))))

(defn get-ls
  "Returns value of `key` from browser's localStorage."
  [key]
  (some-> (.getItem js/localStorage key)
          (js/JSON.parse)
          (js->clj :keywordize-keys true)))

(defn remove-item!
  "Remove the browser's localStorage value for the given `key`"
  [key]
  (.removeItem (.-localStorage js/window) key))

(comment
  (set-ls! "cooc"
           {:a 12
            :b "foo"})
  (get-ls "cooc")
  (rf/dispatch [::save-to-ls [:auto-sim.canvas/canvas :canvas-1]])
  ;
)
