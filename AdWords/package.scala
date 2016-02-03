

/**
 * @author alexandregenon
 */
package object AdWords {
  case class Table(allAd: Array[Array[Double]], var ctrLimit: Int, labels: Array[String]) {
    def sel(pos:Int) = allAd.filter(ad=>ad(6)==pos).head
    
    
    def dumpTable = for (i <- 0 until allAd.size) dumpAd(i)
    def dumpAd(pos:Int) = println (labels(pos) + " : "+(allAd(pos) map (("%.3f".format(_)))).mkString(", "))
    def getAd(pos:Int) = allAd.zipWithIndex    // index is required in further operations
                              .filter(ad=>ad._1(4)>ad._1(0) && ad._1(6)==0)  // Still has some budget and not yet selected
                              .maxBy(ad => ad._1(0) * ad._1(pos))  //Get the best expected yield
                              
    def selectAd(pos:Int) = {
       val id=getAd(pos)._2
       allAd(id)(6) = pos
    }
    
    def nbDisplay = Math.round(ctrLimit / (sel(1)(1) + sel(2)(2) + sel(3)(3)))
    def nbDisplayForAd(pos: Int) = {
      val ad = sel(pos)
      Math.round((ad(4)/ad(0))/ad(pos))
    } 
    
    private def updateAd(pos:Int,pageDisplay: Int) = {
      val ad=allAd.filter(a=>a(6)==pos).head
      var ctrCount = Math.round(ad(pos)*pageDisplay)
      ad(5) = ad(5) + ctrCount
      ad(4) = ad(4) - ctrCount * ad(0)
      ctrLimit = ctrLimit - ctrCount.toInt
    }
    def updateAds(pageDisplay: Int) = 
      for(i<-1 to 3) updateAd(i,pageDisplay)
      
    def resetSelection = for (i <- 0 until allAd.size) allAd(i)(6)=0
    
  }
    
}