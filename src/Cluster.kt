import java.awt.Color

class Cluster(centroid: Pixel) {

    var curCentroid: Pixel = centroid
    private val pixels = ArrayList<Pixel>()
    private var lastCentroid = Pixel(curCentroid.x, curCentroid.y, curCentroid.color)


    fun getPixels() = pixels

    fun addPixel(pix: Pixel) = pixels.add(pix)

    private fun clearPixels() = pixels.clear()

    fun isNotChanged() = curCentroid == lastCentroid


    /*
     * calculates new color for this centroid
     * new color is average of R, G, B
     * also clear list of pixels of this centroid
     * for new centroid will be calculated new list of pixels
     * updates last centroid
     */
    fun newColor() {

        var newR = 0
        var newG = 0
        var newB = 0

        pixels.forEach {

            newR += it.color.red
            newG += it.color.green
            newB += it.color.blue

        }

        lastCentroid.color = curCentroid.color
        curCentroid.color = Color(newR / (pixels.size + 1), newG / (pixels.size + 1), newB / (pixels.size + 1))
        clearPixels()
    }

}