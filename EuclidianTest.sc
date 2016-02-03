object EuclidianTest {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  import EuclidianSpace._
  import scala.collection.breakOut
  
  val c1 = Point(5,10)                            //> c1  : EuclidianSpace.Point = [5.0,10.0]
 	val c2 = Point(20,5)                      //> c2  : EuclidianSpace.Point = [20.0,5.0]
 	
 	val c = Clustering(c1,c2)                 //> c  : EuclidianSpace.Clustering.Clustering = [5.0,10.0] -> List([5.0,10.0])
                                                  //| [20.0,5.0] -> List([20.0,5.0])
 	
 	// Answer 1
 	val r1UL = Point(3,3)                     //> r1UL  : EuclidianSpace.Point = [3.0,3.0]
 	val r1LR = Point(10,1)                    //> r1LR  : EuclidianSpace.Point = [10.0,1.0]
 	val r2UL = Point(15,14)                   //> r2UL  : EuclidianSpace.Point = [15.0,14.0]
 	val r2LR = Point(20,10)                   //> r2LR  : EuclidianSpace.Point = [20.0,10.0]
	
	// Answer 2
 	/*val r1UL = Point(3,3)
 	val r1LR = Point(10,1)
 	val r2UL = Point(13,10)
 	val r2LR = Point(16,4)*/
 	
 	// Answer 3
 	/*val r1UL = Point(6,15)
 	val r1LR = Point(13,7)
 	val r2UL = Point(16,19)
 	val r2LR = Point(25,12)*/
	
	// Answer 4
 	/*val r1UL = Point(6,7)
 	val r1LR = Point(11,4)
 	val r2UL = Point(11,5)
 	val r2LR = Point(17,2)*/
	
	val r1X = List(r1UL,r1LR,Point(r1UL(0),r1LR(1)),Point(r1LR(0),r1UL(1)))
                                                  //> r1X  : List[EuclidianSpace.Point] = List([3.0,3.0], [10.0,1.0], [3.0,1.0], [
                                                  //| 10.0,3.0])
	val r2X = List(r2UL,r2LR,Point(r2UL(0),r2LR(1)),Point(r2LR(0),r2UL(1)))
                                                  //> r2X  : List[EuclidianSpace.Point] = List([15.0,14.0], [20.0,10.0], [15.0,10.
                                                  //| 0], [20.0,14.0])
	c.findClosest(r1UL)                       //> res0: (EuclidianSpace.Clustering.Centroid, List[EuclidianSpace.Point]) = ([5
                                                  //| .0,10.0],List([5.0,10.0]))
	
	val r1S:Set[Point] = r1X.map(p => c.findClosest(p)._1)(breakOut)
                                                  //> r1S  : Set[EuclidianSpace.Point] = Set([5.0,10.0])
	val r2S:Set[Point] = r2X.map(p => c.findClosest(p)._1)(breakOut)
                                                  //> r2S  : Set[EuclidianSpace.Point] = Set([20.0,5.0])
}