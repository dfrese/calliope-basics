(ns dfrese.calliope.location
  (:require [dfrese.calliope.core :as core]
            [dfrese.calliope.browser :as browser]))

(defn- location-hash
  "Returns the current location hash of the given browser window."
  [window]
  ;; e has (.-oldURL e), (.-newURL e).. seems quite useless.
  ;; location.hash is empty, or has a leading #
  (let [hash (.-hash (.-location window))]
    hash))

(defrecord ^:no-doc GetHashCmd [window]
  core/ICmd
  (-run! [this context]
    (core/dispatch! context (location-hash window))))

(defn get-location-hash
  "Returns a command that sends the current location hash."
  [window]
  (GetHashCmd. window))

(defn- target [e]
  (.-target e))

(defn location-hash-updates
  "Returns a subscription to changes of the location hash of the given browser
  window. The message will be the new hash."
  [window]
  (core/sub-> (browser/event-listener-sub window "hashchange" false)
              target
              location-hash))

(defrecord ^:no-doc SetHashCmd [window hash]
  core/ICmd
  (-run! [this context]
    (set! (.-hash (.-location window))
          hash)))

(defn set-location-hash
  "Returns a command that sets the location hash of the given browser window. It does not send any messages."
  [window hash]
  (SetHashCmd. window hash))

