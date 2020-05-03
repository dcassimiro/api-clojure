(ns api.github
  (:require
    [clj-http.client :as client]
    [cheshire.core :as json]
    [api.query :refer :all]))

(defn trans [obj]
  (let [str (json/generate-string obj) str-json (json/parse-string str true)]
    str-json))

(defn get-repo [owner repo]
  (let [
        url (str "https://api.github.com/repos/" owner "/" repo)
        res (:body (client/get url{:decode-body-headers true :as :auto}))]
    (insert-repos res)
    (trans res)))

(defn get-repos [user]
  (let [url (str "https://api.github.com/users/" user)
        res (:body (client/get url{:decode-body-headers true :as :auto}))]
    (insert-users res))
  (let [
        url (str "https://api.github.com/users/" user "/repos")
        res (:body (client/get url{:decode-body-headers true :as :auto}))]
    (trans res)))