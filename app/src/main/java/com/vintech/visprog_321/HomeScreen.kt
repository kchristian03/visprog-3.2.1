package com.vintech.visprog_321

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vintech.visprog_321.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        listener()
    }

    private fun listener() {
        viewBinding.addDataFAB.setOnClickListener {
            val myIntent = Intent(this, FormActivity::class.java)
            startActivity(myIntent)
        }
    }
}