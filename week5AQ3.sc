object week5AQ3 {
 	import EuclidianSpace._
  import scala.collection.breakOut
  
  def furthestPoint(points: Set[Point], reps: Set[Point]) = points.maxBy(p => {
		reps map(r => (Point.dist(p,r))) min
	})                                        //> furthestPoint: (points: Set[EuclidianSpace.Point], reps: Set[EuclidianSpace.
                                                  //| Point])EuclidianSpace.Point
  
  def recAddPoints(points:Set[Point], reps:Set[Point],step:Int=1, log: String=""):(Set[Point],String) =
  	if(points.isEmpty)
  		(reps,log+"\nDone!")
  	else{
  		val newRep = furthestPoint(points, reps)
  		recAddPoints(points - newRep, reps + newRep,step+1,log+"\n(%d) Added ".format(step)+newRep)
  	}                                         //> recAddPoints: (points: Set[EuclidianSpace.Point], reps: Set[EuclidianSpace.P
                                                  //| oint], step: Int, log: String)(Set[EuclidianSpace.Point], String)
  
 
  val pointsMap=Map('x' -> Point (0,0),'y' -> Point (10,10), 'a' ->Point (1,6), 'b' ->Point (3,7), 'c' ->Point (4,3), 'd' ->Point (7,7), 'e' ->Point (8,2), 'f' -> Point(9,5))
                                                  //> pointsMap  : scala.collection.immutable.Map[Char,EuclidianSpace.Point] = Map
                                                  //| (e -> [8.0,2.0], x -> [0.0,0.0], y -> [10.0,10.0], f -> [9.0,5.0], a -> [1.0
                                                  //| ,6.0], b -> [3.0,7.0], c -> [4.0,3.0], d -> [7.0,7.0])
  val points:Set[Point] = pointsMap.map(t => Point(t._2.coords,t._1.toString))(breakOut)
                                                  //> points  : Set[EuclidianSpace.Point] = Set(c[4.0,3.0], b[3.0,7.0], f[9.0,5.0]
                                                  //| , y[10.0,10.0], x[0.0,0.0], d[7.0,7.0], a[1.0,6.0], e[8.0,2.0])
	
	val reps=points.filter { p => p.label=="x" || p.label=="y" }
                                                  //> reps  : scala.collection.immutable.Set[EuclidianSpace.Point] = Set(y[10.0,10
                                                  //| .0], x[0.0,0.0])
	
	furthestPoint(points, reps)               //> res0: EuclidianSpace.Point = e[8.0,2.0]
	
	recAddPoints(points, reps, 1,"Starting")  //> res1: (Set[EuclidianSpace.Point], String) = (Set(c[4.0,3.0], b[3.0,7.0], f[9
                                                  //| .0,5.0], y[10.0,10.0], x[0.0,0.0], d[7.0,7.0], a[1.0,6.0], e[8.0,2.0]),Start
                                                  //| ing
                                                  //| (1) Added e[8.0,2.0]
                                                  //| (2) Added b[3.0,7.0]
                                                  //| (3) Added c[4.0,3.0]
                                                  //| (4) Added d[7.0,7.0]
                                                  //| (5) Added f[9.0,5.0]
                                                  //| (6) Added a[1.0,6.0]
                                                  //| (7) Added y[10.0,10.0]
                                                  //| (8) Added x[0.0,0.0]
                                                  //| Done!)
}