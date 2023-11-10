(ns adamharbin.advent.2017.4
  (:require [clojure.string :as s]))

(defn parse-line [input]
  (map sort (s/split input #" ")))

(defn parse-input [input]
  (->> input
       s/split-lines
       (map parse-line)))

(defn solve [input]
  (->> input
       (map #(= (count %) (count (set %))))
       (filter true?)
       count))

(def raw-input (slurp "input.dat"))
(def parsed-input (parse-input raw-input))
(solve parsed-input)
