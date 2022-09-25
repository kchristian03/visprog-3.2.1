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

    fun makanan(i: Biji): String {
        return "makan biji"
    }

    fun <Int> makanan(j: Rumput): String {
        return "makan rumput"
    }

    open fun interaksi(): String {
        return ""
    }
}