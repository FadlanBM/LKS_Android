package com.example.lks_android.Image

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface  ApiInterface{

    @Multipart
    @POST("upload_image.php")
    fun uploadImage(
        @Part("image\"; filename=\"image.jp\"") file: MultipartBody.Part
    ):retrofit2.Call<ResponseBody>
}