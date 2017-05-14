(ns dfrese.calliope.storage
  (:require [dfrese.calliope.core :as core]
            [dfrese.calliope.ext :as ext]))

(defrecord StorageGetCmd [which key]
  ext/ICmd
  (-run! [this context]
    (ext/dispatch! context (.getItem which key))))

(defrecord StorageSetCmd [which key value]
  ext/ICmd
  (-run! [this context]
    (.setItem which key value)))

(defrecord StorageRemoveCmd [which key]
  ext/ICmd
  (-run! [this context]
    (.removeItem which key)))

(defn local-storage-get [window key]
  (StorageGetCmd. (.-localStorage window) key))

(defn local-storage-set [window key value]
  (StorageSetCmd. (.-localStorage window) key value))

(defn local-storage-remove [window key]
  (StorageRemoveCmd. (.-localStorage window) key))

(defn session-storage-get [window key]
  (StorageGetCmd. (.-sessionStorage window) key))

(defn session-storage-set [window key value]
  (StorageSetCmd. (.-sessionStorage window) key value))

(defn session-storage-remove [window key]
  (StorageRemoveCmd. (.-sessionStorage window) key))
