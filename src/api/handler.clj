(ns api.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as json]
            [ring.util.response :refer [response]]
            [api.github :refer :all]
            ))

(defroutes app-routes
           (GET "/repos/:user" [user]
             (response (get-repos user)))

           (GET "/repo/:owner/:repo" [owner repo]
             (response (get-repo owner repo)))

           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (json/wrap-json-params)
      (json/wrap-json-response)))