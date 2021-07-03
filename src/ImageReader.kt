import java.io.File
import java.lang.NullPointerException
import java.nio.file.Files
import javax.swing.JFileChooser


class ImageReader {

    private var file: File
    val path get() = file.path

    init {

        do {

            val fileChooser = JFileChooser()
            val ret = fileChooser.showDialog(null, "Открыть файл")

            if (ret == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.selectedFile

                val mimeType = Files.probeContentType(file.toPath())
                if (mimeType.split("/")[0] == "image") break

            } else {
                throw NullPointerException()
            }

        } while (true)
    }
}