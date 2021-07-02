import java.awt.image.BufferedImage


interface IClusterizer {

    val sourceImage: BufferedImage


    fun clustering() : BufferedImage

}