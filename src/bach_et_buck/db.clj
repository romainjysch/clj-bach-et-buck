(ns bach-et-buck.db
  (:require [clojure.java.io      :as io]
            [next.jdbc            :as jdbc]
            [next.jdbc.result-set :as rs]))

(def db-spec
  (->> (io/resource "config.edn")
       slurp
       read-string))

(defn get-customers
  []
  (jdbc/execute! db-spec
                 ["SELECT * FROM customers"]
                 {:builder-fn rs/as-kebab-maps}))

(comment
  (get-customers))