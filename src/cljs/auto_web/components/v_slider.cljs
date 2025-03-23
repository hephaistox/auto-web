(ns auto-web.components.v-slider
  "A slider shows a list of items, with a navigator to crawl them."
  (:require
   [auto-web.components.c-selector       :as wcselector]
   [auto-web.components.v-labelled-image :as wvlabelled-image]
   [auto-web.components.v-space          :as wvspace]
   [clojure.string                       :as str]))

(defn subtitle-img-long-desc
  "The slider shows an image with a title (i.e. `desc-title`) and a detailed description (i.e. `desc-detailed-title`)

  A selector with the number of `items` to show is displayed, and the `item-n` item is displayed.

  `go-to` is called with `(go-to nitems page)` and returns an option map
  `go-rel` is called with `(go-rel nitems page)` and returns an option map"
  [desc-detailed-title desc-title items item-n kw]
  (let [nitems (count items)
        {:keys [title sub-title img-link desc details href]} (nth items
                                                                  (if (number? item-n) item-n 0))
        selector [:div.w3-center [wcselector/c-selector kw nitems]]]
    [:div
     selector
     (when (some? title) [:h2.text title])
     (when (some? sub-title) [:h3.text sub-title])
     [:div.w3-row [:p.w3-third] [wvlabelled-image/raw-image img-link href nil :medium]]
     [wvspace/vertical-spacing]
     [:div.w3-container.w3-card-4 [:h3 desc-title ": "] [:p.text desc]]
     [wvspace/vertical-spacing]
     (when-not (str/blank? details) [:p desc-detailed-title ": "])
     (when-not (str/blank? details) [:p.text details])
     [wvspace/vertical-spacing]
     selector]))
