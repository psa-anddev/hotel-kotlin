package hotel.bookings

import hotel.EmployeeId
import hotel.RoomType
import hotel.Booking
import hotel.hotel.HotelService
import hotel.policies.BookingPolicyService
import java.time.LocalDate

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
       val hotelInfo = hotelService.findHotelBy(hotelId)
       val room = hotelInfo.findRoomsBy(roomType).first()
       return bookingsRepository.add(employeeId, hotelId, room.number, from, to)
   } 
}
