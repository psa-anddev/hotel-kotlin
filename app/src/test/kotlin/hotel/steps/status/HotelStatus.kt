package hotel.steps.status

import hotel.*

class HotelStatus {
    private val hotels = mutableListOf<Hotel>()

    fun findBy(name: String): Hotel =
        hotels.firstOrNull { it.name == name } ?:
            Hotel(hotels.size + 1, name)
                .apply { hotels += this }
}
