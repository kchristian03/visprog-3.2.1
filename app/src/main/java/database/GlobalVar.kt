package database

import model.Animal

class GlobalVar {
    companion object {
        val STORAGERead_PERMISSION_CODE: Int = 2
        val STORAGEWrite_PERMISSION_CODE: Int = 3
        val listDataHewan = ArrayList<Animal>()
        val tempDataHewan = ArrayList<Animal>()
        var session = "test"
    }
}