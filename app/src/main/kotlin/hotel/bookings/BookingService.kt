package hotel.bookings

import hotel.EmployeeId
import hotel.RoomType
import hotel.Booking
import hotel.hotel.HotelService
import hotel.policies.BookingPolicyService
import hotel.HotelInfo
import hotel.Room
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
        validateDates(from, to)
        validatePermissions(employeeId, roomType)
       val hotelInfo = hotelService.findHotelBy(hotelId)
       val room = findAvailableRoom(hotelId, hotelInfo, roomType, from, to)
       return bookRoom(employeeId, hotelId, room, from, to)
   } 

   private fun validateDates(from: LocalDate, to: LocalDate) {
        if (ChronoUnit.DAYS.between(from, to) <= 1L)
            throw InvalidTimeframeForBooking()
   }

   private fun validatePermissions(employeeId: EmployeeId, roomType: RoomType) {
        if (!bookingPolicyService.isBookingAllowed(employeeId, roomType))
            throw BookingDenied()
   }

   private fun findAvailableRoom(
       hotelId: Int,
       hotelInfo: HotelInfo, 
       roomType: RoomType, 
       from: LocalDate,
       to: LocalDate
    ): Room {
       val room = 
        hotelInfo.findRoomsBy(roomType)
            .filter { room -> isAlreadyBooked(hotelId, room, from, to) }
            .firstOrNull()

       if (room == null)
        throw NoRoomTypeAvailable()
       return room
   }

   private fun isAlreadyBooked(hotelId: Int, room: Room, from: LocalDate, to: LocalDate): Boolean =
       bookingsRepository.findBy(hotelId, room.number) 
           .filter { from.isBefore(it.to) || (to.isAfter(it.from) && to.isBefore(it.to)) }
           .count() == 0

    private fun bookRoom(employeeId: EmployeeId, hotelId: Int, room: Room, from: LocalDate, to: LocalDate) =
       bookingsRepository.add(employeeId, hotelId, room.number, from, to)
    
}

class InvalidTimeframeForBooking: Throwable()
class NoRoomTypeAvailable: Throwable()
class BookingDenied: Throwable()
