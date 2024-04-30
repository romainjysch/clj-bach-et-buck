(ns bach-et-buck.http
  (:require [bach-et-buck.db   :as db]
            [clojure.data.json :refer [write-str]]))

(defn get-customers
  [request]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (str (write-str (db/get-customers)))})