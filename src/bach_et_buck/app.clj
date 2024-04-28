(ns bach-et-buck.app
  (:require [clojure.tools.logging :as log]
            [bach-et-buck.server   :as http]))

(defn start [& _]
  (log/info "HTTP server starting...")
  (http/start-server)
  (log/info "HTTP server started."))

(comment
  (start))