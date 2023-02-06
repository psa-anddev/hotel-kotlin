package hotel.hotel

import hotel.*
import kotlin.collections.mutableListOf

class HotelRepository {
    private val hotels = mutableListOf<Hotel>()

    fun findBy(id: Int): Hotel? =
        hotels.firstOrNull { it.id == id }

    fun add(id: Int, name: String) {
        hotels += Hotel(id, name)
    }
}
