(defproject solsort/util "0.0.2-SNAPSHOT"
  :description "Solsort utility-functions"
  :url "https://github.com/rasmuserik/solsort-util"
  :license {:name "Several licenses" :url "https://github.com/rasmuserik/solsort-util"}

  :dependencies
  [[org.clojure/clojure "1.7.0"]
   [org.clojure/clojurescript "1.7.107"]
   [org.clojure/core.async "0.1.346.0-17112a-alpha"]
   [com.cognitect/transit-cljs "0.8.220"]
   [cljsjs/pouchdb "3.5.0-0"]
   [reagent "0.5.0"]
   [re-frame "0.4.1"]]

  :plugins
  [[lein-cljsbuild "1.0.6"]
   [lein-ancient "0.6.7"]
   [michaelblume/lein-marginalia "0.9.0"]
   [lein-figwheel "0.3.7"]
   [lein-kibit "0.1.2"]
   [lein-bikeshed "0.2.0"]
   [cider/cider-nrepl "0.9.1"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} 
  ["resources/public/out" 
   "resources/public/solsort.js" 
   "target"
   ".lein.failures"
   "figwheel_server.log"
   "pom.xml"
   "docs"
  ; "node_modules"
   ]

  :cljsbuild 
  {:builds 
   [{:id "dev"
     :source-paths ["src"]
     :figwheel {:websocket-host ~(.getHostAddress (java.net.InetAddress/getLocalHost))
                :on-jsload "solsort.util/start" }
     :compiler {:main solsort.main
                :asset-path "out"
                :output-to "resources/public/solsort.js"
                :output-dir "resources/public/out"
                :source-map-timestamp true }}

    {:id "dist"
     :source-paths ["src"]
     :compiler {:output-to "resources/public/solsort.js"
                :main solsort.main
                :externs ~(into ["misc/externs.js"]
                                (map 
                                  #(str "node_modules/nodejs-externs/externs/" % ".js")
                                  ["assert" "buffer" "child_process" "cluster" "core"
                                   "crypto" "dgram" "dns" "domain" "events" "fs" "http"
                                   "https" "net" "os" "path" "process" "punycode"
                                   "querystring" "readline" "repl" "stream"
                                   "string_decoder" "tls" "tty" "url" "util" "vm"
                                   "zlib"]))
                :optimizations :advanced
                :pretty-print false}}]
   ; TODO, notes:
   ; https://github.com/cemerick/clojurescript.test/tree/master/resources/cemerick/cljs/test
   ; https://github.com/emezeske/lein-cljsbuild/blob/master/doc/TESTING.md
   ; :test-commands
   ; {"unit-tests" ["phantomjs" :runner "resources/public/solsort.js"]}
   }

  :figwheel {:nrepl-port 7888})
