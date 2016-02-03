package EuclidianSpace
import Point._
import scala.collection.breakOut

/**
 * @author alexandregenon
 */
object Clustering {
  type Centroid = Point
  type Clustroid = Point

  case class Clustering(clusters: Map[Centroid, List[Point]]) {
    def findClosest(p: Point) = clusters.minBy(c => Point.dist(p,c._1))
    def centroids = clusters.map(c => c._1)

    def addPoint(p: Point): Clustering = {
      val closestCentroid = findClosest(p)
      val updatedClusters = clusters.updated(closestCentroid._1, p :: closestCentroid._2)
      Clustering(updatedClusters)
    }

    def addPoints(ps: List[Point]): Clustering = {
        ps match {
          case p :: tail => addPoint(p).addPoints(tail)
          case List()    => this
      }
    }
   
   def average(points: List[Point]):Point = 
     points.reduce((a,b) => a+b).dotDiv(points.size)
     
    
   def recomputeCentroids = {
     val newCluster = clusters.map(c => (
       average(c._2),
       c._2
     ))
     Clustering(newCluster)
   }
   
   // Cluster containing only the centroids - useful for next pass in k-mean e.g.
   def resetMembers = Clustering(clusters.map(c=>(c._1,List(c._1))))
   
   override def toString = clusters.mkString("\n")
  }

  def apply(centroids: Point*) = Clustering(centroids.map(p => (p, List(p)))(breakOut))
}