(defproject dfrese/calliope-basics "0.1.0-SNAPSHOT"
  :description "Basic utilities to write calliope application."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.9.229" :scope "provided"]
                 [dfrese/edomus "0.1.0-SNAPSHOT"]
                 [dfrese/calliope-core "0.1.0-SNAPSHOT"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-doo "0.1.7"]
            [lein-codox "0.10.1"]]

  :cljsbuild
  {:builds {:test
            {:source-paths ["src" "test"]
             :compiler {:output-to "target/test.js"
                        :output-dir "target"
                        :optimizations :none
                        :main dfrese.calliope.runner}}}}

  :doo {:build "test"}

  :codox {:language :clojurescript
          :metadata {:doc/format :markdown}}
  )
