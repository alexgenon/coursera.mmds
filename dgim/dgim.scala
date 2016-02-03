package dgim

/**
 * @author alexandregenon
 */
object DGIM {
  // The buckets are ordered by timestamps decreasing
  case class Bucket(timestamp: Int, size: Int, count: Int)
  case class Snapshot(buckets: List[Bucket], curTimestamp: Int) {
    def mkString(extractor: (Bucket => Int)): String = {
      val f = "%4d"
      (buckets.foldLeft("")((acc, b) => { acc + f.format(extractor(b)) } + "|"))
    }
    override def toString() = {
      val ts = "At time : " + curTimestamp
      val bts = mkString { b => b.timestamp }
      val bs = mkString { b => b.size }
      val bc = mkString { b => b.count }
      ts + "\nTime : |" + bts + "\nSize : |" + bs + "\nCount : |" + bc
    }
  }
  val initialSnapshot = Snapshot(List[Bucket](), 0)

  case class DGIMInstance(N: Int, initialSnapshot: Snapshot) {
    def advanceTime(s: Snapshot) = {
      val newTimestamp = s.curTimestamp + 1

      if (s.buckets.last.timestamp <= newTimestamp - N)
        Snapshot(s.buckets.dropRight(1), newTimestamp)
      else
        Snapshot(s.buckets, newTimestamp)
    }

    def compactBuckets(buckets: List[Bucket]): List[Bucket] = {
      if (buckets.size <= 2) {
        buckets
      } else {
        val fb = buckets.head
        val curSize = fb.size
        if (buckets.count { b => (b.size == curSize) } == 3) {
          val sb = buckets.tail.head
          val tb = buckets.tail.tail.head
          val newUpperBucket = Bucket(sb.timestamp, sb.size * 2, sb.count + tb.count)
          val remBuckets = buckets.drop(3)
          fb :: compactBuckets(newUpperBucket :: remBuckets)
        } else {
          buckets
        }
      }
    }

    def nextStep(nextBit: Int) = {
      val ns = advanceTime(initialSnapshot)
      if (nextBit == 0)
        DGIMInstance(N, ns)
      else {
        val newBucket = Bucket(ns.curTimestamp, 1, 1)
        val ns2 = Snapshot(compactBuckets(newBucket :: ns.buckets), ns.curTimestamp)
        DGIMInstance(N, ns2)
      }
    }
  }

  def apply(N: Int, initialSnapshot: Snapshot = initialSnapshot) = DGIMInstance(N, initialSnapshot)

}