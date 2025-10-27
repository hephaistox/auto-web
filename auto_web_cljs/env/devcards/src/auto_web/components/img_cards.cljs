(ns auto-web.components.img-cards
  (:require
   [auto-web.components.img :refer [cicon cicon-img cimg clabelled-img]]
   [devcards.core           :refer [defcard]]
   [reagent.core            :as reagent]))

(defcard img-no-image
         "### No valid image provided"
         (fn [data-atom _owner] (reagent/as-element [cimg :tiny @data-atom]))
         {}
         {:inspect-data true})

(defcard img-test
         "### A valid image provided"
         (fn [data-atom _owner] (reagent/as-element [cimg :tiny @data-atom]))
         {:url "/images/logos/hephaistox_logo.png"
          :img-id :logo
          :alt "alt message"}
         {:inspect-data true})

(defcard labelled-img-wo-label
         "### A labelled image with no label is like an image!"
         (fn [data-atom _owner]
           (reagent/as-element [:div.w3-cell [clabelled-img :tiny @data-atom]]))
         {:url "/images/logos/hephaistox_logo.png"
          :img-id :logo
          :alt "alt message"}
         {:inspect-data true})

(defcard labelled-img-test
         "### A labelled image"
         (fn [data-atom _owner]
           (reagent/as-element [:div.w3-cell [clabelled-img :tiny @data-atom]]))
         {:url "/images/logos/hephaistox_logo.png"
          :img-id :logo
          :label "Got it"
          :alt "alt message"}
         {:inspect-data true})

(defcard icon-img-test
         "### An cicon image"
         (fn [data-atom _owner] (reagent/as-element [:div.w3-cell [cicon-img @data-atom]]))
         {:url "/images/logos/hephaistox_logo.png"
          :img-id :logo
          :label "Got it"
          :alt "alt message"}
         {:inspect-data true})

(defcard icon-test
         "### A small font awesome icon"
         (reagent/as-element [:div.w3-center
                              [cicon {}
                               "fa-exclamation"]])
         {}
         {:inspect-data true})
