package hotel.steps

import io.cucumber.java8.En
import hotel.*
import hotel.hotel.*
import hotel.steps.status.*

class HotelSteps(
        private val hotelStatus: HotelStatus, 
        private val hotelService: HotelService): En {
    init {
        ParameterType("hotel", "\".*\"") { name: String ->
            hotelStatus.findBy(name)
        }
        Given("{string} is a hotel manager") { _: String ->
        }

        Given("{string} added {hotel}") { _: String, hotel: Hotel ->
            hotelService.addHotel(hotel.id, hotel.name)
        }

        Given("{hotel} room {int} is a {room-type} room") { hotel: Hotel, roomNumber: Int, roomType: RoomType ->
            hotelService.setRoom(hotel.id, roomNumber, roomType)
        }
    }
}
