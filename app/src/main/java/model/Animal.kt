package model

open class Animal(
    namaHewan: String,
    jenisHewan: String,
    usiaHewan: Int,
    imageUri: String,
    id: Int
) {
    var namahewan: String = namaHewan
    var jenishewan: String = jenisHewan
    var usiahewan: Int = usiaHewan
    var imageUri: String = imageUri
    var id: Int = id

    fun makananAyam(i: Biji): String {
        return "Memberi makan ayam dengan biji"
    }

    fun makananSapi(j: Rumput): String {
        return "Memberi makan sapi dengan rumput"
    }

    fun makananKambing(k: Rumput): String {
        return "Memberi makan kambing dengan rumput"
    }

    open fun interaksi(): String {
        return ""
    }
}