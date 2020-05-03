(ns api.database
  (:require [korma.db :as korma]))

 (def db-connection-info
   (korma/sqlite3
     {:classname "org.sqlite.JDBC"
      :subprotocol "sqlite"
      :subname "db/github.db"}))

;set up korma
 (korma/defdb db db-connection-info)