(ns ft-turing.helpers
  (:require [clojure.data.json :as json]))




;; =================== General helpers =================================
(defn return
	[value]
	((constantly value)))

(defn get-file 
  "captura conteudo do arquivo passado como parametro"
  [path]
  (try (json/read-str (slurp path)) (catch Exception e (return nil))))

;; =================== Validation helpers ==============================
(defn get-name
  "captura o campo name do mapa ou retorna nil caso não encontrar"
  [machine-spec]
  (get machine-spec "name" nil))

(defn get-alphabet
  "captura o campo alphabet do mapa ou retorna nil caso não encontrar"
  [machine-spec]
  (get machine-spec "alphabet" nil))

(defn get-blank
  "captura o campo alphabet do mapa ou retorna nil caso não encontrar"
  [machine-spec]
  (get machine-spec "blank" nil))

(defn get-states
  "captura o campo states do mapa ou retorna nil caso não encontrar"
  [machine-spec]
  (get machine-spec "states" nil))

(defn get-initial
  "captura o campo initial do mapa ou retorna nil caso não encontrar"
  [machine-spec]
  (get machine-spec "initial" nil))

(defn get-finals
  "captura o campo finals do mapa ou retorna nil caso não encontrar"
  [machine-spec]
  (get machine-spec "finals" nil))

(defn get-transitions
  "captura o campo transitions do mapa ou retorna nil caso não encontrar"
  [machine-spec]
  (get machine-spec "transitions" nil))

(defn is-error?
	[boolean message-error]
	(if (not boolean) (println (str "ERROR " "(" message-error ")" ) ))
	(return boolean))

(defn success-validation
	[]
  (println "OK")
  (return true))

;; =================== Turing helpers ==============================
(defn insert-head-in-tape
  "apresenta na string em qual posição o cabeçote da maquina de turing esta"
  [head-position tape blank]
  (let [tape-size (count tape) position (if (< head-position 0) (return 0) (if (>= head-position tape-size) (return tape-size) (return head-position)))]
    (str 
      (if (< position 0) ("") (subs tape 0 position))
      (if (and (>= position 0) (< position tape-size)) (str "\033[0;31m[\033[1;33m\033[41m" (subs tape position (+ position 1)) "\033[0;31m]\033[0m"))
      (if (>= position tape-size) (str "[" blank "]") (subs tape (+ position 1) tape-size)))))

(defn update-head-position
  [move position]
    (cond 
      (= move "RIGHT") (return (+ position 1))
      (= move "LEFT") (return (max 0 (- position 1)))
      (and (not (nil? move)) (not (= move "LEFT")) (not (= move "RIGHT"))) (return position)
      :else (return nil)))

(defn write-new-char-in-tape
  [tape head-position new-char]
  (str (subs tape 0 head-position) new-char (subs tape (+ head-position 1) (count tape))))

(defn get-from-char
  [char transition-list]
  (if (nil? transition-list) (return []) (vec (filter #(= char (get % "read" nil)) transition-list))))

(defn query-st
  [char state field state-transitions]
  (get (get (get-from-char char state-transitions) 0) field nil))

(defn get-char
  [head-position tape]
  (subs tape head-position (+ head-position 1)))

(defn is_in?
  "true if coll contains elm"
  [elm coll]
  (some #(= elm %) coll))

(defn append-blank
  [tape pos blank]
  (if (= pos (count tape))
    (str tape blank)
    tape))
