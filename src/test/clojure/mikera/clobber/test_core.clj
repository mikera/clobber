(ns mikera.clobber.test-core
  (:use clojure.test)
  (:use mikera.clobber.core)
  (:require mikera.clobber.test-core))

(defprofn keyc [obj] :default (count (keys obj)))

(defprofn nullc [obj] :null 1)

(deftest test-key-count
  (is (== 3 (keyc {:a 1 :b 2 :c 3})))
  (is (thrown? Throwable (keyc nil)))
  (let [ob {:keyc (fn [& args] "foo!")}]
    (= "foo!" (keyc ob))))

(deftest test-nullc
  (is (== 1 (nullc nil)))
  (is (thrown? Throwable (nullc {:foo :bar}))))

