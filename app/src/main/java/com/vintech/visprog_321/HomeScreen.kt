package com.vintech.visprog_321

import `interface`.CardListener
import adapter.ListDataRV
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vintech.visprog_321.databinding.ActivityHomeScreenBinding
import database.GlobalVar
import model.*

class HomeScreen : AppCompatActivity(), CardListener {
    private lateinit var viewBinding: ActivityHomeScreenBinding
    private val adapter = ListDataRV(GlobalVar.listDataHewan, this)
    private val adapterTemp = ListDataRV(GlobalVar.tempDataHewan, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        listener()
        setDataRV()

        // access the items of the list
        val arrayAnimal = resources.getStringArray(R.array.listAnimal)
        // access the spinner
        val spinner = viewBinding.filterSpinner
        val adapterr = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arrayAnimal
        )
        spinner.adapter = adapterr
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Toast.makeText(
                    this@HomeScreen,
                    getString(R.string.jenis_hewan) + " " +
                            "" + arrayAnimal[position], Toast.LENGTH_SHORT
                ).show()

                when (arrayAnimal[position]) {
                    "Ayam" -> {
                        GlobalVar.session = "ayam"
                        GlobalVar.tempDataHewan.clear()
                        for (i in 0 until GlobalVar.listDataHewan.size) {
                            if (GlobalVar.listDataHewan[i] is ayam) {
                                GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                            }
                        }
                        viewBinding.listDataRV.adapter = adapterTemp
                        adapter.notifyDataSetChanged()
                    }
                    "Sapi" -> {
                        GlobalVar.session = "sapi"
                        GlobalVar.tempDataHewan.clear()
                        for (i in 0 until GlobalVar.listDataHewan.size) {
                            if (GlobalVar.listDataHewan[i] is sapi) {
                                GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                            }
                        }
                        viewBinding.listDataRV.adapter = adapterTemp
                        adapter.notifyDataSetChanged()
                    }
                    "Kambing" -> {
                        GlobalVar.session = "kambing"
                        GlobalVar.tempDataHewan.clear()
                        for (i in 0 until GlobalVar.listDataHewan.size) {
                            if (GlobalVar.listDataHewan[i] is kambing) {
                                GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                            }
                        }
                        viewBinding.listDataRV.adapter = adapterTemp
                        adapter.notifyDataSetChanged()
                    }
                    "ALL" -> {
                        GlobalVar.session = "null"
                        viewBinding.listDataRV.adapter = adapter
//                        GlobalVar.listDataHewan.clear()
//                        viewBinding.AddDataTV.alpha = 1f
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        when (GlobalVar.session) {
            "Ayam" -> {
                GlobalVar.tempDataHewan.clear()
                for (i in 0 until GlobalVar.listDataHewan.size) {
                    if (GlobalVar.listDataHewan[i] is ayam) {
                        GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                    }
                }
                viewBinding.listDataRV.adapter = adapterTemp
                adapter.notifyDataSetChanged()
            }
            "Sapi" -> {
                GlobalVar.tempDataHewan.clear()
                for (i in 0 until GlobalVar.listDataHewan.size) {
                    if (GlobalVar.listDataHewan[i] is sapi) {
                        GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                    }
                }
                viewBinding.listDataRV.adapter = adapterTemp
                adapter.notifyDataSetChanged()
            }
            "kambing" -> {
                GlobalVar.tempDataHewan.clear()
                for (i in 0 until GlobalVar.listDataHewan.size) {
                    if (GlobalVar.listDataHewan[i] is kambing) {
                        GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                    }
                }
                viewBinding.listDataRV.adapter = adapterTemp
                adapter.notifyDataSetChanged()
            }
            else -> {
                adapter.notifyDataSetChanged()
            }
        }

        if (GlobalVar.listDataHewan.size > 0) {
            viewBinding.AddDataTV.alpha = 0f
        } else {
            viewBinding.AddDataTV.alpha = 1f
        }
    }

    private fun setDataRV() {
        val layoutManager = LinearLayoutManager(baseContext)
        viewBinding.listDataRV.layoutManager = layoutManager
        viewBinding.listDataRV.adapter = adapter
    }

    private fun listener() {
        viewBinding.addDataFAB.setOnClickListener {
            val myIntent = Intent(this, FormActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun onCardClick(position: Int) {
        val myIntent = Intent(this, DetailAnimal::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCardClick1(button: String, position: Int) {
        if (button == "delete") {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Animal")
            builder.setMessage("Are you sure you want to delete this animal?")

            builder.setPositiveButton(android.R.string.yes) { function, which ->
                val snackbar = Snackbar.make(
                    viewBinding.listDataRV,
                    "Animal Deleted",
                    Snackbar.LENGTH_INDEFINITE
                )
                snackbar.setAction("Dismiss") { snackbar.dismiss() }
                snackbar.setActionTextColor(Color.WHITE)
                snackbar.setBackgroundTint(Color.GRAY)
                snackbar.show()

                for (i in 0 until GlobalVar.listDataHewan.size) {
                    if (GlobalVar.listDataHewan[i].id == position) {
                        GlobalVar.listDataHewan.removeAt(i)
                        break
                    }
                }

                when (GlobalVar.session) {
                    "Ayam" -> {
                        GlobalVar.tempDataHewan.clear()
                        for (i in 0 until GlobalVar.listDataHewan.size) {
                            if (GlobalVar.listDataHewan[i] is ayam) {
                                GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                            }
                            if (GlobalVar.tempDataHewan.size > 0) {
                                viewBinding.AddDataTV.alpha = 0f
                            } else {
                                viewBinding.AddDataTV.alpha = 1f
                            }
                        }
                        viewBinding.listDataRV.adapter = adapterTemp
                        adapter.notifyDataSetChanged()
                    }
                    "Sapi" -> {
                        GlobalVar.tempDataHewan.clear()
                        for (i in 0 until GlobalVar.listDataHewan.size) {
                            if (GlobalVar.listDataHewan[i] is sapi) {
                                GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                            }
                            if (GlobalVar.tempDataHewan.size > 0) {
                                viewBinding.AddDataTV.alpha = 0f
                            } else {
                                viewBinding.AddDataTV.alpha = 1f
                            }
                        }
                        viewBinding.listDataRV.adapter = adapterTemp
                        adapter.notifyDataSetChanged()
                    }
                    "Kambing" -> {
                        GlobalVar.tempDataHewan.clear()
                        for (i in 0 until GlobalVar.listDataHewan.size) {
                            if (GlobalVar.listDataHewan[i] is kambing) {
                                GlobalVar.tempDataHewan.add(GlobalVar.listDataHewan[i])
                            }
                            if (GlobalVar.tempDataHewan.size > 0) {
                                viewBinding.AddDataTV.alpha = 0f
                            } else {
                                viewBinding.AddDataTV.alpha = 1f
                            }
                        }
                        viewBinding.listDataRV.adapter = adapterTemp
                        adapter.notifyDataSetChanged()
                    }
                    else -> {
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    android.R.string.no, Toast.LENGTH_LONG
                ).show()
            }
            builder.show()
        } else if (button == "edit") {
            val intent = Intent(this, FormActivity::class.java).putExtra("position", position)
            startActivity(intent)
        } else if (button == "feed") {
            for (i in 0 until GlobalVar.listDataHewan.size) {
                if (GlobalVar.listDataHewan[i].id == position) {
                    if (GlobalVar.listDataHewan[i] is ayam) {
                        Toast.makeText(
                            this,
                            GlobalVar.listDataHewan[i].makananAyam(Biji()),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (GlobalVar.listDataHewan[i] is sapi) {
                        Toast.makeText(
                            this,
                            GlobalVar.listDataHewan[i].makananSapi(Rumput()),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            GlobalVar.listDataHewan[i].makananKambing(Rumput()),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    break
                }
            }
        } else {
            for (i in 0 until GlobalVar.listDataHewan.size) {
                if (GlobalVar.listDataHewan[i].id == position) {
                    Toast.makeText(this, GlobalVar.listDataHewan[i].interaksi(), Toast.LENGTH_LONG)
                        .show()
                    break
                }
            }
        }
    }
}