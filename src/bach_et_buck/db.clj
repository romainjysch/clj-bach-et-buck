(ns bach-et-buck.db
  (:require [seql.helpers :refer [make-schema entity field ident]]
            [seql.core    :refer [query]]))

(def schema
  (make-schema
    (entity :customers
            (field :numero (ident))
            (field :lastname)
            (field :firstname)
            (field :gender)
            (field :birthday)
            (field :cardnumber))))

(defn find-customers [datasource]
  (query datasource
         :customers
         [:customers/lastname
          :customers/firstname
          :customers/gender
          :customers/birthday
          :customers/cardnumber]))