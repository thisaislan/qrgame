package thisaislan.qrgame.start.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import thisaislan.qrgame.start.repository.QrGameRepository

class QrValueProcessingViewModel(application: Application) : AndroidViewModel(application) {

    private val isSuccessful = MutableLiveData<Boolean>()
    private val scriptRepository = QrGameRepository(application)

    private val sourceTag = "SC~"
    private var methodsTags = arrayListOf(
        "AP~", "BP~", "AR~", "BR~", "BD~", "BK~", "BU~", "PD~", "PK~", "PU~", "AD~", "AK~",
        "AU~", "UD~", "UK~", "UU~", "RD~", "RK~", "RD~", "RK~", "RU~", "DD~", "DK~", "DU~",
        "LD~", "LK~", "LU~", "QD~", "QK~", "QU~", "HD~", "HK~", "HU~", "SG~", "AC~", "BC~"
    )

    fun getIsSuccessful() = isSuccessful

    fun processingQrGame(qrValue: String?) =
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                writeQrGame(qrValue)
            }
        }

    private fun getAllTags(qrValue: String) = Regex("(?<=[A-Z][A-Z0-9]~)|(?=[A-Z][A-Z0-9]~)")
        .split(qrValue).filter { it.isNotBlank() }
        .chunked(2)
        .associate { it.first() to it.last() }

    private suspend fun writeQrGame(qrValue: String?) =
        withContext(Dispatchers.IO) {

            if (qrValue.isNullOrBlank()) {
                isSuccessful.postValue(false)
            } else {
                val allTagsMap = getAllTags(qrValue)
                val sourceValue = allTagsMap[sourceTag]

                if (sourceValue.isNullOrBlank()) {
                    isSuccessful.postValue(false)
                } else {
                    runCatching {
                        writeQrGame(allTagsMap, sourceValue)
                    }.onSuccess {
                        isSuccessful.postValue(true)
                    }.onFailure {
                        isSuccessful.postValue(false)
                    }
                }
            }
        }

    private fun writeQrGame(allTagsMap: Map<String, String>, sourceValue: String) {
        deleteOldQrGame()
        saveQrGameFilledMethod(allTagsMap)
        saveQrGameVoidMethod()
        saveQrGameSource(sourceValue)
    }

    private fun deleteOldQrGame() = scriptRepository.deleteOldQrGame()

    private fun saveQrGameFilledMethod(allTagsMap: Map<String, String>) =
        allTagsMap.forEach { entity ->
            if (methodsTags.remove(entity.key)) {
                scriptRepository.saveQrGameFilledMethod(entity.key, entity.value)
            }
        }

    private fun saveQrGameVoidMethod() =
        methodsTags.forEach {
            scriptRepository.saveQrGameVoidMethod(it)
        }

    private fun saveQrGameSource(sourceValue: String) =
        scriptRepository.saveQrGameSource(sourceValue)

}