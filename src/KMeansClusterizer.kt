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

    private val k = 100

    override fun clustering() : BufferedImage {

        val begin: Long = System.currentTimeMillis()

        /*
         * Pick random pixel on image
         *
         * if the same pixel is already was picked
         *      it will be replaced
         * else
         *      add this pixel as centroid for current cluster
         */


        for (n in 0 until k) {

            var iteration = 0
            var cluster: Cluster

            do {

                val w = Random.nextInt(0, clusteredImage.width)
                val h = Random.nextInt(0, clusteredImage.height)
                val pix = Pixel(w, h, Color(sourceImage.getRGB(w, h)))
                cluster = Cluster(pix)
                iteration++

                //println("iteration = $iteration")
            } while ((clusters.contains(cluster) || checkClustersForRepeats(pix.color)) && iteration != 7)

            clusters.add(cluster)
        }

        //clusters.forEach{
            //println("centroid = ${it.curCentroid}")
        //}

        fastPaint()

        save()

        val end: Long = System.currentTimeMillis()
        println("\nk = $k\nTime spent: ${ end - begin } ms")

        return clusteredImage
    }


    /*
     * change color of pixels based on their distance between centroids
     */
    private fun fastPaint() {
        for (w in 0 until sourceImage.width) {
            for (h in 0 until sourceImage.height) {

                val newColor = calcColor(Color(sourceImage.getRGB(w, h)))
                clusteredImage.setRGB(w, h, newColor.rgb)

            }
        }
    }

    /*
     * calculates new color of pixel
     * based on its distance between centroids of clusters
     * if distance between color of current pixel and cur centroid color is minimal
     *      this color becomes new color of this pix
     * else
     *      check distance between next centroids
     */
    private fun calcColor(color: Color): Color {

        var min = Int.MAX_VALUE
        var newColor = Color(32, 32, 32)

        clusters.forEach{

            val r = (color.red - it.curCentroid.color.red).toDouble().pow(2)
            val g = (color.green - it.curCentroid.color.green).toDouble().pow(2)
            val b = (color.blue - it.curCentroid.color.blue).toDouble().pow(2)

            if ((r + g + b) < min) {
                min = (r + g + b).toInt()
                newColor = Color(it.curCentroid.color.rgb)
            }
        }
        return newColor
    }


    /*
     * the function calculates whether distance between random pixels
     * if (distance < 1500) i.e. big enough
     *      selected color is accepted
     * else
     *      return false and choose new random color
     */
    private fun checkClustersForRepeats(color: Color): Boolean {

        clusters.forEach {

            val r = (color.red - it.curCentroid.color.red).toDouble().pow(2)
            val g = (color.green - it.curCentroid.color.green).toDouble().pow(2)
            val b = (color.blue - it.curCentroid.color.blue).toDouble().pow(2)

            if ((r + g + b) < 1500) return true
            if (it === clusters.last()) return false
        }
        return false
    }


    /*
     * save current clustered image in root of project
     */

    private fun save() {
        try {
            val outputfile = File("clusteredImage.jpg")
            ImageIO.write(clusteredImage, "jpg", outputfile)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}