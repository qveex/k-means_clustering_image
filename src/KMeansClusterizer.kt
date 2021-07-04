import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.pow
import kotlin.random.Random


class KMeansClusterizer(override var sourceImage: BufferedImage) : IClusterizer {

    private var clusteredImage = sourceImage
    private var clusters = ArrayList<Cluster>()

    val k get() = 20                // number of clusters
    private val iteration = 10      // max number of iterations


    override fun clustering() : BufferedImage {

        val begin: Long = System.currentTimeMillis()

        initCentroids()
        newCentroids()
        paint()

        val end: Long = System.currentTimeMillis()
        println("\nk = $k\nTime spent: ${ end - begin } ms")

        save()
        return clusteredImage
    }



    /*
    * Pick random pixel on image
    *
    * if the same pixel is already was picked
    *      it will be replaced
    * else
    *      add this pixel as centroid for current cluster
    */
    private fun initCentroids() {

        for (n in 0 until k) {

            var count = 0
            var cluster: Cluster

            do {

                val w = Random.nextInt(0, clusteredImage.width)
                val h = Random.nextInt(0, clusteredImage.height)
                val pix = Pixel(w, h, Color(sourceImage.getRGB(w, h)))
                cluster = Cluster(pix)
                count++

            } while ((clusters.contains(cluster) || checkClustersForRepeats(pix.color)) && count != 42)

            clusters.add(cluster)
        }
        clustersFilling()
    }


    /*
    * Fills lists of pixels in each of clusters
    * by minimal distance of these pixels to centroids
    */
    private fun clustersFilling() {

        for (w in 0 until sourceImage.width) {
            for (h in 0 until sourceImage.height) {

                var min = Int.MAX_VALUE
                var cluster = Cluster(clusters[0].curCentroid)
                val curColor = Color(sourceImage.getRGB(w, h))

                clusters.forEach{

                    val d = distance(curColor, it)
                    if (d < min) {
                        min = d
                        cluster = it
                    }
                }

                cluster.addPixel(Pixel(w, h, curColor))
            }
        }
    }


    /*
    * recalcs new centroid`s color for each of clusters
    * after recalcs fills it again
    * if (count of iteration > 20 or ALL cur and last centroid equals)
    *       cycle stops
    * else
    *       cycle continue and recalcs more suitable colors for centroids
    */
    private fun newCentroids() {

        var counter = 0
        do {

            clusters.forEach { it.newColor() }
            clustersFilling()
            println("iteration $counter")

        } while (++counter < iteration && clustersAreGood())
        println("cycle stop\niteration = $counter")
    }


    /*
     * returns state of all clusters
     * if (for all clusters last color == cur color)
     *      it is good :> (we stop cycle and paint!)
     * else
     *      it is bad :<
     */
    private fun clustersAreGood(): Boolean {
        var isGood = true
        clusters.forEach { isGood = isGood and it.isNotChanged() }
        return isGood
    }

    /*
     * change color of pixels based on their distance between centroids
     */
    private fun paint() {
        for (cluster in clusters)
            cluster.getPixels().forEach { clusteredImage.setRGB(it.x, it.y, cluster.curCentroid.color.rgb) }
    }


    /*
     * the function calculates color distance between random pixels
     * if (distance < 1500) i.e. big enough
     *      selected color is accepted
     * else
     *      return false and choose new random color
     */
    private fun checkClustersForRepeats(color: Color): Boolean {
        clusters.forEach { if (distance(color, it) < 1500) return true }
        return false
    }



    /*
     * calculates distance between cur pixel and centroid
     */
    private fun distance(color: Color, cluster: Cluster): Int {

        val r = (color.red - cluster.curCentroid.color.red).toDouble().pow(2)
        val g = (color.green - cluster.curCentroid.color.green).toDouble().pow(2)
        val b = (color.blue - cluster.curCentroid.color.blue).toDouble().pow(2)

        return (r + g + b).toInt()
    }


    /*
     * save current clustered image in root of project
     */
    private fun save() {
        try {
            val outputfile = File("clusteredImage(k = $k).jpg")
            ImageIO.write(clusteredImage, "jpg", outputfile)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}