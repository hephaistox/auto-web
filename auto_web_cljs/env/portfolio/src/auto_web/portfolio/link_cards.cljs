(ns auto-web.portfolio.link-cards
  (:require
   [auto-web.components.link :refer [link-opts]]
   [portfolio.react-18       :refer-macros [defscene]]
   [reagent.core             :as reagent]))

(defscene a-test
          "# A link with an :a, opt is an orange background"
          (reagent/as-element [:a
                               (-> (link-opts {:url "http://google.fr"
                                               :link-id :id-test})
                                   (assoc :class "w3-orange"))
                               "testing"]))

(defscene a-div
          "# A link on a div"
          (reagent/as-element [:a
                               (-> (link-opts {:url "http://google.fr"
                                               :link-id :id-test})
                                   (assoc :class "w3-orange"))
                               [:div.w3-cell.w3-yellow {:style {:width "4em"
                                                                :height "4em"}}
                                "Arg"]]))

(defscene a-with-target
          "# An anchor with target"
          (reagent/as-element [:a
                               (-> (link-opts {:url "http://google.fr"
                                               :target "blank"
                                               :link-id :id-test})
                                   (assoc :class "w3-orange"))
                               "with blank target"]))

