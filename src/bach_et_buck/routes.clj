(ns bach-et-buck.routes
  (:require [bach-et-buck.http :as http]))

(def routes
  [["/customers" {:get http/get-customers}]])