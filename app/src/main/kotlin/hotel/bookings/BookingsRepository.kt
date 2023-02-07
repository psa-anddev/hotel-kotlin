package hotel.bookings

import hotel.Booking
import hotel.EmployeeId
import java.time.LocalDate

class BookingsRepository {
   fun findBy(hotelId: Int, roomNumber: Int): List<Booking> {
       TODO("Not implemented")
   } 

   fun add(employeeId: EmployeeId, hotelId: Int, roomNumber: Int, from: LocalDate, to: LocalDate): Booking {
       return Booking(employeeId, hotelId, roomNumber, from, to)
   }
}
