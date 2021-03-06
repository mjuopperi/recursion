(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll)
       (product (rest coll)))))

(defn singleton? [coll]
  (and (not (empty? coll)) (empty? (rest coll))))

(defn my-last [coll]
  (if (empty? coll)
    nil
    (if (singleton? coll)
     (first coll)
     (my-last (rest coll)))))

(defn max-element [a-seq]
  (if (empty? a-seq)
    nil
    (if (empty? (rest a-seq))
      (first a-seq)
      (max (first a-seq) (max-element (rest a-seq))))))

(defn seq-max [seq-1 seq-2]
  (if (<= (count seq-1) (count seq-2)) seq-2 seq-1))

(defn longest-sequence [a-seq]
  (if (empty? a-seq)
    nil
    (if (empty? (rest a-seq))
      (first a-seq)
      (seq-max (first a-seq) (longest-sequence (rest a-seq))))))

(defn my-filter [pred? a-seq]
  (if (empty? a-seq)
   []
   (if (pred? (first a-seq))
    (cons (first a-seq) (my-filter pred? (rest a-seq)))
    (my-filter pred? (rest a-seq)))))

(defn sequence-contains? [elem a-seq]
  (if (empty? a-seq)
   false
   (if (= elem (first a-seq))
    true
    (sequence-contains? elem (rest a-seq)))))

(defn my-take-while [pred? a-seq]
  (if (empty? a-seq)
   []
   (if (pred? (first a-seq))
    (cons (first a-seq) (my-take-while pred? (rest a-seq)))
    [])))

(defn my-drop-while [pred? a-seq]
  (if (empty? a-seq)
   []
   (if (pred? (first a-seq))
    (my-drop-while pred? (rest a-seq))
    a-seq)))

(defn seq= [a-seq b-seq]
  (cond
    (and (empty? a-seq) (empty? b-seq)) true
    (empty? a-seq) false
    (empty? b-seq) false
    (not (= (first a-seq) (first b-seq))) false
    :else (seq= (rest a-seq) (rest b-seq))))

(defn my-map [f seq-1 seq-2]
  (if (or (empty? seq-1) (empty? seq-2))
   []
   (cons
     (f (first seq-1) (first seq-2))
     (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (if (zero? k)
   1
   (* n (power n (dec k)))))

(defn fib [n]
  (if (< n 2)
    n
    (+ (fib (- n 1)) (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (< how-many-times 1)
   []
   (cons what-to-repeat (my-repeat (dec how-many-times) what-to-repeat))))

(defn my-range [up-to]
  (if (zero? up-to)
   []
   (cons (dec up-to) (my-range (dec up-to)))))

(defn tails [a-seq]
  (if (empty? a-seq)
   [[]]
   (cons a-seq (tails ( seq (rest a-seq))))))

(defn inits [a-seq]
  (reverse (map reverse (tails (reverse a-seq)))))

(defn rotations [a-seq]
  (distinct (map concat (tails a-seq) (inits a-seq))))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq)
   freqs
   (my-frequencies-helper
    (if (contains? freqs (first a-seq))
     (update-in freqs [(first a-seq)] inc)
     (assoc freqs (first a-seq) 1))
     (rest a-seq))))

(defn my-frequencies [a-seq]
  (my-frequencies-helper {} a-seq))

(defn un-frequencies [a-map]
  (let [[k v] (first a-map)]
   (if (empty? a-map)
    []
    (concat (repeat v k) (un-frequencies (rest a-map))))))

(defn my-take [n coll]
  (if (or (zero? n) (empty? coll))
   []
   (cons (first coll) (my-take (dec n) (rest coll)))))

(defn my-drop [n coll]
  (if (or (zero? n) (empty? coll))
   coll
   (my-drop (dec n) (rest coll))))

(defn halve [a-seq]
  (let [length (count a-seq)
        half (int (/ length 2))]
   (cons
    (take half a-seq)
    (list (drop half a-seq)))))

(defn seq-merge [a-seq b-seq]
  (cond
    (empty? a-seq) b-seq
    (empty? b-seq) a-seq
    (< (first a-seq) (first b-seq)) (cons (first a-seq) (seq-merge (rest a-seq) b-seq))
    :else (cons (first b-seq) (seq-merge a-seq (rest b-seq)))))

(defn merge-sort [a-seq]
  (if (or (empty? a-seq) (empty? (rest a-seq)))
   a-seq
   (let [[a b] (halve a-seq)]
    (seq-merge (merge-sort a) (merge-sort b)))))

(defn monotonic? [a-seq]
  (or (empty? a-seq) (apply <= a-seq) (apply >= a-seq)))

(defn split-into-monotonics [a-seq]
  (if (empty? a-seq)
   []
   (let [longest (last (take-while monotonic? (inits a-seq)))]
    (cons longest (split-into-monotonics (drop (count longest) a-seq))))))

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])
