package hotel.bookings

import hotel.Booking
import hotel.EmployeeId
import java.time.LocalDate
import kotlin.collections.emptyList

class BookingsRepository {
    private val bookings = mutableListOf<Booking>()
   fun findBy(hotelId: Int, roomNumber: Int): List<Booking> =
       bookings.filter { it.hotelId == hotelId && it.roomNumber == roomNumber }

   fun add(employeeId: EmployeeId, hotelId: Int, roomNumber: Int, from: LocalDate, to: LocalDate): Booking =
       Booking(employeeId, hotelId, roomNumber, from, to).also { bookings += it }
}
