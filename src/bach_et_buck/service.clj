(ns bach-et-buck.service
  (:require [bach-et-buck.db :as db]))

(defprotocol IClientService
  (find-all-clients [this])
  (find-client-by-id [this id])
  (find-client-by-card-number [this card-number]))

(defrecord ClientService [datasource]
  IClientService
  (find-all-clients [_]
    (db/find-all-clients datasource))
  (find-client-by-id [_ id]
    (db/find-client-by-id datasource id))
  (find-client-by-card-number [_ card-number]
    (db/find-client-by-card-number card-number)))