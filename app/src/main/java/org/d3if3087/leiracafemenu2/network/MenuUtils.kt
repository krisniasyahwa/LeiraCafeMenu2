//package org.d3if3087.leiracafemenu2.network
//
//import android.util.Log
//import android.widget.ImageView
//import com.bumptech.glide.Glide
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import org.d3if3087.leiracafemenu2.network.MenuApi
//
//suspend fun fetchMenu(imageView: ImageView) {
//    try {
//        val menuList = MenuApi.service.getMenu("your_authorization_token")
//        for (menu in menuList) {
//            val imageUrl = MenuApi.getMenuUrl(menu.image)
//            Log.d("MenuApi", "Image URL: $imageUrl")
//            withContext(Dispatchers.Main) {
//                loadImage(imageUrl, imageView)
//            }
//        }
//    } catch (e: Exception) {
//        Log.e("MenuApi", "Error fetching menu", e)
//    }
//}
//
//fun loadImage(imageUrl: String, imageView: ImageView) {
//    Glide.with(imageView.context)
//        .load(imageUrl)
//        .into(imageView)
//}
