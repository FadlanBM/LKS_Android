package com.example.lks_android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.lks_android.Image.ApiInterface
import com.example.lks_android.databinding.ActivityModalBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

private fun uploadImage(filePath: String) {
    val file=File("imagePath")
    val requestBody= file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
    val multipartBody=MultipartBody.Part.createFormData("image",file.name,requestBody)

    val apiInterface=Retrofit.Builder()
        .baseUrl("https://api.galeri.infiniteuny.id/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiInterface::class.java)

    val call=apiInterface.uploadImage(multipartBody)
    call.enqueue(object :Callback<ResponseBody>{
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

        }
    })
}
class ModalActivity : AppCompatActivity() {
    val REQUEST_CODE = 100

    private var _binding:ActivityModalBinding?=null
    private val binding get()=_binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityModalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageUpload.setOnClickListener{
            val intent=Intent()
            intent.type="image/*"
            intent.action=Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Select Image"), REQUEST_CODE)
        }

        fun onActivityResult(requestCode:Int,resultCode:Int,data:Intent?){
            if (requestCode==REQUEST_CODE && resultCode==Activity.RESULT_OK && data!=null){
                    val selectImageUri=data.data
                val filePathColumn= arrayOf(MediaStore.Images.Media.DATA)

                val cursor=contentResolver.query(selectImageUri!!,filePathColumn,null,null,null)
                cursor!!.moveToFirst()

                val columnIndex=cursor.getColumnIndex(filePathColumn[0])
                val filePath=cursor.getString(columnIndex)
                cursor.close()

                uploadImage(filePath)
            }
        }
        binding.btnBackMain.setOnClickListener{
            startActivity(Intent(this,MenuActivity::class.java))
        }
    }
}