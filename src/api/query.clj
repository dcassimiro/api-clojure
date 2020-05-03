(ns api.query
  (:require [api.database]
            [korma.core :refer :all]))

(defentity users)

(defentity repositories)

(defn get-all-users []
  (select users))


(defn insert-users [obj]
  (insert users
          (values {:id (:id obj)
                   :node_id (:node_id obj)
                   :login (:login obj)
                   :html_url (:html_url obj)
                   :name (:name obj)
                   :company (:company obj)
                   :location (:location obj)
                   :email (:email obj)
                   :bio (:bio obj)
                   :public_repos (:public_repos obj)
                   :followers (:followers obj)
                   :following (:following obj)
                   })))

(defn get-all-repos []
  (select repositories))

(defn insert-repos [obj]
  (insert repositories
          (values {:id (:id obj)
                   :node_id (:node_id obj)
                   :name (:name obj)
                   :html_url (:html_url obj)
                   :user_id (:id (:owner obj))
                   }))
  )

;@ToDO
;(defn get-repo-by-user-id [id]
;  (first
;    (select repositories
;            (where {:user_id [= id]}))))