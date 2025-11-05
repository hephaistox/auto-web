(ns auto-web.components.link
  "A link is made of
  * `url` where the link lead into
  * `target` as defined in the html `:a` target
  * `link-id` the internal name of it")

(defn link-opts
  "Returns link options for a link"
  [link]
  (let [{:keys [url target link-id]} link]
    {:href url
     :target target
     "data-link-name" (some-> link-id
                              name)}))
