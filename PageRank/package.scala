

/**
 * @author alexandregenon
 */
package object PageRank {
  import Graph._
  type Ranking = Map[Node, Double]
  case class TeleportSet(nodes: Set[Node], weightF: Node => Double) {
    def totalSum = nodes.foldLeft(0.0)((acc,n)=>acc+weightF(n))
    def ratio(n: Node) = weightF(n) /totalSum
    def size = nodes.size
    def contains(n:Node) = nodes.contains(n)
  }

  class GenericSolver(graph: Graph, beta: Double, epsilon: Double, telSet: TeleportSet) {
    
    def iterations(curRanking: Ranking): Stream[(Ranking, Double)] = {      
      // Ranking without teleport
      def newRank(n: Node) =
        graph.inNodes(n).foldLeft(0.0)((acc, i) => acc + curRanking(i) / graph.outDegree(i))

      val newRanking = graph.nodes.map(n=> n -> beta * newRank(n) ).toMap

      val leakedRanking = 1-newRanking.foldLeft(0.0)((acc,n) => acc + n._2)
      // Adding leaked ranking by random teleport to a ref node.
      val telRanking = newRanking.map {case (n,r) => {
          if(telSet contains n) n -> (r + leakedRanking*telSet.ratio(n))
          else n->r
        }
      }
      
      // Finally, compute the total amount of change in the pageRank
      val newDelta = telRanking.foldLeft(0.0)((acc, t) => acc + Math.abs(t._2 - curRanking(t._1)))
      
      (telRanking, newDelta) #:: iterations(telRanking)
    }

    def pageRank: Ranking = {
      val initRanking: Ranking = graph.nodes.map(n => (n -> 1.0/(graph.nodes.size) ) ).toMap
      val resultStream = iterations(initRanking)
      val firstAcceptable = resultStream.dropWhile({case (n,delta)=>delta>epsilon})
      firstAcceptable.head._1
    }
  }

  class TopicSpecific(graph: Graph, beta: Double, epsilon: Double, refNodes: TeleportSet)
      extends GenericSolver(graph, beta, epsilon, refNodes) {

  }
}