object Main extends App {
  def groupTitles(strs:List[String]):Iterable[List[String]] = {
    strs.groupBy(s => s.sorted).values
  }

  //Driver code

  var titles = List("duel","dule","speed","spede","deul","cars")
  var gt = groupTitles(titles)
  var query = "spede"

  //Searching for all titles
  for (g <- gt)
    if (g.contains(query))
      print(g)

}