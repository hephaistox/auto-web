(ns auto-web.portfolio.lang
  (:require
   [auto-web.components.lang :refer [clang-bar]]
   [portfolio.react-18       :refer-macros [defscene]]
   [reagent.core             :as reagent]))

(defscene lang-bar-test
          "# A lang bar"
          :params
          (atom {:lang :fr})
          [params]
          (let [{:keys [lang]} @params]
            (reagent/as-element [:div.w3-cell
                                 [clang-bar {:class "w3-orange"}
                                  lang
                                  [:fr :pl :en]
                                  #(swap! params assoc :lang %)]
                                 lang])))
