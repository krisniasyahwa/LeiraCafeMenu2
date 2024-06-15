package org.d3if3087.leiracafemenu2.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.d3if3087.leiracafemenu2.model.Menu
import org.d3if3087.leiracafemenu2.network.ApiStatus
import org.d3if3087.leiracafemenu2.network.MenuApi
import java.io.ByteArrayOutputStream

class MainViewModel : ViewModel() {
    val data = mutableStateOf(emptyList<Menu>())

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun retrieveData(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = MenuApi.service.getMenu(id)
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }

    fun saveData(id: String, name: String, type: String, price: String, bitmap: Bitmap){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = MenuApi.service.postMenu(
                    id,
                    name = name.toRequestBody("text/plain".toMediaTypeOrNull()),
                    type = type.toRequestBody("text/plain".toMediaTypeOrNull()),
                    price = price.toRequestBody("text/plain".toMediaTypeOrNull()),
                    bitmap.toMultipartBody(),
                )
                if (result.status == "success")
                    retrieveData(id)
                else
                    throw Exception(result.message)
            }catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun Bitmap.toMultipartBody(): MultipartBody.Part{
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG,80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image".toMediaTypeOrNull(),0, byteArray.size
        )
        return MultipartBody.Part.createFormData(
            "image","image",requestBody
        )
    }

    fun clearMessage() { errorMessage.value = null }
}