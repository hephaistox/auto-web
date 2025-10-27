(ns auto-web.lang-test
  (:require
   [auto-web.lang :as sut]
   #?(:clj [clojure.test :refer [deftest is]]
      :cljs [cljs.test :refer [deftest is] :include-macros true])))

(deftest into-languages-test
  (is (= [{:weight 100
           :base-lang :en
           :subtag "US"}
          {:weight 0.9
           :base-lang :en
           :subtag nil}
          {:weight 0.8
           :base-lang :zh
           :subtag "CN"}
          {:weight 0.7
           :base-lang :zh
           :subtag nil}]
         (sut/into-languages "en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7"))))

(deftest accepted-lang-test
  (is (= :zh
         (sut/accepted-lang [{:weight 100
                              :base-lang :en
                              :subtag "US"}
                             {:weight 0.9
                              :base-lang :en
                              :subtag nil}
                             {:weight 0.8
                              :base-lang :zh
                              :subtag "CN"}
                             {:weight 0.7
                              :base-lang :zh
                              :subtag nil}]
                            #{:zh}))
      "One only matching language")
  (is (= :en
         (sut/accepted-lang [{:weight 100
                              :base-lang :en
                              :subtag "US"}
                             {:weight 0.9
                              :base-lang :en
                              :subtag nil}
                             {:weight 0.8
                              :base-lang :zh
                              :subtag "CN"}
                             {:weight 0.7
                              :base-lang :zh
                              :subtag nil}]
                            #{:zh :en}))
      "The highest weight language is selected")
  (is (nil? (sut/accepted-lang [{:weight 100
                                 :base-lang :en
                                 :subtag "US"}
                                {:weight 0.9
                                 :base-lang :en
                                 :subtag nil}
                                {:weight 0.8
                                 :base-lang :zh
                                 :subtag "CN"}
                                {:weight 0.7
                                 :base-lang :zh
                                 :subtag nil}]
                               #{:non-existing}))
      "No language is selected"))

