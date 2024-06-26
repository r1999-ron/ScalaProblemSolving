package com.exercise.scala

object Fibonacci extends App{

  def fibonacci(n: Int):Int={
    @annotation.tailrec
    def loop(n: Int, prev: Int, curr: Int):Int={
      if(n==0) prev
      else loop(n-1, curr, prev+curr)
    }
    loop(n,0,1)
  }

  println(fibonacci(5))
  println(fibonacci(10))

}
