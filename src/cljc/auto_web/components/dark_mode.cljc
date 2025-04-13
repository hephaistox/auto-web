(ns auto-web.components.dark-mode)

;; "function toggleDarkMode() {
;;     document.body.classList.toggle(\"w3-dark-grey\");
;;     document.body.classList.toggle(\"w3-text-white\");
;;   }"

(defn indicator
  "Show an indicator based on the dark / light mode

  Is based on actual value seen by css @media."
  [opts]
  [[:div.hidden-light opts [:i.fa.fa-moon-o.aria-hidden]]
   [:div.hidden-dark opts [:i.fa.fa-sun-o.aria-hidden]]])
