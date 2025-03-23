(ns auto-web.components.v-link
  "A link is made of
  * `url`
  * `target`
  * `name`
  * `download?`
  * `skip-test?`
  * `ping`"
  (:require
   [auto-core.schema :refer [validate-data-humanize]]))

(defn validate-link-schema
  [link]
  (validate-data-humanize [:map
                           [:url :string]
                           [:target {:optional true}
                            [:enum "blank"]]
                           [:name :keyword]
                           [:download? {:optional true}
                            :boolean]
                           [:skip-test? {:optional true}
                            :boolean]
                           [:ping {:optional true}
                            :string]]
                          link))

(defn link-meta
  "Metadata for an `a` html tag"
  [link]
  (when (map? link)
    (let [{:keys [url target name download? ping]} link]
      (cond-> {:href url
               "link-name" name}
        ping (assoc :ping ping)
        target (assoc :target target)
        download? (assoc :download nil)))))

(defn v-a
  "Returns an hiccup `:a` tag to the link.
  `content` is what is wrapped by the `:a` tag.

  See [[auto-web.components.v-link]] to see link options."
  [link content & opts]
  (cond
    link [:a (merge (link-meta link) (first opts)) content]
    (first opts) [:span (first opts) content]
    :else content))
