import dgim.DGIM
import dgim.DGIM.Bucket
import org.xml.sax.helpers.NewInstance

object testDGIM {
  println("coucou gamin!")                        //> coucou gamin!
  
  val s = DGIM.Snapshot( List (
  	Bucket(100,1,1),
  	Bucket(98,1,1),
  	Bucket(95,2,1),
  	Bucket(92,2,1),
  	Bucket(87,4,1),
  	Bucket(80,8,1),
  	Bucket(65,8,1))
  	,100)                                     //> s  : dgim.DGIM.Snapshot = At time : 100
                                                  //| Time : | 100|  98|  95|  92|  87|  80|  65|
                                                  //| Size : |   1|   1|   2|   2|   4|   8|   8|
                                                  //| Count : |   1|   1|   1|   1|   1|   1|   1|
	
	val dgim = DGIM(40,s)                     //> dgim  : dgim.DGIM.DGIMInstance = DGIMInstance(40,At time : 100
                                                  //| Time : | 100|  98|  95|  92|  87|  80|  65|
                                                  //| Size : |   1|   1|   2|   2|   4|   8|   8|
                                                  //| Count : |   1|   1|   1|   1|   1|   1|   1|)
	val d2 =  dgim.nextStep(1)                //> d2  : dgim.DGIM.DGIMInstance = DGIMInstance(40,At time : 101
                                                  //| Time : | 101| 100|  95|  87|  80|  65|
                                                  //| Size : |   1|   2|   4|   4|   8|   8|
                                                  //| Count : |   1|   2|   2|   1|   1|   1|)
	val d3 = d2.nextStep(1)                   //> d3  : dgim.DGIM.DGIMInstance = DGIMInstance(40,At time : 102
                                                  //| Time : | 102| 101| 100|  95|  87|  80|  65|
                                                  //| Size : |   1|   1|   2|   4|   4|   8|   8|
                                                  //| Count : |   1|   1|   2|   2|   1|   1|   1|)
	val d4 = d3.nextStep(1)                   //> d4  : dgim.DGIM.DGIMInstance = DGIMInstance(40,At time : 103
                                                  //| Time : | 103| 102| 100|  95|  87|  80|  65|
                                                  //| Size : |   1|   2|   2|   4|   4|   8|   8|
                                                  //| Count : |   1|   2|   2|   2|   1|   1|   1|)
	val d5 = d4.nextStep(1)                   //> d5  : dgim.DGIM.DGIMInstance = DGIMInstance(40,At time : 104
                                                  //| Time : | 104| 103| 102| 100|  95|  87|  80|  65|
                                                  //| Size : |   1|   1|   2|   2|   4|   4|   8|   8|
                                                  //| Count : |   1|   1|   2|   2|   2|   1|   1|   1|)
	val d6 = d5.nextStep(1)                   //> d6  : dgim.DGIM.DGIMInstance = DGIMInstance(40,At time : 105
                                                  //| Time : | 105| 104| 102|  95|  80|
                                                  //| Size : |   1|   2|   4|   8|   8|
                                                  //| Count : |   1|   2|   4|   3|   1|)
}