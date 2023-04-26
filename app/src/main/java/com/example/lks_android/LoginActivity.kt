package com.example.lks_android

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.lks_android.data.AppDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val passwordText=findViewById<EditText>(R.id.passwordEditText)
        val btn_show=findViewById<ImageView>(R.id.img_show)
        passwordText.transformationMethod=PasswordTransformationMethod.getInstance()
        btn_show.setImageResource(R.drawable.closed_eye)
        btn_show.setOnClickListener{
            if (passwordText.transformationMethod is PasswordTransformationMethod){
                passwordText.transformationMethod=HideReturnsTransformationMethod.getInstance()
                btn_show.setImageResource(R.drawable.baseline_remove_red_eye_24)
            }else{
                passwordText.transformationMethod=PasswordTransformationMethod.getInstance()
                btn_show.setImageResource(R.drawable.closed_eye)
            }
        }
        val emailText=findViewById<EditText>(R.id.emailEditText)
        val loginButton=findViewById<Button>(R.id.btn_login)
        database=AppDatabase.getInstance(applicationContext)


        loginButton.setOnClickListener {
            val email = emailText.text.toString().trim()
            val password = passwordText.text.toString().trim()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if (email.isEmpty()) {
                    Toast.makeText(this, "Username Wajib di isi!", Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(this, "Password Wajib di isi!", Toast.LENGTH_SHORT).show()
                } else if (email.matches(emailPattern.toRegex())) {
                val userExists=database.userDao().getUser(email,password)
                if (userExists!=null){
                    Toast.makeText(this, "Berhasil Login!", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this,MenuActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Gagal Login!", Toast.LENGTH_SHORT).show()
                }
                }else{
                Toast.makeText(this,"Email Harus berformat @gmail.com",Toast.LENGTH_SHORT).show()
                }
        }


//
//        db=DatabaseHelper(this)
//
//        val passwordText=findViewById<EditText>(R.id.passwordEditText)
//        val emailText=findViewById<EditText>(R.id.emailEditText)
//        val loginButton=findViewById<Button>(R.id.btn_login)
//        loginButton.setOnClickListener{
//            val email=emailText.text.toString().trim()
//            val password=passwordText.text.toString().trim()
//
//            if (email.isEmpty()){
//                Toast.makeText(this,"Username Wajib di isi!",Toast.LENGTH_SHORT).show()
//            }else if (password.isEmpty()){
//                Toast.makeText(this,"Password Wajib di isi!",Toast.LENGTH_SHORT).show()
//            }else{
//                val userExists=db.checkUser(email, password)
//                Toast.makeText(applicationContext,"$userExists",Toast.LENGTH_SHORT).show()
////                if (userExists){
////                    Toast.makeText(this,"Login Berhasil",Toast.LENGTH_SHORT).show()
////                    startActivity(Intent(this,MenuActivity::class.java))
////                    finish()
////                }else{
////                    Toast.makeText(this,"Username Dan Password yang anda masukkan salah!",Toast.LENGTH_SHORT).show()
////                }
//            }
//        }

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