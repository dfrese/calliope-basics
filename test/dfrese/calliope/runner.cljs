(ns dfrese.calliope.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            dfrese.calliope.location-test))

(doo-tests 'dfrese.calliope.location-test)
