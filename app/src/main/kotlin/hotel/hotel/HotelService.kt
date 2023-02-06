package hotel.hotel

import hotel.RoomType

class HotelService(
        private val hotelRepository: HotelRepository,
        private val roomRepository: RoomRepository) {
    fun addHotel(id: Int, name: String) {
        if (hotelRepository.findBy(id) != null)
            throw HotelExists(id)

        hotelRepository.add(id, name)
    }

    fun setRoom(hotelId: Int, roomNumber: Int, roomType: RoomType) {
        if (hotelRepository.findBy(hotelId) == null)
            throw HotelDoesNotExist(hotelId)

        roomRepository.add(hotelId, roomNumber, roomType)
    }
    
}

class HotelExists(id: Int) : 
    Throwable("Hotel with ID $id already exists")

class HotelDoesNotExist(id: Int) : 
    Throwable("Hotel with ID $id does not exist")
