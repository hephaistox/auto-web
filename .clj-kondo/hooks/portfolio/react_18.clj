(ns hooks.portfolio.react-18
  (:require
   [clj-kondo.hooks-api :as api]))

(defn defscene-hook
  [{:keys [node]}]
  (let [[_defscene name & more] (:children node)
        forms more
        first-form (first forms)]
    (cond
      (and (api/string-node? first-form) (api/keyword-node? (second forms)))
      (let [[docstring _params _params-value fn-params & body] forms
            body-node (api/list-node (cons (api/token-node 'do) body))
            def-node (api/list-node [(api/token-node 'defn) name docstring fn-params body-node])]
        {:node def-node})
      (api/string-node? first-form)
      (let [[docstring & body] forms
            body-node (api/list-node (cons (api/token-node 'do) body))
            def-node (api/list-node [(api/token-node 'def) name docstring body-node])]
        {:node def-node})
      :else (let [body-node (api/list-node (cons (api/token-node 'do) forms))
                  def-node (api/list-node [(api/token-node 'def) name body-node])]
              {:node def-node}))))

