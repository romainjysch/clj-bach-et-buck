(ns bach-et-buck.server
  (:require [bach-et-buck.service       :as service]
            [com.stuartsierra.component :as component]
            [clojure.data.json          :refer [write-str]]
            [ring.adapter.jetty         :refer [run-jetty]]
            [compojure.core             :refer [routes GET]]))

(defn make-handler
  [service]
  (routes
    (GET "/customers" [] (write-str (service/find-customers service)))))

(defrecord Server [config
                   service] ;; dependency
  component/Lifecycle
  (start [this]
    (assoc this :server (run-jetty (#'make-handler service)
                                   {:join? false
                                    :port (get config :port 11000)})))
  (stop [this]
    (let [{:keys [server]} this]
      (when server
        (.stop (:server this))))
    this))