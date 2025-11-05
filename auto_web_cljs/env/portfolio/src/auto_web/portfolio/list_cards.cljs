(ns auto-web.portfolio.list-cards
  (:require
   [auto-web.components.list :refer [cbar cbar-img cbar-item cbullet csmall-imgs]]
   [portfolio.react-18       :refer-macros [defscene]]
   [reagent.core             :as reagent]))

(defscene
 bullet
 "# Bullets list - Each item in the list is a bullet.

Note that:
- Opts with boder is applied.
- Bullets are customizable with `fa-icon`.
- The default bullet is a circle.
- You can define a tooltip.
- Labels can be linked."
 (let [v [{:fa-icon "fa-exclamation"
           :react-key :fa
           :link {:url "https://hephaistox.fr"}
           :label "This is the label"
           :desc "This is the description"}
          {:link {:url "https://hephaistox.com"}
           :label "This is the label"
           :desc "This one has the default bullet"}
          {:desc "With desc only"}
          {:desc "With a tooltip"
           :tooltip "This is a tooltip"}]]
   (reagent/as-element [:div
                        [cbullet {:class "w3-border"}
                         v]
                        [:p (pr-str v)]])))

(defscene
 bullet-with-no-opts
 "# Bullets list - Opts are missing

Note that:
- Borders have disappeared,
- But the rest is the same than the previous card"
 (reagent/as-element [cbullet
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
                        :tooltip "This is a tooltip"}]]))

(defscene
 bar-item
 "# One item in a bar

Note that:
- The element looks like a button when hovered,
- Has a tooltip"
 (reagent/as-element [:div.w3-cell
                      [cbar-item {}
                       {:fa-icon "fa-exclamation"
                        :link {:url "https://hephaistox.fr"}
                        :tooltip "This is a tooltip"}]]))

(defscene bar-item-with-no-tooltip
          "# One item of a list bar, with no tooltip"
          (reagent/as-element [cbar-item {}
                               {:fa-icon "fa-exclamation"
                                :link {:url "https://hephaistox.fr"}}]))

(defscene bar-item-with-no-opts
          "# One bar item with no options"
          (reagent/as-element [cbar-item {:fa-icon "fa-exclamation"
                                          :link {:url "https://hephaistox.fr"}}]))

(defscene bar
          "# One bar of icon lists"
          (reagent/as-element [:div.w3-display-container {:style {:height "100px"}}
                               [:div.w3-display-middle
                                [cbar {:class "w3-cell"}
                                 [{:fa-icon "fa-exclamation"
                                   :react-key :fa
                                   :link {:url "https://hephaistox.fr"
                                          :link-id :hephaistox-1}}
                                  {:link {:url "https://hephaistox.com"
                                          :link-id :hephaistox-2}}
                                  {}
                                  {:tooltip "This is a tooltip"}]]]]))

(defscene
 bar-img
 "# Same with icons replacing images

Note that:
- Tooltip has non breakable spaces
- Now the element is an image, not an icon"
 (reagent/as-element [:div.w3-cell
                      [cbar-img {}
                       {:img {:url "/images/logos/hephaistox_logo.png"}
                        :react-key :fa
                        :link {:url "https://hephaistox.fr"}
                        :tooltip "This is a tooltip"
                        :label "This is the label"
                        :desc "This is the description"}]]))

(defscene bar-img-no-image
          "# When the image is missing"
          (reagent/as-element [:div.w3-cell
                               [cbar-img {}
                                {:img {}
                                 :react-key :fa
                                 :link {:url "https://hephaistox.fr"}
                                 :tooltip "This is a tooltip"
                                 :label "This is the label"
                                 :desc "This is the description"}]]))


(defscene small-imgs
          "# With a small image"
          (reagent/as-element [:div.w3-cell
                               [csmall-imgs {}
                                [{:fa-icon "fa-exclamation"
                                  :link {:url "https://hephaistox.fr"}
                                  :label "This is the exclamation label"}
                                 {:fa-icon "fa-question"
                                  :link {:url "https://hephaistox.fr"}
                                  :tooltip "This is the question tooltip"
                                  :label "This is the label"}]]]))
