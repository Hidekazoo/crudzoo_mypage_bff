(ns crudzoo-mypage-bff.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.json :as middleware]
            [compojure.core :refer [defroutes context GET routes]]
            [compojure.route :as route]))

(defn health [req] {:status 200
                    :headers {"Content-Type" "application/json;  charset=utf-8"
                              "Access-Control-Allow-Origin" "*"
                              "Access-Control-Allow-Methods" "POST,GET,OPTIONS"}
                    :body {:status "success"}})

(defroutes health-routes
  (GET "/health" [req] health))

(defn get-tasks [req]
  {:status 200
   :headers {"Content-Type" "application/json;  charset=utf-8"
             "Access-Control-Allow-Origin" "*"
             "Access-Control-Allow-Methods" "POST,GET,OPTIONS"}
   :body {:data [{:id "7EE6B5F1-A077-43E2-8ECD-9189BB83939B",
                  :subject "test",
                  :link "http://example.com",
                  :body "body"}]}})

(defroutes tasks-routes
  (context "/tasks" _
    (GET "/" [req] get-tasks)))

(defroutes common-routes
  (route/not-found {:status 404
                    :body {:message "not found"}}))

(def app (routes health-routes tasks-routes common-routes))
(defn -main [& _args] (jetty/run-jetty (middleware/wrap-json-response app) {:port 8080}))