(ns pegasus.core-test
  (:require [clojure.test :refer :all]
            [pegasus.core :refer :all]))

(def default-board (remove-peg (new-board 5) 4))

(deftest test-triangular?
  (testing "triangular?"
    (is (= true (triangular? 6)))
    (is (= false (triangular? 5)))))

(deftest test-row-tri
  (testing "row-tri"
    (is (= 6 (row-tri 3)))
    (is (= 10 (row-tri 4)))))

(deftest test-row-num
  (testing "row-num"
    (is (= 1 (row-num 1)))
    (is (= 2 (row-num 3)))
    (is (= 5 (row-num 12)))))

(deftest test-new-board
  (testing "new-board"
    (let [expected {:rows 3,
                    1 {:pegged true, :connections {4 2, 6 3}},
                    4 {:connections {1 2, 6 5}, :pegged true},
                    6 {:connections {1 3, 4 5}, :pegged true},
                    2 {:pegged true},
                    3 {:pegged true},
                    5 {:pegged true}}]
      (is (= expected (new-board 3))))))

(deftest test-valid-moves
  (testing "valid-moves"
      (is (= {4 2} (valid-moves default-board 1)))))

(deftest test-make-move
  (testing "make-move"
    (let [board (remove-peg (new-board 3) 1)
          expected {:rows 3,
                    1 {:pegged true, :connections {4 2, 6 3}},
                    4 {:connections {1 2, 6 5}, :pegged true},
                    6 {:connections {1 3, 4 5}, :pegged false},
                    2 {:pegged true},
                    3 {:pegged false},
                    5 {:pegged true}}]
      (is (= expected (make-move board 6 1)))
      (is (= nil (make-move board 6 2))))))

(deftest test-can-move?
  (testing "can-move?"
    (is (= {4 2} (can-move? default-board)))
    (is (= nil (can-move? (new-board 5))))))
