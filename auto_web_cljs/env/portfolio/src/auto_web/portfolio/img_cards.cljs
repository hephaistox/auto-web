(ns auto-web.portfolio.img-cards
  (:require
   [auto-web.components.img :refer [cicon cicon-img cimg clabelled-img]]
   [portfolio.react-18      :refer-macros [defscene]]
   [reagent.core            :as reagent]))

(defscene
 img-no-image
 "# No valid image provided

- Hephaistox has its own broken link image shown when no valid link id is provided.
- If a link is defined, and the image is broken, the broken link image won't be shown. To detect this, please consult admin page."
 (reagent/as-element [cimg :tiny {}]))

(defscene img-test
          "# A valid image provided"
          (reagent/as-element [cimg
                               :tiny
                               {:url "/images/logos/hephaistox_logo.png"
                                :img-id :logo
                                :alt "alt message"}]))


(defscene labelled-img-test
          "# A labelled image"
          (reagent/as-element [clabelled-img
                               :tiny
                               {:url "/images/logos/hephaistox_logo.png"
                                :img-id :logo
                                :label "Got it"
                                :alt "alt message"}]))

(defscene icon-img-test
          "# An cicon image"
          (reagent/as-element [:div.w3-cell
                               [cicon-img {:url "/images/logos/hephaistox_logo.png"
                                           :img-id :logo
                                           :label "Got it"
                                           :alt "alt message"}]]))

(defscene icon-test
          "# A small font awesome icon"
          (reagent/as-element [cicon {}
                               "fa-exclamation"]))
