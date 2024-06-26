(ns bach-et-buck.app
  (:require [bach-et-buck.system   :as system]
            [clojure.java.io       :as io]
            [clojure.tools.logging :as log]))

(defn read-config
  []
  (->> (io/resource "config.edn")
       slurp
       read-string))

(defn -main
  [& _]
  (let [config (read-config)]
    (log/info "Server starting...")
    (.start (system/build-server config)))
    (log/info "Server started."))

(comment
  (-main))

(comment
  (use 'com.stuartsierra.component)
  (def system (.start (system/build-server (read-config))))
  (stop system))