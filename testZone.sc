object testZone {
	def l2Norm (p1:(Int, Int), p2:(Int,Int)) ={
		def sq(a:Int, b:Int) = Math.pow((a-b),2)
		Math.sqrt(sq(p1._1,p2._1)+sq(p1._2,p2._2))
	}                                         //> l2Norm: (p1: (Int, Int), p2: (Int, Int))Double
	
	def l1Norm(p1:(Int, Int), p2:(Int,Int)) =
		Math.abs(p1._1 - p2._1) +  Math.abs(p1._2-p2._2)
                                                  //> l1Norm: (p1: (Int, Int), p2: (Int, Int))Int
	
	import genon.be.wordlib._
  val ab = WordLib.kShingles("ABRACADABRA",2)     //> ab  : Set[String] = Set(RA, AC, DA, AD, BR, AB, CA)
  val br = WordLib.kShingles("BRICABRAC",2)       //> br  : Set[String] = Set(RA, AC, BR, AB, IC, CA, RI)
  ab.size                                         //> res0: Int = 7
  br.size                                         //> res1: Int = 7
  val abbr = ab.intersect(br)                     //> abbr  : scala.collection.immutable.Set[String] = Set(RA, AC, BR, AB, CA)
  abbr.size                                       //> res2: Int = 5
  WordLib.jaccardSim(ab, br)                      //> res3: Double = 0.5555555555555556
  
  val points :Set[(Int,Int)] = Set((53,10),(54,8),(57,5),(53,18))
                                                  //> points  : Set[(Int, Int)] = Set((53,10), (54,8), (57,5), (53,18))
  for(p<-points) {
  	println("==== For point : "+p+" =====")
  	println("L1 " + l1Norm(p,(0,0)) + " - "+l1Norm(p,(100,40)))
  	println("L2 "+ l2Norm(p,(0,0))+ " - "+	l2Norm(p,(100,40)))
  }                                               //> ==== For point : (53,10) =====
                                                  //| L1 63 - 77
                                                  //| L2 53.9351462406472 - 55.758407437802596
                                                  //| ==== For point : (54,8) =====
                                                  //| L1 62 - 78
                                                  //| L2 54.589376255824725 - 56.0357029044876
                                                  //| ==== For point : (57,5) =====
                                                  //| L1 62 - 78
                                                  //| L2 57.21887800367987 - 55.44366510251645
                                                  //| ==== For point : (53,18) =====
                                                  //| L1 71 - 69
                                                  //| L2 55.97320787662612 - 51.894122981316485
  "%5d".format(24)                                //> res4: String = "   24"
  
  val l=List(2)                                   //> l  : List[Int] = List(2)
  l.reduceLeft(_+_)                               //> res5: Int = 2
  
  
}