(ns auto-web.components.menu-cards
  (:require
   [auto-web.components.menu :refer [chorizontal-text-menu cvertical-text-menu]]
   [devcards.core            :refer [defcard]]
   [reagent.core             :as reagent]))

(defcard
 horizontal-text-menu
 "### A menu displayed with texts,

Note that
- the border is added by the options
- the bar is all screen large"
 (fn [data-atom _owner]
   (reagent/as-element [chorizontal-text-menu {:class "w3-border"}
                        @data-atom]))
 [{:url "https://hephaistox.com"
   :link-id :foo
   :text "site en .com"}
  {:url "https://hephaistox.fr"
   :link-id :bar
   :text "site en .fr"}]
 {:inspect-data true})

(defcard horizontal-text-menu-no-opts
         "### A menu with no options"
         (fn [data-atom _owner] (reagent/as-element [chorizontal-text-menu @data-atom]))
         [{:url "https://hephaistox.com"
           :link-id "foo2"
           :text "site en .com"}
          {:url "https://hephaistox.fr"
           :link-id "bar2"
           :text "site en .fr"}]
         {:inspect-data true})

(defcard vertical-text-menu-no-opts
         "### A vertical menu with no options

Note that:
- Each elements are the whole width"
         (fn [data-atom _owner]
           (reagent/as-element [cvertical-text-menu {:class "w3-border"}
                                @data-atom]))
         [{:url "https://hephaistox.com"
           :link-id "foo3"
           :text "site en .com"}
          {:url "https://hephaistox.fr"
           :link-id "bar3"
           :text "site en .fr"}]
         {:inspect-data true})
