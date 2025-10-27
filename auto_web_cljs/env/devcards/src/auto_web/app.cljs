(ns auto-web.app
  (:require
   [reagent.core :as r]
   [reagent.dom  :as dom]))

(defn app
  []
  (let [state (r/atom {:counter 0})
        change (fn [event f] (.preventDefault event) (swap! state update-in [:counter] f))]
    (fn [] [:h2
            "Counter"
            [:div
             [:button {:on-click #(change % dec)}
              "-"]
             [:span (:counter @state)]
             [:button {:on-click #(change % inc)}
              "+"]]])))

(defn ^:dev/after-load start! [] (dom/render [app] (.getElementById js/document "app")))

(defn init! [] (start!))
