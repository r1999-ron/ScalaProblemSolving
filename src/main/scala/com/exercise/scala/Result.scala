import scala.io.Source

case class Order(productName: String, quantity: Int, brand: String)

object OrderProcessor {
  def parseOrders(inputFileName: String): List[Order] = {
    val bufferedSource = Source.fromFile(inputFileName)
    val orders = bufferedSource.getLines().map { line =>
      val Array(_, _, productName, quantityStr, brand) = line.split(", ").map(_.trim)
      Order(productName, quantityStr.toInt, brand)
    }.toList
    bufferedSource.close()
    orders
  }

  def calculateAverageQuantities(orders: List[Order]): List[String] = {
    val productQuantities = orders.groupBy(_.productName).mapValues { productOrders =>
      val totalQuantity = productOrders.map(_.quantity).sum
      val totalOrders = productOrders.size
      totalQuantity.toDouble / totalOrders
    }
    productQuantities.toList.map { case (productName, avgQuantity) =>
      s"$productName,$avgQuantity"
    }
  }

  def calculatePopularBrands(orders: List[Order]): List[String] = {
    val productBrands = orders.groupBy(_.productName).mapValues { productOrders =>
      productOrders.groupBy(_.brand).maxBy(_._2.size)._1
    }
    productBrands.toList.map { case (productName, popularBrand) =>
      s"$productName,$popularBrand"
    }
  }

  def generateReports(inputFileName: String): Unit = {
    val orders = parseOrders(inputFileName)

    val avgQuantityData = calculateAverageQuantities(orders)
    println("Average Quantity per Order:")
    avgQuantityData.foreach(println)

    val popularBrandData = calculatePopularBrands(orders)
    println("\nMost Popular Brand:")
    popularBrandData.foreach(println)
  }
}

object OrderReportApp extends App {
  val inputFileName = "/Users/ronak/Downloads/ScalaTraining-Linkedin/src/main/scala/com/exercise/scala/orders.csv"
  println(s"The input file is: $inputFileName")

  OrderProcessor.generateReports(inputFileName)
}