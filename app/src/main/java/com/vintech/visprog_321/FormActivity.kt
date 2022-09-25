package com.vintech.visprog_321

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.vintech.visprog_321.databinding.ActivityFormBinding
import database.GlobalVar
import model.Animal
import model.ayam
import model.kambing
import model.sapi

class FormActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFormBinding
    private lateinit var animal: Animal
    private var position = -1
    private var image: String = ""
    private var jenis: String = "null"
    private var id: Int = 0

    private val GetResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
                val uri = it.data?.data
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (uri != null) {
                        baseContext.contentResolver.takePersistableUriPermission(
                            uri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        )
                    }
                }// GET PATH TO IMAGE FROM GALLEY
                viewBinding.imageView.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
                image = uri.toString()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        getintent()
        listener()
    }

    private fun getintent() {
        position = intent.getIntExtra("position", -1)
        if (position != -1) {
            var animal = GlobalVar.listDataHewan[0]
            for (i in 0 until GlobalVar.listDataHewan.size) {
                if (position == GlobalVar.listDataHewan[i].id) {
                    animal = GlobalVar.listDataHewan[i]
                }
            }
            viewBinding.toolbarForm.title = "Edit Animal"
            viewBinding.submitButton.text = "Save"
            viewBinding.imageView.setImageURI(Uri.parse(animal.imageUri))
            viewBinding.namaTIL.editText?.setText(animal.namahewan)
            viewBinding.usiaTIL.editText?.setText(animal.usiahewan.toString())
            image = animal.imageUri
            when (animal) {
                is ayam -> {
                    viewBinding.ayam.isChecked = true
                }
                is sapi -> {
                    viewBinding.sapi.isChecked = true
                }
                else -> {
                    viewBinding.kambing.isChecked = true
                }
            }
            jenis = animal.jenishewan
        }
    }

    private fun listener() {
        viewBinding.imageView.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBinding.ayam.setOnClickListener {
            jenis = "Ayam"
        }
        viewBinding.sapi.setOnClickListener {
            jenis = "Sapi"
        }
        viewBinding.kambing.setOnClickListener {
            jenis = "Kambing"
        }

        viewBinding.submitButton.setOnClickListener {
            val nama = viewBinding.namaTIL.editText?.text.toString().trim()
            val usia = 0

            animal = Animal(nama, jenis, usia, image, id)
            animal.jenishewan = jenis

            checker()
        }

        viewBinding.toolbarForm.getChildAt(1).setOnClickListener {
            finish()
        }
    }

    private fun checker() {
        var isCompleted: Boolean = true

        if (animal.namahewan.isEmpty()) {
            viewBinding.namaTIL.error = "Nama hewan wajib di isi"
            isCompleted = false
        } else {
            viewBinding.namaTIL.error = ""
        }

        if (animal.jenishewan == "null") {
            Toast.makeText(this, "Jenis hewan wajib di pilih", Toast.LENGTH_LONG).show()
            isCompleted = false
        }

        animal.imageUri = image.toUri().toString()
        if (animal.imageUri.isEmpty()) {
            Toast.makeText(this, "Gambar hewan wajib di isi", Toast.LENGTH_LONG).show()
            isCompleted = false
        }

        if (viewBinding.usiaTIL.editText?.text.toString()
                .isEmpty() || viewBinding.usiaTIL.editText?.text.toString().toInt() < 0
        ) {
            viewBinding.usiaTIL.error = "Usia hewan bajib di isi"
            isCompleted = false
        }

        if (isCompleted == true) {
            var sAnimal: Animal
            if (position == -1) {
                if (GlobalVar.listDataHewan.size > 0) {
                    id = GlobalVar.listDataHewan[GlobalVar.listDataHewan.size - 1].id + 1
                }
                animal.usiahewan = viewBinding.usiaTIL.editText?.text.toString().toInt()
                if (jenis == "Ayam") {
                    sAnimal = ayam(
                        animal.namahewan,
                        animal.jenishewan,
                        animal.usiahewan,
                        animal.imageUri,
                        id
                    )
                    sAnimal.jenishewan = "Ayam"
                } else if (jenis == "Sapi") {
                    sAnimal = sapi(
                        animal.namahewan,
                        animal.jenishewan,
                        animal.usiahewan,
                        animal.imageUri,
                        id
                    )
                    sAnimal.jenishewan = "Sapi"
                } else {
                    sAnimal = kambing(
                        animal.namahewan,
                        animal.jenishewan,
                        animal.usiahewan,
                        animal.imageUri,
                        id
                    )
                    sAnimal.jenishewan = "Kambing"
                }
                GlobalVar.listDataHewan.add(sAnimal)
            } else {
                id = position
                animal.usiahewan = viewBinding.usiaTIL.editText?.text.toString().toInt()
                if (jenis == "Ayam") {
                    sAnimal = ayam(
                        animal.namahewan,
                        animal.jenishewan,
                        animal.usiahewan,
                        animal.imageUri,
                        id
                    )
                    sAnimal.jenishewan = "Ayam"
                } else if (jenis == "Sapi") {
                    sAnimal = sapi(
                        animal.namahewan,
                        animal.jenishewan,
                        animal.usiahewan,
                        animal.imageUri,
                        id
                    )
                    sAnimal.jenishewan = "Sapi"
                } else {
                    sAnimal = kambing(
                        animal.namahewan,
                        animal.jenishewan,
                        animal.usiahewan,
                        animal.imageUri,
                        id
                    )
                    sAnimal.jenishewan = "Kambing"
                }

                for (i in 0 until GlobalVar.listDataHewan.size) {
                    if (GlobalVar.listDataHewan[i].id == position) {
                        GlobalVar.listDataHewan[i] = sAnimal
                        break
                    }
                }
            }
            finish()
        }
    }
}