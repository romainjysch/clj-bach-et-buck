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
                 ["select c.lastname,
                          c.firstname,
                          c.gender,
                          c.birthday,
                          TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()) as age,
                          c.cardnumber
                   from customers c"]
                 {:builder-fn rs/as-kebab-maps}))

(comment
  (get-customers))