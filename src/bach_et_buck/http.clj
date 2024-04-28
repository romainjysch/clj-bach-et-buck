(ns bach-et-buck.http
  (:require [bach-et-buck.db :as db]
            [clojure.data.json       :as json]))

(defn get-customers
  [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Coucou"})