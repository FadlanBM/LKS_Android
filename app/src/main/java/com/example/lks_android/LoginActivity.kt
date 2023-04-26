package com.example.lks_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    private lateinit var db:DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        db=DatabaseHelper(this)

        val passwordText=findViewById<EditText>(R.id.passwordEditText)
        val emailText=findViewById<EditText>(R.id.emailEditText)
        val loginButton=findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener{
            val email=emailText.text.toString().trim()
            val password=passwordText.text.toString().trim()

            if (email.isEmpty()){
                Toast.makeText(this,"Username Wajib di isi!",Toast.LENGTH_SHORT).show()
            }else if (password.isEmpty()){
                Toast.makeText(this,"Password Wajib di isi!",Toast.LENGTH_SHORT).show()
            }else{
                val userExists=db.checkUser(email, password)
                Toast.makeText(applicationContext,"$userExists",Toast.LENGTH_SHORT).show()
//                if (userExists){
//                    Toast.makeText(this,"Login Berhasil",Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this,MenuActivity::class.java))
//                    finish()
//                }else{
//                    Toast.makeText(this,"Username Dan Password yang anda masukkan salah!",Toast.LENGTH_SHORT).show()
//                }
            }
        }

        val moveText=findViewById<TextView>(R.id.txt_to_register)
        moveText.setOnClickListener{
            val intent=Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        val moveImg=findViewById<ImageView>(R.id.row_back_login)
        moveImg.setOnClickListener{
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}