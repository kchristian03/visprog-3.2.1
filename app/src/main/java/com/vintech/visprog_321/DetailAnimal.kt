package com.vintech.visprog_321

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vintech.visprog_321.databinding.ActivityDetailAnimalBinding
import database.GlobalVar
import model.Animal

class DetailAnimal : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDetailAnimalBinding
    private var position = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityDetailAnimalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        getintent()
        listener()
    }

    override fun onResume() {
        super.onResume()
        val animal = GlobalVar.listDataHewan[position]
        open(animal)
    }

    private fun getintent() {
        position = intent.getIntExtra("position", -1)
        val animal = GlobalVar.listDataHewan[position]
        if (animal.imageUri.isNotEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                baseContext.contentResolver.takePersistableUriPermission(
                    Uri.parse(GlobalVar.listDataHewan[position].imageUri),
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
        }
        open(animal)
    }

    private fun open(animal: Animal) {
        viewBinding.imageView.setImageURI(Uri.parse(GlobalVar.listDataHewan[position].imageUri))
        viewBinding.namaTV.text = animal.namahewan
        viewBinding.jenisTV.text = animal.jenishewan
        viewBinding.usiaTV.text = animal.usiahewan.toString()
    }

    private fun listener() {
        viewBinding.toolbarDetail.getChildAt(1).setOnClickListener {
            finish()
        }
    }
}