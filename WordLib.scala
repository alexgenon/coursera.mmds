package genon.be.wordlib
/**
 * @author alexandregenon
 */
object WordLib {
   def kShingles (word: String, k: Int): Set[String] = word.sliding(k).toSet
   def jaccardSim(a: Set[String], b:Set[String]): Double =  
     ((a.intersect(b).size.toDouble) / (a.union(b).size))
 
}