package org.d3if3087.leiracafemenu2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.d3if3087.leiracafemenu2.model.Menu
import org.d3if3087.leiracafemenu2.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

private const val BASE_URL = "https://leiracafeapi.000webhostapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient.Builder().build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .build()

interface MenuApiService {
    @GET("/")
    suspend fun getMenu(
        @Header("Authorization") id: String
    ): List<Menu>

    @Multipart
    @POST("/")
    suspend fun postMenu(
        @Header("Authorization") id: String,
        @Part("name") name: RequestBody,
        @Part("type") type: RequestBody,
        @Part("price") price: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus
}

object MenuApi {
    val service: MenuApiService by lazy {
        retrofit.create(MenuApiService::class.java)
    }

    fun getMenuUrl(image: String): String {
        return "$BASE_URL/uploads/$image"
    }
}

fun getImageUrl(imagePath: String): String {
    return MenuApi.getMenuUrl(imagePath)
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }
