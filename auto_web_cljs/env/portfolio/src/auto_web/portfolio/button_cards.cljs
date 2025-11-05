(ns auto-web.portfolio.button-cards
  (:require
   [auto-web.components.button :refer [clink-button]]
   [portfolio.react-18         :refer-macros [defscene]]
   [reagent.core               :as reagent]))

(defscene button-nolink
          "# A simple button, no link defined"
          :params
          "w3-orange"
          [params]
          (reagent/as-element [clink-button {:class params}
                               {}
                               "Testing"]))

(defscene button
          "# A simple button, a link is defnied"
          :params
          "w3-orange"
          [params]
          (reagent/as-element [clink-button {:class params}
                               {:url "https://hephaistox.fr"}
                               "Testing"]))

(defscene
 button-with-options
 "# A simple button showing an alert when clicked

Note that:
- The orange background is added
- The link has been removed in the example, but it could have been kept
- So is the the on-click behavior"
 (reagent/as-element [clink-button {:on-click #(js/alert "Simple button alert")
                                    :class "w3-orange"}
                      {}
                      "testing"]))

