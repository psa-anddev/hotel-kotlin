package hotel.bookings

import hotel.EmployeeId
import hotel.RoomType
import hotel.Booking
import hotel.hotel.HotelService
import hotel.policies.BookingPolicyService
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.time.days

class BookingService(
    private val hotelService: HotelService,
    private val bookingPolicyService: BookingPolicyService,
    private val bookingsRepository: BookingsRepository
) {
   fun book(employeeId: EmployeeId, 
        hotelId: Int, 
        roomType: RoomType, 
        from: LocalDate, 
        to: LocalDate): Booking {
        if (ChronoUnit.DAYS.between(from, to) <= 1L)
            throw InvalidTimeframeForBooking()
       val hotelInfo = hotelService.findHotelBy(hotelId)
       val room = hotelInfo.findRoomsBy(roomType).firstOrNull()
       if (room == null)
        throw NoRoomTypeAvailable()
       return bookingsRepository.add(employeeId, hotelId, room.number, from, to)
   } 
}

class InvalidTimeframeForBooking: Throwable()
class NoRoomTypeAvailable: Throwable()
