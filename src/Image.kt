import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JPanel

// http://statistica.ru/theory/klasterizatsiya-metod-k-srednikh/#:~:text=%D0%9C%D0%B5%D1%82%D0%BE%D0%B4%20k%2D%D1%81%D1%80%D0%B5%D0%B4%D0%BD%D0%B8%D1%85%20%E2%80%93%20%D1%8D%D1%82%D0%BE%20%D0%BC%D0%B5%D1%82%D0%BE%D0%B4,%D1%86%D0%B5%D0%BD%D1%82%D1%80%D0%BE%D0%B8%D0%B4%D1%83)%20%D0%BA%D0%BE%D1%82%D0%BE%D1%80%D0%BE%D0%B3%D0%BE%20%D0%BE%D0%BD%D0%BE%20%D0%B1%D0%BB%D0%B8%D0%B6%D0%B5%20%D0%B2%D1%81%D0%B5%D0%B3%D0%BE.&text=%2D%20%D1%86%D0%B5%D0%BD%D1%82%D1%80%D0%BE%D0%B8%D0%B4%20%D0%B4%D0%BB%D1%8F%20%D0%BA%D0%BB%D0%B0%D1%81%D1%82%D0%B5%D1%80%D0%B0%20.
// https://habr.com/ru/post/165087/
// https://habr.com/ru/post/67078/
// https://habr.com/ru/post/427761/
// https://habr.com/ru/company/jetinfosystems/blog/467745/

class Image(private val path: String) : JPanel() {

    private val image: BufferedImage

    init {
        image = readFile(path)
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
        frame.isVisible = true
        frame.contentPane.add(this)
        background = Color.BLACK
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    }

    fun clustering(): Image {
        val clusterizer = KMeansClusterizer(readFile(path))
        clusterizer.clustering()

        return Image("clusteredImage.jpg")
    }




    private fun readFile(p: String): BufferedImage {
        return try {
            ImageIO.read(File(p))
        } catch (ex: IOException) {
            ex.printStackTrace()
            BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
        }
    }
}