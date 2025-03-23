(ns auto-web.http-request.lang-test
  (:require
   [auto-web.http-request.lang :as sut]
   [clojure.test               :refer [deftest is]]))

(deftest cookies-lang-test
  (is (nil? (sut/cookies-lang {})) "no lang returns nil")
  (is (nil? (sut/cookies-lang {})) "null")
  (is (sut/cookies-lang {:cookies {"lang" {:value ":pl"}}})))
