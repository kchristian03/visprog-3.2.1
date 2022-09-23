package com.vintech.visprog_321

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.vintech.visprog_321.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityFormBinding
    var image: String = ""

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
        listener()
    }

    private fun listener() {
        viewBinding.imageView.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBinding.submitButton.setOnClickListener {
            var nama = viewBinding.namaTIL.editText?.text.toString().trim()
            var usia = viewBinding.usiaTIL.editText?.text.toString().trim()
            //TODO: Use <include> and <exclude> to control what is backed up.

        }
    }
}