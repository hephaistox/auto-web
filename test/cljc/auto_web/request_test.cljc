(ns auto-web.request-test
  (:require
   [auto-web.request :as sut]
   #?(:clj [clojure.test :refer [deftest is]]
      :cljs [cljs.test :refer [deftest is] :include-macros true])))

(deftest accept-languages-str-test
  (is (= "en-US,en;q=0.9"
         (sut/accept-languages-str {:headers {"accept-language" "en-US,en;q=0.9"}}))
      "Accepted language"))

(deftest str-to-languages-test
  (is (= [{:weight 100
           :base-lang "en"
           :subtag "US"}
          {:weight 0.9
           :base-lang "en"
           :subtag nil}
          {:weight 0.8
           :base-lang "zh"
           :subtag "CN"}
          {:weight 0.7
           :base-lang "zh"
           :subtag nil}]
         (sut/str-to-languages "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7"))))

(deftest match-lang-test
  (is (= "zh"
         (sut/match-lang [{:weight 100
                           :base-lang "en"
                           :subtag "US"}
                          {:weight 0.9
                           :base-lang "en"
                           :subtag nil}
                          {:weight 0.8
                           :base-lang "zh"
                           :subtag "CN"}
                          {:weight 0.7
                           :base-lang "zh"
                           :subtag nil}]
                         #{"zh"}))
      "One only matching language")
  (is (= "en"
         (sut/match-lang [{:weight 100
                           :base-lang "en"
                           :subtag "US"}
                          {:weight 0.9
                           :base-lang "en"
                           :subtag nil}
                          {:weight 0.8
                           :base-lang "zh"
                           :subtag "CN"}
                          {:weight 0.7
                           :base-lang "zh"
                           :subtag nil}]
                         #{"zh" "en"}))
      "The highest weight language is selected")
  (is (nil? (sut/match-lang [{:weight 100
                              :base-lang "en"
                              :subtag "US"}
                             {:weight 0.9
                              :base-lang "en"
                              :subtag nil}
                             {:weight 0.8
                              :base-lang "zh"
                              :subtag "CN"}
                             {:weight 0.7
                              :base-lang "zh"
                              :subtag nil}]
                            #{"non-existing"}))
      "No language is selected"))
