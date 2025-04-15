(ns auto-web.components.lang
  "Language based components"
  (:require
   #?(:cljs [auto-web.local-storage :refer [set-item!]]
      :clj [auto-web.http-request.lang :refer [update-cookie-language]])))


#?(:cljs (defn- update-lang-opts
           [on-lang-change l]
           {:on-click #(do (set-item! :lang l) (when on-lang-change (on-lang-change l)))})
   :clj (defn- update-lang-opts [_ l] {:onclick (update-cookie-language l)}))

(defn clang-bar
  "Displays all lang in `langs`, and highlight the `current-lang`. When a lang is actioned it is calling `(switch-lang :pl)` to switch to polish language."
  [opts current-lang langs on-lang-change]
  (reduce (fn [hiccup lang]
            (conj hiccup
                  [:button.w3-button.w3-right
                   (update-lang-opts on-lang-change lang)
                   (if (= current-lang lang) [:u lang] lang)]))
          [:div.w3-bar opts]
          (reverse langs)))
