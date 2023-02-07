package hotel.steps

import io.cucumber.java8.En
import io.kotest.matchers.*
import java.time.*
import java.time.format.*
import hotel.*
import hotel.bookings.*

class BookingSteps(private val bookingService: BookingService): En {
    private var booking: Booking? = null
    init {
        ParameterType("date", "\\d{2}/\\d{2}/\\d{4}") {
            dateString: String -> LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }

        When("{employee} books a {room-type} room in {hotel} from {date} to {date}") {
            employeeId: EmployeeId, roomType: RoomType, hotel: Hotel,
            from: LocalDate, to: LocalDate ->
            booking = bookingService.book(employeeId, hotel.id, roomType, from, to)
        }

        Then("{employee} gets a booking confirmation for room {int} in {hotel} from {date} to {date}") {
            employeeId: EmployeeId, roomNumber: Int, hotel: Hotel,
            from: LocalDate, to: LocalDate ->
                val expectedBooking = Booking(employeeId, hotel.id, roomNumber, from, to)
                booking shouldBe expectedBooking
        }
    }
    
}
