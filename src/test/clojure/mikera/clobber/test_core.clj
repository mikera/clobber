(ns mikera.clobber.test-core
  (:use clojure.test)
  (:use mikera.clobber.core))

(defprofn keyc [obj] :default (count (keys obj)))

(deftest test-key-count
  (is (== 3 (keyc {:a 1 :b 2 :c 3}))))

