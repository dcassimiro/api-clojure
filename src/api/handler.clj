(ns api.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as json]
            [ring.util.response :refer [response]]
            [api.github :refer :all]
            [api.query :refer :all]
            ))

(defroutes app-routes
           (GET "/api/users" []
             (response (get-all-users)))

           (GET "/api/repos" []
             (response (get-all-repos)))

           (GET "/api/repos/:user" [user]
             (response (get-repos user)))

           (GET "/api/repos/:owner/:repo" [owner repo]
             (response (get-repo owner repo)))

           ;@ToDO
           ;(GET "/api/repos/userid/:id" [id]
           ;  (response (get-repo-by-user-id (Integer/parseInt id))))

           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (json/wrap-json-params)
      (json/wrap-json-response)))