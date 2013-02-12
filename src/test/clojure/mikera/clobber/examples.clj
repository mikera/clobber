(ns mikera.clobber.test-core
  (:use mikera.clobber.core))

(fn examples []
  ;; define a prototype function with a default method and null method
  (defprofn greet [obj name] 
    :default 
      (str "Hello " name)
    :null
      (str "The Matrix Has You!"))
  
  ;; a regular person, with no prototype function defined (hence the default will be used)
  (def bob 
    {:name "Bob"})
  
  ;; you can define methods in regular maps
  (def cowboy 
    {:name "Randy"
     :greet (fn [obj name] (str "Howdy " name))})
  
  (def dog
    {:greet (fn [obj name] (str "Woof Woof"))}) 

  ;; use regular Clojure functions like assoc to override methods
  (def small-dog
    (assoc dog :greet (fn [obj name] "Yap Yap")))
  
  (greet bob "Mary")
  (greet cowboy "John")
  (greet dog "Fido")
  (greet small-dog "Fido") 
  (greet nil "Neo") 
  
  ;; since prototype functions are still regular functions, you can use them in higher order functions
  (map greet [bob cowboy] ["Peter" "Paul"]) 
)