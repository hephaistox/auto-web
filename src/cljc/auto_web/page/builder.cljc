(ns auto-web.page.builder "Tooling to create a web page")

(defn all-pages-metas
  "For all pages, necessary informations for browsers"
  [title icon]
  (let [icon (or icon "/favicon.ico")]
    [[:meta {:name "title"
             :property "og:title"
             :content title}]
     [:meta {:name "twitter:title"
             :content title}]
     [:title title]
     [:meta {:name "og:type"
             :property "og:type"
             :content "website"}]
     [:link {:rel "icon"
             :href icon}]]))

(defn seo-page-metas
  "For public pages that SEO could find - including pages known by social medias"
  [author description img-url url]
  [[:meta {:name "author"
           :content author}]
   [:meta {:name "description"
           :property "og:description"
           :content description}]
   [:meta {:name "image"
           :property "og:image"
           :content img-url}]
   [:meta {:name "og:url"
           :property "og:url"
           :content url}]])

(defn X-prepared-page-metas
  "Can be referenced in Twitter"
  [description twitter-site img-url]
  [[:meta {:name "twitter:site"
           :content twitter-site}]
   [:meta {:name "twitter:image"
           :content img-url}]
   [:meta {:name "twitter:card"
           :content "summary_large_image"}]
   [:meta {:name "twitter:description"
           :content description}]])

(defn css-meta
  "Add a css stylesheet"
  [{:keys [url]
    :as _link}]
  [:link {:type "text/css"
          :rel "stylesheet"
          :href url}])

(defn css-meta-preloaded
  "Add a css stylesheet"
  [{:keys [url]
    :as _link}]
  [:link
   {:type "text/css"
    :rel "preload"
    :as "style"
    :onload "this.rel='stylesheet'"
    :href url}])

(defn js-script-link
  [{:keys [url]
    :as _link}]
  [:script {:type "text/javascript"
            :crossorigin "anonymous"
            :src url}])

