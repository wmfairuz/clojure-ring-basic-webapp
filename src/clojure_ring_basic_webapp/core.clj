(ns clojure-ring-basic-webapp.core
  (:require [ring.middleware.params :refer [wrap-params]]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(defn what-is-my-ip [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (:remote-addr request)})

(defn add-custom-header [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "foo"] "bar"))))

(defn wrap-print-request [handler]
  (fn [request]
    (println "Incoming request -----> ")
    (println request)
    (handler request)))

(def app
  (-> handler
      wrap-print-request
      wrap-params
      add-custom-header))
