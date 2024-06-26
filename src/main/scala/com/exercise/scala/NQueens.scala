package com.exercise.scala

import scala.annotation.tailrec

object NQueens extends App {

  def nQueens(n: Int): List[List[Int]] = {
    // Check if a position conflicts with existing queens
    def conflict(position: Int, queens: List[Int]): Boolean = {
      def conflictOneQueen(position: Int, queen: Int, index: Int): Boolean =
        queen == position || (index + 1) == (position - queen).abs

      queens.zipWithIndex.exists { case (queen, index) =>
        conflictOneQueen(position, queen, index)
      }
    }

    @tailrec
    def nQueensTailrec(currentPosition: Int, currentQueens: List[Int], solutions: List[List[Int]]): List[List[Int]] = {
      if (currentPosition >= n && currentQueens.isEmpty) solutions
      else if (currentPosition >= n) {
        nQueensTailrec(currentQueens.head + 1, currentQueens.tail, solutions)
      } else if (conflict(currentPosition, currentQueens)) {
        nQueensTailrec(currentPosition + 1, currentQueens, solutions)
      } else if (currentQueens.length == n - 1) {
        val newSolution = currentPosition :: currentQueens
        nQueensTailrec(currentQueens.head + 1, currentQueens.tail, newSolution :: solutions)
      } else {
        nQueensTailrec(0, currentPosition :: currentQueens, solutions)
      }
    }

    nQueensTailrec(0, List(), List())
  }

  // Example usage
  val solutions = nQueens(4)
  solutions.foreach(solution => println(solution.reverse.mkString(", ")))
}