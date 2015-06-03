(ns tic-tac-toe.core)

(def EMPTY '- )
(def ONE  'x)
(def TWO  'o)

(defn start-player [] ONE )

(defn next-player [player]
  (cond
    (= player ONE) TWO
    (= player TWO) ONE

    ))

(defn empty-board []
  "Create an empty board"
  [
   EMPTY EMPTY EMPTY
   EMPTY EMPTY EMPTY
   EMPTY EMPTY EMPTY
   ]
  )

(defn move [board player square]
  "Make a move on the board"
    (assoc board square player)
  )


(defn empty-square? [square]
  (= EMPTY square))

(defn occupied? [square]
  (not (empty? square)))

(defn has-won? [board player]
  (let [
        winning-positions '([0 1 2] [3 4 5] [6 7 8] [0 3 6] [1 4 7] [2 5 8] [0 4 8] [2 4 6])
        player-wins? (fn [position] (every? (fn [sq] (= (board sq) player)) position))
       ]
    (true? (some player-wins? winning-positions)
           )))

(defn full-board? [board]
  (true? (some empty-square? board)))

(defn draw? [board]
  (and
   (full-board? board)
   (not (or (has-won? board ONE)
        (not (has-won? board TWO)
            ) ))))

(defn unfinished-game? [board]
  (not
   (or
    (full-board? board)
    (has-won? ONE)
    (has-won? TWO)
    )))
