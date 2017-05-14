(ns dfrese.calliope.location-test
  (:require [cljs.test :refer-macros [deftest is testing async]]
            [dfrese.calliope.location :as location]
            [dfrese.calliope.core :as core]
            [dfrese.calliope.ext :as ext]))

(deftest location-hash-test
  (testing "get command"
    (let [c (location/get-location-hash js/window)
          a (atom nil)]
      (set! (.-hash (.-location js/window)) "#foo")
      (ext/-run! c (ext/context (fn [v]
                                  (reset! a v))))
      (is (= "#foo" @a))))
  (testing "set command"
    (let [c (location/set-location-hash js/window "#foo")]
      (set! (.-hash (.-location js/window)) "")
      (ext/-run! c (ext/context (fn [v]
                                  (is false))))
      (is (= "#foo" (.-hash (.-location js/window)))))))

(deftest location-hash-updates-test
  (async done
         (let [s (location/location-hash-updates js/window)
               a (atom nil)]
           (set! (.-hash (.-location js/window)) "")
           (ext/-subscribe! s (ext/context (fn [v]
                                             (is (= v "#foo"))
                                             (done))))
           (set! (.-hash (.-location js/window)) "#foo"))))
