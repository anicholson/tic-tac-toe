(ns tic-tac-toe.app
  (:use [tic-tac-toe.core :as core])
  (:require [clojure.string :as string])
  (:gen-class))

(defn try-times* [thunk times]
  (let [res
        (first
         (drop-while #{::fail}
                     (repeatedly times
                                 #(try (thunk)
                                       (catch Throwable _ ::fail)))))]
    (when-not (= ::fail res)
      res)))

(defn read-move-entry []
  (Math/abs
   (Integer/parseInt
    (read-line))))

(defn read-move [board]
  (loop [square (try-times* read-move-entry 100)]
    (cond
      (= core/EMPTY (board square)) square
      :else
      (recur (read-move-entry))))
)


(defn print-winner [player board]
  (print player)
  (println " wins!")
  (core/print-board board))

(defn print-draw [board]
  (println "Game drawn.")
  (core/print-board board))

(defn -main [& args]
  (println "Let's play tic-tac-toe")

  (loop [board (core/empty-board) player (core/start-player)]
    (print player)
    (println " to play.")
    (core/print-board board)

    (let [move (read-move board)]
      (let [
             new-board   (core/move board player move)
             next-player (core/next-player player)
           ]
        (cond
          (core/has-won? new-board player)
            (print-winner player new-board)
          (core/draw? new-board)
            (print-draw new-board)
          :else
          (recur new-board next-player)))))
  )
