import java.awt.Color
import java.awt.Graphics
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JPanel


class Image(private val path: String) : JPanel() {

    private var image: BufferedImage

    init {
        image = readFile(path)
        if (image.width >= 1280 || image.height >= 720) image = scaledImage(image,image.width / 2,image.height / 2)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.drawImage(image, 0, 0, null)
    }

    fun paint() {

        val frame = JFrame()
        frame.setSize(image.width, image.height + 40)
        frame.iconImage = image
        frame.title = "image"
        frame.title = path.substringAfterLast("\\")
        frame.isVisible = true
        frame.contentPane.add(this)
        background = Color.BLACK
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    }

    fun clustering(): Image {
        val clusterizer = KMeansClusterizer(readFile(path))
        clusterizer.clustering()

        return Image("clusteredImage(k = ${clusterizer.k}).jpg")
    }


    private fun readFile(p: String): BufferedImage {
        return try {
            ImageIO.read(File(p))
        } catch (ex: IOException) {
            ex.printStackTrace()
            BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
        }
    }


    private fun scaledImage(img: BufferedImage, w: Int, h: Int): BufferedImage {

        val resizedImage = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
        val g2 = resizedImage.createGraphics()
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
        g2.drawImage(img, 0, 0, w, h, null)
        g2.dispose()

        return resizedImage
    }
}