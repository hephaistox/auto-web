(ns auto-web.http-request.lang
  "Parse a request to determine the language to display.

  A [[default-be-strategy]] is provided, but you can also build your own by assembling [[cookies-lang]], [[accepted-lang-request]], [[tld-lang]]"
  (:require
   [auto-core.http.url :as url]
   [auto-web.lang      :refer [accepted-lang into-languages]]
   [clojure.string     :as str]))

(defn accept-lang-str
  "The accepted language's string in a request: e.g: `\"en-US,en;q=0.9\"`"
  [http-request]
  (get-in http-request [:headers "accept-language"]))

(defn accepted-lang-request
  "The language matching one possible-lang among `query-langs`"
  [http-request possible-langs]
  (-> http-request
      accept-lang-str
      into-languages
      (accepted-lang possible-langs)))

(defn tld-lang
  "The tld in the host of the http-request"
  [http-request]
  (-> http-request
      (get-in [:headers "host"])
      url/extract-tld))

(defn- remove-leading-colon [s] (if (str/starts-with? s ":") (subs s 1) s))

(defn cookies-lang
  "Language defined in the cookies under the `lang` key"
  [http-request]
  (let [lang (get-in http-request [:cookies "lang" :value])]
    (cond
      (= lang "null") nil
      (string? lang) (keyword (remove-leading-colon (str/lower-case lang)))
      :else nil)))

(defn default-be-strategy
  "For the backend, the language is coming from the cookie, or the language in the accepted languages"
  [http-request possible-langs]
  (let [r (or (cookies-lang http-request) (accepted-lang-request http-request possible-langs))] r))
