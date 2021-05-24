package thisaislan.qrgame.start.repository

interface IQrGameRepository {
    fun deleteOldQrGame()
    fun saveQrGameFilledMethod(tag: String, body: String)
    fun saveQrGameVoidMethod(tag: String)
    fun saveQrGameSource(value: String)
}