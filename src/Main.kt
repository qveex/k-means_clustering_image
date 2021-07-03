import java.lang.NullPointerException


fun main() {

    try {

        val imageReader = ImageReader()
        val sourcePic = Image(imageReader.path)
        val clusteredPic = sourcePic.clustering()

        sourcePic.paint()
        clusteredPic.paint()


    } catch (e: NullPointerException) {
        println("\nThis file is null or not image ¯\\_(ツ)_/¯\n")
    }
}