

/**
 * @author alexandregenon
 */
package object Graph {
  case class GraphLabel(label:String)
  object noLabel extends GraphLabel("")
  case class Node(id:Int)
  case class Edge(source: Node, dest: Node)
  
  abstract class Graph(){
    def nodes: Stream[Node]
    def inEdges(n:Node): Stream[Edge]
    def inNodes(n:Node): Stream[Node] = inEdges(n) map (e => e.source)
    def inDegree(n:Node): Int = inEdges(n).size
    
    def outEdges(n:Node):Stream[Edge]
    def outNodes(n:Node): Stream[Node] = outEdges(n) map (e=>e.dest) 
    def outDegree(n:Node): Int = outEdges(n).size
  }
}