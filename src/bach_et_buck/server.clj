(ns bach-et-buck.server
  (:require [bach-et-buck.service       :as service]
            [com.stuartsierra.component :as component]
            [clojure.data.json          :refer [write-str]]
            [clojure.tools.logging      :refer [info]]
            [compojure.core             :refer [routes GET]]
            [ring.adapter.jetty         :refer [run-jetty]]
            [ring.middleware.json       :refer [wrap-json-body wrap-json-response]]))

(defn make-handler [service]
  (routes
        (GET "/customers" []
          (info "GET /customers asked")
          {:status  200
           :headers {"Content-Type" "application/json"}
           :body    (write-str (service/find-all-customers service))})

        (GET "/customer/:cardnumber" [cardnumber]
          (info "GET /customer/:cardnumber asked for" cardnumber)
          {:status  200
           :headers {"Content-Type" "application/json"}
           :body    (write-str (service/find-customer-by-cardnumber service cardnumber))})))

(defrecord Server [config
                   service] ;; dependency
  component/Lifecycle
  (start [this]
    (assoc this :server (run-jetty (-> (#'make-handler service)
                                       wrap-json-response
                                       wrap-json-body)
                                   {:join? false
                                    :port (get config :port 11000)})))
  (stop [this]
    (let [{:keys [server]} this]
      (when server
        (.stop (:server this))))
    this))