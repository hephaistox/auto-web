(ns auto-web.links
  "Manage a link library.

  A link library is a map with
  * `image-links`
  * `route-links` internal routing of the website
  * `external-links` urls pointing to external website
  * `site-url` url where the home page is deployed.")

(defn img-meta
  "Metadata of an img html element."
  [link]
  (when (map? link)
    (let [{:keys [url target alt name]
           :or {url "images/no_image.png"}}
          link]
      {:src url
       :target target
       "linkname" name
       :alt alt})))

(defn link-meta
  "Metadata for an `a` html tag"
  [link]
  (when (map? link)
    (let [{:keys [url target alt name]} link]
      {:href url
       :target target
       "link-name" name
       :alt alt})))

(defn a "Returns an hiccup `:a` tag to the link." [link content] [:a (link-meta link) content])
