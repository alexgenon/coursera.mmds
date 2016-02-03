package WordSim

object week7AQ2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val l = List("abcef", "acdeg", "bcdefg", "adfg", "bcdfgh", "bceg", "cdfg", "abcd")
                                                  //> l  : List[String] = List(abcef, acdeg, bcdefg, adfg, bcdfgh, bceg, cdfg, abc
                                                  //| d)
  val buckets = new WordSim(0.8)                  //> buckets  : WordSim.WordSim = WordSim.package$WordSim@50480174
  for (s <- l)
    buckets index (s)

	buckets.buckets                           //> res0: scala.collection.immutable.Map[Char,Set[String]] = Map(a -> Set(abcef,
                                                  //|  acdeg, adfg, abcd), b -> Set(abcef, bcdefg, bcdfgh, bceg), c -> Set(acdeg, 
                                                  //| bcdefg, bcdfgh, cdfg))
  buckets.findSimilar(l(0)).size-1                //> res1: Int = 6
  buckets.findSimilar(l(2)).size  -1              //> res2: Int = 5
  buckets.findSimilar(l(5)).size -1               //> res3: Int = 3
}