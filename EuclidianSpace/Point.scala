package EuclidianSpace

/**
 * @author alexandregenon
 */
case class Point(coords: Vector[Double], label:String) {
  def applyOp(op: (Double, Double) => Double)(that: Point) = 
    Point(coords.zipWithIndex.map { case (e, i) => op(e, that.coords(i)) }, "")
  def - = applyOp(_ - _) _
  def + = applyOp(_ + _) _
  def dotDiv (that: Double) = Point(coords.map(e=>e/that),"")
  def apply(value: Int) = coords(value)
  def norm(L: Int) = {
   val sum = coords.foldLeft(0.0)((acc, coord) => acc + Math.abs(Math.pow(coord, L)))
   Math.pow(sum,1.0/L)
  }

  override def toString = label + coords.mkString("[",",","]")
}

object Point {
  def apply(coordsDb: Double*) = new Point(coordsDb.toVector,"")
  def apply(label: String, coordsDb: Double*) = new Point(coordsDb.toVector, label)
  def dist(a: Point, b: Point) = (a - b) norm (2)
}