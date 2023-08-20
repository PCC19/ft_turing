(ns ft-turing.turing_machine
  (:require [ft-turing.helpers :refer :all]))

(defn turing-machine
    [step machine-spec tape state head-position]
    (cond
      (nil? state)
        (println "\nInvalid state transition in machine specification (json file) :(")
      (is_in? state (get-finals machine-spec))
        ; If in last state, exit
        (do
          (println "|" (insert-head-in-tape head-position tape (get-blank machine-spec)))
          (println "\nWE HAVE REACHER AN FINAL STATE, BYE BYE! :)"))
      ; Else, update vars and execute machine
      :else
      (let
         [state-transitions (get (get machine-spec "transitions") state nil)
             read-char      (get-char head-position tape) 
             next-state     (query-st read-char state "to_state" state-transitions)
             char-to-write  (query-st read-char state "write" state-transitions)
             move           (query-st read-char state "action" state-transitions)
           written-tape     (write-new-char-in-tape tape head-position char-to-write) 
           new-head-position (update-head-position move head-position)
           new-tape         (append-blank written-tape new-head-position (get-blank machine-spec))
           new-step         (inc step)]
          (println "|" (insert-head-in-tape head-position tape (get-blank machine-spec)) "|" "Step: " step "\t(" state "," read-char ") --> \t\t(" next-state "," char-to-write "," move ")")
          (recur new-step machine-spec new-tape next-state new-head-position))))
