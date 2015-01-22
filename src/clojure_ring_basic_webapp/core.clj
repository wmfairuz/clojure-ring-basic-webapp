(ns clojure-ring-basic-webapp.core)

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

(def app
  (-> handler
      add-custom-header))
