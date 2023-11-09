(ns adamharbin.advent.2017.2
  (:require
    [clojure.repl :as repl]
    [clojure.string :as s]))

(defn parse-line [input]
  (mapv parse-long (s/split input #"\t")))

(defn parse-input [input]
  (->> input
       s/split-lines
       (mapv parse-line)))

(defn solve-1 [input]
  (->> input
       (map (fn [rw] (- (apply max rw) (apply min rw))))
       (apply +)))

(defn whole-number-quotient [values]
  (->> (for [x values y values :when (> x y)] [x y])
       (filter (fn [[x y]] (zero? (rem x y))))
       (map (fn [[x y]] (/ x y)))
       first))

(defn solve-2 [input]
  (->> input
       (map whole-number-quotient)
       (apply +)))

(defn solve-file [input]
  (->> (slurp input)
       parse-input
       solve-2
       prn))

(solve-file "input.dat")
