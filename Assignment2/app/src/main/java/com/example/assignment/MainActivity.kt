package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.data.Datasource
import com.example.assignment.databinding.ActivityMainBinding
//Main Activity handles Login
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Datasource().data()

        val button = binding.loginButton
        button.setOnClickListener {
            var found = false
            Datasource.users.forEach{ user ->
                if(binding.usernameText.text.toString() == user.username){
                    if(binding.passwordText.text.toString() == user.password){
                        found = true
                        val context=view.context
                        val intent = Intent(context,StudentHomepage::class.java)
                        context.startActivity(intent)
                    }
                }
            }
            if(!found){
                Toast.makeText(this, "Wrong username or password!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}