(ns auto-web.portfolio
  (:require
   [auto-web.portfolio.badge-cards]
   [auto-web.portfolio.button-cards]
   [auto-web.portfolio.img-cards]
   [auto-web.portfolio.lang]
   [auto-web.portfolio.link-cards]
   [auto-web.portfolio.list-cards]
   [auto-web.portfolio.menu-cards]
   [auto-web.portfolio.people-cards]
   [auto-web.portfolio.selector-cards]
   [auto-web.portfolio.size-cards]
   [portfolio.ui :as ui]))

::auto-web.portfolio.people-cards/keep
::auto-web.portfolio.badge-cards/keep
::auto-web.portfolio.button-cards/keep
::auto-web.portfolio.img-cards/keep
::auto-web.portfolio.lang/keep
::auto-web.portfolio.link-cards/keep
::auto-web.portfolio.list-cards/keep
::auto-web.portfolio.menu-cards/keep
::auto-web.portfolio.selector-cards/keep
::auto-web.portfolio.size-cards/keep

(ui/start! {:config {:css-paths ["/css/w3_schools.css"
                                 "/css/w3_colors_flat.css"
                                 "/fontawesome/css/fontawesome.css"
                                 "/fontawesome/css/brands.css"
                                 "/fontawesome/css/solid.css"
                                 "/css/components.css"]}})

(defn init [])
