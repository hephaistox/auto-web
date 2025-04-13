(ns auto-web.components.link-cards
  (:require
   [auto-web.components.link :refer [clink]]
   [devcards.core            :refer [defcard]]
   [reagent.core             :as reagent]))

(defcard a-test
         "### An anchor of a string, opt is an orange background"
         (fn [data-atom _owner]
           (let [{:keys [link opts]} @data-atom]
             (reagent/as-element [:div.w3-cell [clink opts link "testing"]])))
         {:link {:url "http://google.fr"
                 :link-id :id-test}
          :opts {:class "w3-orange"}}
         {:inspect-data true})

(defcard a-div
         "### An anchor of a div"
         (fn [data-atom _owner]
           (let [{:keys [link opts]} @data-atom]
             (reagent/as-element [:div.w3-cell
                                  [clink
                                   opts
                                   link
                                   [:div.w3-cell.w3-yellow {:style {:width "4em"
                                                                    :height "4em"}}
                                    "Arg"]]])))
         {:link {:url "http://google.fr"
                 :link-id :id-test}
          :opts {:class "w3-orange"}}
         {:inspect-data true})

(defcard a-w-target
         "### An anchor with target"
         (fn [data-atom _owner]
           (let [{:keys [link opts]} @data-atom]
             (reagent/as-element [:div.w3-cell [clink opts link "with blank target"]])))
         {:link {:url "http://google.fr"
                 :target "blank"
                 :link-id :id-test}
          :opts {:class "w3-orange"}}
         {:inspect-data true})

(defcard a-downloadable?
         "### A download anchor, the cursor is different, a tooltip tells it is a download"
         (fn [data-atom _owner]
           (let [{:keys [link opts]} @data-atom]
             (reagent/as-element [:div.w3-cell [clink opts link "testing"]])))
         {:link {:url "/images/logos/hephaistox_logo.png"
                 :download? true
                 :link-id :logo}
          :opts {:class "w3-orange"}}
         {:inspect-data true})
