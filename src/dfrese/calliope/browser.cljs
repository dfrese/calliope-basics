(ns dfrese.calliope.browser
  (:require [dfrese.calliope.core :as core]
            [dfrese.calliope.ext :as ext]))

;; commands that search for elements? (elementById, querySelector?)

(defrecord ^:no-doc Focus
  [document id]
  ext/ICmd
  (-run! [this context]
    (when-let [node (.getElementById document id)]
      (.focus node))))

;; also relevant: document.activeElement, document.hasFocus

(defn focus "Returns a command that sets the focus to the DOM element
  with the given id, in the given document. Note that the id does not
  need to exist now, and is it's silently ignored if does not later." ;; TODO: better explain
  [document id]
  (Focus. document id))

;; TODO: title
;; document.readyState?
;; document.referrer

;; window.open

(defrecord ^:no-doc EventListenerSub
  [^js/EventTarget target event-name use-capture?]
  ext/ISub
  (-subscribe! [this context]
    (let [f (fn [e]
              (ext/dispatch! context e))]
      (.addEventListener target event-name f use-capture?)
      f))
  (-unsubscribe! [this f]
    (.removeEventListener target event-name f use-capture?)))

(defn event-listener-sub [^js/EventTarget target event-name use-capture?]
  (EventListenerSub. target event-name use-capture?))


;; TODO html5 history:
;; state

;; replaceState - change url
;; pushState - change url and go forward in history

;; back, forward, go
