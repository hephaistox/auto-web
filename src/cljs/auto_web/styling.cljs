(ns auto-web.styling "Basic css html manipulation of dom")

(defn toggle-visibility
  [id]
  (let [el (.getElementById js/document id)
        current (.-style.visibility el)]
    (set! (.-style.visibility el) (if (= current "hidden") "visible" "hidden"))))
