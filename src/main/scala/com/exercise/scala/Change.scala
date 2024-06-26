package com.exercise.scala

object Change {
  def findFewestCoins(amount: Int, denominations: List[Int]): Option[List[Int]] = {
    if (amount < 0) {
      return None
    }

    val sortedDenominations = denominations.sorted(Ordering[Int].reverse)
    var remainingAmount = amount
    var change = List[Int]()

    for (coin <- sortedDenominations) {
      while (remainingAmount >= coin) {
        change = coin :: change
        remainingAmount -= coin
      }
    }

    if (remainingAmount == 0) Some(change) else None
  }

  def main(args: Array[String]):Unit={
    val denominations = List(1,5,10,25,100)
    val amount = 15
    try{
      val change = findFewestCoins(amount, denominations)
      println(s"Change for $amount with denominations $denominations: $change")
    }catch{
      case e: IllegalArgumentException => println(e.getMessage)
    }
  }
}


