(ns auto-web.components.sizes
  "Predefined sizes:

  `:icon`, `:tiny`, `:xsmall`, `:small`, `:medium`, `big`")

(defn predefined-size
  "Turn `kw` into a size in `em`.

  See [[auto-web.components.sizes]] for keywords"
  [kw]
  (if (keyword? kw)
    (-> {:icon "1em"
         :tiny "4em"
         :xsmall "7em"
         :small "10em"
         :medium "20em"
         :big "40em"}
        (get kw "10em"))
    kw))

(defn min-size
  "Minimum size linked to `kw`.

  See [[auto-web.components.sizes]] for keywords"
  [kw]
  (if (keyword? kw)
    (-> {:small "4em"
         :xsmall "2em"
         :medium "5em"
         :tiny "3em"
         :big "20em"
         :icon "0.8em"}
        (get kw "4em"))
    kw))
