package com.exercise.scala

object PalindromeChecker {

  def isPalindrome(word: String): Boolean={
    val lower = word.toLowerCase
    lower.reverse == lower
  }

  def main(args: Array[String]):Unit={
    println(isPalindrome(("madam")))
  }
}
