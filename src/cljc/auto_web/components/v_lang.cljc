(ns auto-web.components.v-lang "Language based components")

(defn lang-bar
  "Displays all lang in `langs`, and highlight the `current-lang`. When a lang is actioned it is calling `(switch-lang :pl)` to switch to polish language."
  [langs current-lang & opts]
  (reduce (fn [hiccup lang]
            (conj hiccup
                  [:button.w3-button.w3-right
                   (assoc (second opts)
                          :onclick
                          (str "document.cookie = \"lang=" lang "; path=/\";location.reload();"))
                   (if (= current-lang lang) [:u lang] lang)]))
          [:div.w3-bar (first opts)]
          (reverse langs)))
