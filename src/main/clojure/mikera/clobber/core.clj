(ns mikera.clobber.core
  (:use [mikera.cljutils error]))

(defmacro defprofn 
  [name args & {:keys [default null]}]
  (when-not (and (vector? args) (>= 1 (count args))) 
    (error "Prototype function requires a vector of arguments"))
  (when-not (every? symbol? args) 
    (error "Prototype function requires an arg list containing at least one argument (the object)"))
  (let [method-key (keyword name)]
    `(defn ~name [~@args]
       (if-let [method# (~method-key ~(first args))]
         (method# ~@(rest args))
         ~default))))
