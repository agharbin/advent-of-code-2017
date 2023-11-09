(ns adamharbin.advent.2017.3.1)

(defn containing-square [x]
  (->> (range)
       (filter odd?)
       (map #(* % %))
       (drop-while #(< % x))
       first))

(def target 265149)

(defn square [x] (* x x))

(let [containing-square-width (long (Math/sqrt (containing-square target)))
      contained-square-width (- containing-square-width 2)
      first-num-in-edge (inc (square contained-square-width))
      min-dist (quot containing-square-width 2)
      max-dist (* 2 min-dist)
      start-dist (dec max-dist)
      dists (concat
              (range start-dist min-dist -1)
              (range min-dist max-dist)
              (range max-dist min-dist -1)
              (range min-dist max-dist)
              (range max-dist min-dist -1)
              (range min-dist max-dist)
              (range max-dist min-dist -1)
              (range min-dist max-dist))
      steps-from-first-num-in-edge (- target first-num-in-edge)]
  (nth dists steps-from-first-num-in-edge))
