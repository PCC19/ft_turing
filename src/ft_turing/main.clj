(ns ft-turing.main
  (:require 
    [ft-turing.helpers :refer [return get-file get-blank get-alphabet get-initial get-finals is-error? success-validation]]
    [ft-turing.validations :refer [json-was-found? json-is-valid? tape-is-valid?]]
    [ft-turing.turing_machine :refer [turing-machine]]
    ))

(defn- validation_ok? [machine-spec tape]
  (if (and
          (json-was-found? machine-spec) 
          (json-is-valid? machine-spec) 
          (tape-is-valid? tape machine-spec))
      (return true) (return false) ))

(defn- start-turing-machine
  [machine-spec tape]
  (println "\n\n\n-=-=-=-=-=-=-=-=-START TURING MACHINE-=-=-=-=-=-=-=-=-")
  (let
    [step 0
     head-position 0
     state (get-initial machine-spec)]
    (turing-machine step machine-spec tape state head-position)))

(defn main
  [& args]
  (if (not (= (count args) 2))
    (println "usage: lein run [machine-spec.json] [tape]")
  (do
    ((println "\n-=-=-=-=-=-=-=-=-TURING MACHINE-=-=-=-=-=-=-=-=-\n")
      (let [path-json (first args) 
            tape (second args) 
            machine-spec (get-file path-json)]
        (if (validation_ok? machine-spec tape)
                (start-turing-machine machine-spec tape)
                (println "Finishing the Turing Machine! :("))))))
)
