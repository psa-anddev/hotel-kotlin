package hotel.hotel

import hotel.*
import kotlin.collections.mutableMapOf

class RoomRepository {
    private val rooms = mutableMapOf<Int, List<Room>>()
    fun add(hotelId: Int, room: Int, type: RoomType) {
        rooms[hotelId] = findBy(hotelId) + Room(room, type)
    }

    fun findBy(hotelId: Int): List<Room> =
        rooms[hotelId] ?: emptyList()
    
}
