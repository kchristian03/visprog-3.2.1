package model

class kambing(
    namaHewan: String,
    jenisHewan: String,
    usiaHewan: Int,
    imageuri: String,
    id: Int
) : Animal(namaHewan, jenisHewan, usiaHewan, imageuri, id) {
    override fun interaksi(): String {
        return "Mbek Mbeek Mbeeek"
    }
}