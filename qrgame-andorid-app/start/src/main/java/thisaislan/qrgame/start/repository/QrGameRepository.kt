package thisaislan.qrgame.start.repository

import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import thisaislan.qrgame.base.util.Constants
import java.io.File
import java.io.FileOutputStream

class QrGameRepository(private val contextWrapper: ContextWrapper) : IQrGameRepository {

    override fun deleteOldQrGame() =
        with(getScript()) {
            delete()

            if (exists()) {
                canonicalFile.delete()

                if (exists()) {
                    takeUnless { contextWrapper.deleteFile(name) }?.let {
                        throw Exception("Couldn't delete the file ")
                    }
                }
            }
        }

    override fun saveQrGameFilledMethod(tag: String, body: String) = writeMethod(tag, body)

    override fun saveQrGameVoidMethod(tag: String) = writeMethod(tag)

    override fun saveQrGameSource(value: String) = write(value)

    private fun writeMethod(tag: String, body: String = "") =
        write("function _${getNameByTag(tag)}() {\r\n $body \r\n}\r\n\r\n")

    private fun write(value: String) =
        with(FileOutputStream(getScript(), true)) {
            write(value.toByteArray())
            close()
        }

    private fun getFolder() = contextWrapper.getDir(
        Constants.FileNames.QR_GAME_SCRIPT_FOLDER,
        AppCompatActivity.MODE_PRIVATE
    )

    private fun getScript() = File(getFolder(), Constants.FileNames.QR_GAME_SCRIPT)

    private fun getNameByTag(tag: String) = tag.dropLast(1).toLowerCase()

}