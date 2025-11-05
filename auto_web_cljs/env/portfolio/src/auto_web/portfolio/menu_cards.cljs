(ns auto-web.portfolio.menu-cards
  (:require
   [auto-web.components.menu :refer [chorizontal-text-menu cvertical-text-menu]]
   [portfolio.react-18       :refer-macros [defscene]]
   [reagent.core             :as reagent]))

(defscene
 horizontal-text-menu
 "# A menu displayed with texts,

Note that
- the border is added by the options
- the bar is all screen large"
 (reagent/as-element [chorizontal-text-menu {:class "w3-border"}
                      [{:url "https://hephaistox.com"
                        :link-id :foo
                        :text "site en .com"}
                       {:url "https://hephaistox.fr"
                        :link-id :bar
                        :text "site en .fr"}]]))

(defscene horizontal-text-menu-no-opts
          "# A menu with no options"
          (reagent/as-element [chorizontal-text-menu
                               [{:url "https://hephaistox.com"
                                 :link-id "foo2"
                                 :text "site en .com"}
                                {:url "https://hephaistox.fr"
                                 :link-id "bar2"
                                 :text "site en .fr"}]]))

(defscene vertical-text-menu-no-opts
          "# A vertical menu with no options

Note that:
- Each elements are the whole width"
          (reagent/as-element [cvertical-text-menu {:class "w3-border"}
                               [{:url "https://hephaistox.com"
                                 :link-id "foo3"
                                 :text "site en .com"}
                                {:url "https://hephaistox.fr"
                                 :link-id "bar3"
                                 :text "site en .fr"}]]))
