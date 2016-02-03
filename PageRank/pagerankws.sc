package PageRank


object pagerankws {
  import PageRank._
  import Graph._
  
  object myGraph extends Graph{
  	override def nodes = List(1,2,3,4) map(Node(_)) toStream
  	private def listToInEdges(n: Node, l:List[Int]) = l map(i => Edge(Node(i),n)) toStream
  	override def inEdges(n:Node) =
  		n.id match {
  			case 1 => listToInEdges(n,List(2))
  			case 2 => listToInEdges(n,List(1))
  			case 3 => listToInEdges(n,List(1,4))
  			case 4 => listToInEdges(n,List(3))
  		}
  	def edges = nodes flatMap(n=>inEdges(n)) toSet
  	override def outEdges(n:Node) = edges filter(e=>e.source == n) toStream
  }

  def weightF(n:Node) = {
  	if (n.id == 1) 2
  	else if (n.id==2) 1
  	else 0
  }                                               //> weightF: (n: Graph.Node)Int
  
  val solver = new TopicSpecific(myGraph,0.7,0.0001,TeleportSet(Set(1,2) map (Node(_)), weightF))
                                                  //> solver  : PageRank.TopicSpecific = PageRank.package$TopicSpecific@53c5472f
  
  val solution=solver.pageRank                    //> solution  : PageRank.Ranking = Map(Node(1) -> 0.35761590758580175, Node(2) -
                                                  //| > 0.22516553356409652, Node(3) -> 0.24540627391048925, Node(4) -> 0.17181228
                                                  //| 493961256)
  println("Results")                              //> Results
  solution.foreach({case (n,r) => println("%d : %.4f".format(n.id,r))})
                                                  //> 1 : 0.3576
                                                  //| 2 : 0.2252
                                                  //| 3 : 0.2454
                                                  //| 4 : 0.1718
 
  
 }