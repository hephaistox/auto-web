(ns auto-web.assembled.people-card-cards
  (:require
   [auto-web.assembled.people-card :refer [cpeople-card cpeople-card-with-modal cpeople-modal]]
   [devcards.core                  :refer [defcard]]
   [reagent.core                   :as reagent]))

(defcard
 people-modal-test
 "## The modal displayed"
 (reagent/as-element
  [cpeople-modal {:url "/images/logos/hephaistox_logo.png"}
   "John Doe"
   "North Pole"
   "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
   Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
   Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."]))

(defcard people-card-test
         "## The card"
         (reagent/as-element [:div.w3-row
                              [cpeople-card
                               :test-modal-id
                               []
                               {:url "/images/logos/hephaistox_logo.png"}
                               "Saaska"
                               "Au collège"
                               "En 6ème 1"]]))

(defcard people-card-with-modal-test
         "## The card and its modal"
         (reagent/as-element [:div.w3-row
                              [cpeople-card-with-modal {}
                               :test-modal-id2
                               []
                               {:url "/images/logos/hephaistox_logo.png"}
                               "Jane Doe"
                               "Atlantide"
                               "Somewhere"]]))
