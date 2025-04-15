(ns auto-web.components.link
  "A link is made of
  * `url`
  * `target`
  * `link-id`
  * `download?`
  * `ping`
  * `skip-test?` is optional and used by test only to check."
  (:require
   [auto-web.page.builder :refer [merge-opts]]))

(defn link-schema
  [_link]
  [:map
   [:url :string]
   [:target {:optional true}
    [:enum "blank"]]
   [:link-id :keyword]
   [:download? {:optional true}
    :boolean]
   [:ping {:optional true}
    :string]
   [:skip-test? {:optional true}
    :boolean]])

(defn- link-opts
  "Options for an `a` html tag"
  [{:keys [url target link-id download? ping]}]
  (cond-> {:href url
           "data-link-name" (some-> link-id
                                    name)}
    ping (assoc :ping ping)
    target (assoc :target target)
    download? (assoc :download download?)
    download? (assoc-in [:style :cursor] "alias")))

(defn clink
  "An hiccup `:a` tag to the [[auto-web.components.link]].
  `content` is what is wrapped by the `:a` tag."
  [opts link & content]
  [:div.w3-tooltip
   (vec (concat (cond
                  link [:a (merge-opts (link-opts link) opts)]
                  opts [:span opts])
                content))
   (when (:download? link)
     [:div.w3-tag.w3-text {:style {:position "absolute"
                                   :left "0"
                                   :bottom "1.5em"}}
      "Download"])])

(defn cspan-link
  "An hiccup `:a` tag to the [[auto-web.components.link]].
  `content` is what is wrapped by the `:a` tag."
  [opts link & content]
  [:a (merge-opts (link-opts link) (assoc-in opts [:style :text-decoration] "underline")) content])
