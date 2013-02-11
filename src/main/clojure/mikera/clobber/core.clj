(ns mikera.clobber.core
  (:use [mikera.cljutils error]))

(defn- build-default-handler [name obj]
  `(error "Can't find implementation for method [" ~name "] on object: " ~obj))

(defn- build-null-handler [name]
  `(error "Method [" ~name "] called on null object, no null handler available "))

(defmacro defprofn 
  "Defines a prototype function with the specified name and argument list.
   The first argument must be the target object."
  ([name arglist & {:keys [default null use-key]}]
	  (when-not (and (vector? arglist) (>= 1 (count arglist))) 
	    (error "Prototype function requires a vector of arguments"))
	  (when-not (every? symbol? arglist) 
	    (error "Prototype function requires an arg list containing at least one argument (the object)"))
	  (let [arglist (map #(symbol nil (clojure.core/name %)) arglist)
          [obj & args] arglist
	        method-key (or use-key (keyword nil (clojure.core/name name)))
	        default (or default (build-default-handler name obj))
	        null (or null (build-null-handler name))]
	    (println "Defining with keyword - " (str method-key))
	    `(defn ~name [~@arglist]
	       (if (nil? ~obj)
	           ~null
	           (if-let [method# (~method-key ~obj)]
	             (method# ~obj ~@args)
	             ~default))))))
