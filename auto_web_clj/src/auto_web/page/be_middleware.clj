(ns auto-web.page.be-middleware
  "Middlewares to add in the be side to the page."
  (:require
   [ring.middleware.anti-forgery :as ring-anti-forgery]))

(defn anti-forgery-html-token
  "Add a non guessable and hidden token in the page with `id=__anti-forgery-token`"
  []
  [:div {:name "__anti-forgery-token"
         :id "__anti-forgery-token"
         :anti-forgery-token (force ring-anti-forgery/*anti-forgery-token*)
         :class ["hidden"]}])
