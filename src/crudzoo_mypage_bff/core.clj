(ns crudzoo-mypage-bff.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.json :as middleware]))

(defn handler [{:keys [uri]}]
  (cond
    (= uri "/health")
    {:status 200
     :headers {"Content-Type" "application/json;  charset=utf-8"
               "Access-Control-Allow-Origin" "*"
               "Access-Control-Allow-Methods" "POST,GET,OPTIONS"}
     :body {:status "success"}}
    (= uri "/worth_doing_later")
    {:status 200
     :headers {"Content-Type" "application/json;  charset=utf-8"
               "Access-Control-Allow-Origin" "*"
               "Access-Control-Allow-Methods" "POST,GET,OPTIONS"}
     :body {:data [{:subject "test",
                    :link "http://example.com",
                    :body "body"}]}}
    :else {:status 404
           :headers {"Content-Type" "text/plain; charset=utf-8"}}))
(def app (middleware/wrap-json-body {:foo "bar"}))
(defn -main [& _args] (jetty/run-jetty (middleware/wrap-json-response handler) {:port 8080}))