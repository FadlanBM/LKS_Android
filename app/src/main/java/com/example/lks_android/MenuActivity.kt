package com.example.lks_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val showMenu=findViewById<ImageView>(R.id.btn_show_menu)
        val addImage=findViewById<ImageView>(R.id.btn_add_image)
        val addAlbum=findViewById<ImageView>(R.id.btn_add_album)

        val rotatAnim=AnimationUtils.loadAnimation(this, R.anim.rotate)
        showMenu.setOnClickListener{
            if (addAlbum.visibility==View.VISIBLE){
                addImage.visibility=View.GONE
                addAlbum.visibility=View.GONE
                showMenu.startAnimation(rotatAnim)
            }else{
                showMenu.startAnimation(rotatAnim)
                addAlbum.visibility=View.VISIBLE
                addImage.visibility=View.VISIBLE
            }
        }
        addImage.setOnClickListener{
            val intent=Intent(this,ModalActivity::class.java)
            startActivity(intent)
        }

    }

}