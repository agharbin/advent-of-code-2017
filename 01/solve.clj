(ns advent.2017.01
  (:require
    [clojure.string :as s]))

(defn parse-input [input]
  (->> (s/split (s/trim input) #"")
       (map parse-long)
       vec))

(defn solve-1 [input]
  (let [final-input (conj input (first input))]
    (->> (partition 2 1 final-input)
         (map (fn [[x y]] (if (= x y) x 0)))
         (apply +))))

(defn solve-2 [input]
  (let [size (count input)
        offset (/ size 2)]
    (apply +
      (for [x-index (range size)]
        (let [x (input x-index)
              y-index (mod (+ x-index offset) size)
              y (input y-index)]
          (if (= x y) x 0))))))

(defn solve-file [input]
  (->> (slurp input)
       parse-input
       solve-2
       prn))

(solve-file "input.dat")
