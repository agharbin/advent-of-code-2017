(ns adamharbin.advent.2017.3.2
  (:require [adamharbin.advent.common :refer [vector+ eight-neighbors third]]))

(defn sum-of-neighbor-costs [curr-position position->cost]
  (->> (eight-neighbors curr-position)
       (map #(get position->cost % 0))
       (apply +)))

(defn next-move [[curr-position position->cost last-cost-written]]
  (let [left-neighbor  (vector+ curr-position [0 -1])
        right-neighbor (vector+ curr-position [0 1])
        down-neighbor  (vector+ curr-position [1 0])
        up-neighbor    (vector+ curr-position [-1 0])
        curr-cell-cost (sum-of-neighbor-costs curr-position position->cost)
        next-position->cost (conj position->cost [curr-position curr-cell-cost])]
    (cond
     (and (position->cost left-neighbor) (not (position->cost up-neighbor)))
      [up-neighbor next-position->cost curr-cell-cost]
     (and (position->cost down-neighbor) (not (position->cost left-neighbor)))
      [left-neighbor next-position->cost curr-cell-cost]
     (and (position->cost right-neighbor) (not (position->cost down-neighbor)))
      [down-neighbor next-position->cost curr-cell-cost]
     (and (position->cost up-neighbor) (not (position->cost right-neighbor)))
      [right-neighbor next-position->cost curr-cell-cost])))

(def target 265149)
(def start-position [0 1])
(def start-costs {[0 0] 1})
(def start-last-cost-written 1)
(def start-state [start-position start-costs start-last-cost-written])

(->> start-state
     (iterate next-move)
     (drop-while (fn [[_ _ last]] (< last target)))
     first ; first state matching above 'drop-while' condition
     third) ; third item in the state vector, last-cost-written
