(ns auto-web.lang-fe
  "Language strategy from the frontend"
  (:require
   [auto-web.local-storage :as wlocal-storage]))

(defn page-language
  "Returns the language, the first valid one in that order:

  * First, the one stored in the local storage
  * Then, the language stored in the js.
  * In worst case, the `:en` language"
  []
  (let [ls-lang (wlocal-storage/get-ls :lang)
        server-accepted-lang js/lang]
    (cond
      (some? ls-lang) (keyword ls-lang)
      server-accepted-lang (keyword server-accepted-lang)
      :else :en)))

(defn clang
  []
  (-> (or js/varlang "fr")
      keyword))
