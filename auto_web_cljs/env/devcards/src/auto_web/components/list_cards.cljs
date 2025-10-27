(ns auto-web.components.list-cards
  (:require
   [auto-web.components.list :refer [cbar cbar-img cbar-item cbullet csmall-imgs]]
   [devcards.core            :refer [defcard]]
   [reagent.core             :as reagent]))

(defcard
 bullet
 "### Bullets list - Each item in the list is a bullet.

Note that:
- Opts with boder is applied.
- Bullets are customizable.
- The default bullet is a circle.
- You can define a tooltip.
- Labels can be linked."
 (fn [data-atom _owner]
   (reagent/as-element [cbullet {:class "w3-border"}
                        @data-atom]))
 [{:fa-icon "fa-exclamation"
   :react-key :fa
   :link {:url "https://hephaistox.fr"}
   :label "This is the label"
   :desc "This is the description"}
  {:link {:url "https://hephaistox.com"}
   :label "This is the label"
   :desc "This one has the default bullet"}
  {:desc "With desc only"}
  {:desc "With a tooltip"
   :tooltip "This is a tooltip"}]
 {:inspect-data true})

(defcard
 bullet-with-no-opts
 "### Bullets list - Opts are missing

Note that:
- Borders have disappeared,
- But the rest is the same than the previous card"
 (fn [data-atom _owner] (reagent/as-element [cbullet @data-atom]))
 [{:fa-icon "fa-exclamation"
   :react-key :fa
   :link {:url "https://hephaistox.fr"}
   :label "This is the label"
   :desc "This is the description"}
  {:link {:url "https://hephaistox.com"}
   :label "This is the label"
   :desc "This one has the default bullet"}
  {:desc "With desc only"}
  {:desc "With a tooltip"
   :tooltip "This is a tooltip"}]
 {:inspect-data true})

(defcard
 bar-item
 "### One item in a bar

Note that:
- The element looks like a button when hovered,
- Has a tooltip"
 (fn [data-atom _owner]
   (reagent/as-element [:div.w3-cell
                        [cbar-item {}
                         @data-atom]]))
 {:fa-icon "fa-exclamation"
  :link {:url "https://hephaistox.fr"}
  :tooltip "This is a tooltip"}
 {:inspect-data true})

(defcard bar-item-with-no-tooltip
         "### One item of a list bar, with no tooltip"
         (fn [data-atom _owner]
           (reagent/as-element [cbar-item {}
                                @data-atom]))
         {:fa-icon "fa-exclamation"
          :link {:url "https://hephaistox.fr"}}
         {:inspect-data true})

(defcard bar-item-with-no-opts
         "### One bar item with no options"
         (fn [data-atom _owner] (reagent/as-element [cbar-item @data-atom]))
         {:fa-icon "fa-exclamation"
          :link {:url "https://hephaistox.fr"}}
         {:inspect-data true})

(defcard bar
         "### One bar of icon lists"
         (fn [data-atom _owner]
           (reagent/as-element [:div.w3-display-container {:style {:height "100px"}}
                                [:div.w3-display-middle
                                 [cbar {:class "w3-cell"}
                                  @data-atom]]]))
         [{:fa-icon "fa-exclamation"
           :react-key :fa
           :link {:url "https://hephaistox.fr"
                  :link-id :hephaistox-1}}
          {:link {:url "https://hephaistox.com"
                  :link-id :hephaistox-2}}
          {}
          {:tooltip "This is a tooltip"}]
         {:inspect-data true})

(defcard
 bar-img
 "## Same with icons replacing images

Note that:
- Tooltip has non breakable spaces
- Now the element is an image, not an icon"
 (fn [data-atom _owner]
   (reagent/as-element [:div.w3-cell
                        [cbar-img {}
                         @data-atom]]))
 {:img {:url "images/logos/hephaistox_logo.png"}
  :react-key :fa
  :link {:url "https://hephaistox.fr"}
  :tooltip "This is a tooltip"
  :label "This is the label"
  :desc "This is the description"}
 {:inspect-data true})

(defcard bar-img-no-image
         "## When the image is missing"
         (fn [data-atom _owner]
           (reagent/as-element [:div.w3-cell
                                [cbar-img {}
                                 @data-atom]]))
         {:img {}
          :react-key :fa
          :link {:url "https://hephaistox.fr"}
          :tooltip "This is a tooltip"
          :label "This is the label"
          :desc "This is the description"}
         {:inspect-data true})


(defcard small-imgs
         (fn [data-atom _owner]
           (reagent/as-element [:div.w3-cell
                                [csmall-imgs {}
                                 @data-atom]]))
         [{:fa-icon "fa-exclamation"
           :link {:url "https://hephaistox.fr"}
           :label "This is the exclamation label"}
          {:fa-icon "fa-question"
           :link {:url "https://hephaistox.fr"}
           :tooltip "This is the question tooltip"
           :label "This is the label"}]
         {:inspect-data true})
