package hotel

data class HotelInfo(
    private val hotel: Hotel,
    private val rooms: List<Room>) {
    fun findRoomsBy(roomType: RoomType): List<Room> {
        return rooms.filter { it.type == roomType }
    }
}
