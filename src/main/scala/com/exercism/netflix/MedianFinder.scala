package com.exercism.netflix

import scala.collection.mutable

object MedianSolution extends App {
  case class MedianFinder() {
    val maxHeap = new mutable.PriorityQueue[Int]()
    val minHeap = new mutable.PriorityQueue[Int]()(Ordering.Int.reverse)

    def insertAge(num: Int): Unit = {
      if (maxHeap.isEmpty || num <= maxHeap.head) {
        maxHeap += num
      } else {
        minHeap += num
      }

      // Balance the heaps
      if (maxHeap.size > minHeap.size + 1) {
        minHeap += maxHeap.dequeue()
      } else if (minHeap.size > maxHeap.size) {
        maxHeap += minHeap.dequeue()
      }
    }

    def findMedian(): Double = {
      if (maxHeap.size > minHeap.size) {
        maxHeap.head.toDouble
      } else if (maxHeap.size < minHeap.size) {
        minHeap.head.toDouble
      } else {
        (maxHeap.head.toDouble + minHeap.head.toDouble) / 2.0
      }
    }
  }

  var medianAge = MedianFinder()
  medianAge.insertAge(22)
  medianAge.insertAge(35)
  print("The recommended content will be for ages under: ")
  println(medianAge.findMedian())
  medianAge.insertAge(30)
  print("The recommended content will be for ages under: ")
  println(medianAge.findMedian())
  medianAge.insertAge(25)
  print("The recommended content will be for ages under: ")
  println(medianAge.findMedian())
}
// 22, 25, 30, 35
//55/2 = 27.5

//largest    smallest
//{22, 25}  {30,35}
//maxHeap    minHeap
//25        30


//Steps:
//maxHeap       minHeap
//  3            null
//  1
//
//  1              3
//       mid = 2
//
//  3                5
//  1
//
//  3                4
//  1                5
//
//      3+4/2 = 3.5