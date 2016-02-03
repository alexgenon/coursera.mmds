

/**
 * @author alexandregenon
 */

package object WordSim {
  class WordSim(jaccardSim: Double) {
    var buckets = Map[Char, Set[String]]()
    def prefixSize(s: String): Int = Math.floor((100 - jaccardSim*100) * s.size/100).toInt + 1
    def findSimilar(s: String): Set[String] = {
      def recFind(s: String, limit: Int, acc: Set[String]): Set[String] = {
        if (limit == 0) acc
        else recFind(s.tail, limit - 1, acc.union(buckets.getOrElse(s(0), Set())))
      }
      recFind(s, prefixSize(s), Set())
    }

    def index(s: String) =
      for (i <- 0 until prefixSize(s)) {
        val curSet = buckets getOrElse (s(i), Set())
        buckets = buckets updated (s(i), curSet + s)
      }

  }
}