(ns bach-et-buck.service
  (:require [bach-et-buck.db :as db]))

(defprotocol ICustomerService
  (find-customers [this]))

(defrecord CustomerService [datasource]
  ICustomerService
  (find-customers [_]
    (db/find-customers datasource)))