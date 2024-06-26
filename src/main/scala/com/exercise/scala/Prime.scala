object Prime extends App{
  def isPrime(n: Int):Boolean={
    if(n <=1) false
    else if(n == 2) true
    else !(2 to Math.sqrt(n).toInt).exists(n % _==0)
  }

  println(isPrime(7))
  println(isPrime(10))
}