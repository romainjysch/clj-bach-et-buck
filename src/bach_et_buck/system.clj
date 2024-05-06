(ns bach-et-buck.system
  (:require [bach-et-buck.datasource    :as datasource]
            [bach-et-buck.server        :as server]
            [bach-et-buck.service       :as service]
            [com.stuartsierra.component :refer [system-map using]]))

(defn build-system [{{:keys[creds]} :db}]
  (system-map
    :datasource (datasource/map->Datasource {:config creds})
    :service (using (service/map->CustomerService {}) [:datasource])))

(defn build-server [{:keys [server] :as config}]
  (merge (build-system config)
         {:server (using (server/map->Server {:config server}) [:service])}))