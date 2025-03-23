(ns auto-web.components.v-copyright "Display a copyright")

(defn copyright-str
  "String with current year Hephaistox copyright"
  []
  (str "Hephaistox © "
       #?(:clj (.getYear (java.time.LocalDateTime/now))
          :cljs (.getFullYear (js/Date.)))))
