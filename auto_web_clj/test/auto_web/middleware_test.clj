(ns auto-web.middleware-test
  (:require
   [auto-web.middleware :as sut]
   [clojure.test        :refer [deftest is]]))

(deftest wrap-exception-handling-test
  (is (= {:ret true} ((sut/wrap-exception-handling (fn [_] {:ret true}) nil) {:lost true}))
      "A sync handler without exception returns its response")
  (is (= {:ret true}
         ((sut/wrap-exception-handling (fn [_ _ _] {:ret true}) nil) {:lost true} nil nil))
      "An async handler without exception returns its response")
  (is (= {:error :page}
         ((sut/wrap-exception-handling (fn [_] (throw (ex-info "Arg" {})))
                                       (fn [_ _e] {:error :page}))
          {:lost true}))
      "A sync handler with an exception returns the error page")
  (is (= {:error :page}
         ((sut/wrap-exception-handling (fn [_ _ _] (throw (ex-info "Arg" {})))
                                       (fn [_ _] {:error :page}))
          {:lost true}
          identity
          identity))
      "An async handler with errors returns the error page"))
