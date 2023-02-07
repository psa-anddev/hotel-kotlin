package hotel.steps

import io.cucumber.java8.En
import io.kotest.matchers.*
import io.kotest.matchers.types.*
import java.time.*
import java.time.format.*
import hotel.*
import hotel.bookings.*
import hotel.hotel.HotelDoesNotExist

class BookingSteps(
    private val bookingService: BookingService,
    private val bookingRepository: BookingsRepository): En {
    private var booking: Booking? = null
    private var error: Throwable? = null
    init {
        ParameterType("date", "\\d{2}/\\d{2}/\\d{4}") {
            dateString: String -> LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }

        Given("{hotel} room {int} is booked from {date} to {date}") { 
            hotel: Hotel, roomNumber: Int, from: LocalDate, to:LocalDate ->
                bookingRepository.add(EmployeeId(200), hotel.id, roomNumber, from, to)
        }

        When("{employee} books a {room-type} room in {hotel} from {date} to {date}") {
            employeeId: EmployeeId, roomType: RoomType, hotel: Hotel,
            from: LocalDate, to: LocalDate ->
            try {
                booking = bookingService.book(employeeId, hotel.id, roomType, from, to)
            } catch(e: Throwable) { error = e }
        }

        Then("{employee} gets a booking confirmation for room {int} in {hotel} from {date} to {date}") {
            employeeId: EmployeeId, roomNumber: Int, hotel: Hotel,
            from: LocalDate, to: LocalDate ->
                val expectedBooking = Booking(employeeId, hotel.id, roomNumber, from, to)
                booking shouldBe expectedBooking
        }

        Then("{employee} gets an invalid timeframe for booking error") { employee: EmployeeId ->
            error.shouldBeInstanceOf<InvalidTimeframeForBooking>()
        }

        Then("{employee} gets a hotel does not exist error") { _: EmployeeId ->
            error.shouldBeInstanceOf<HotelDoesNotExist>()
        }

        Then("{employee} gets a no room type available error") { _: EmployeeId ->
            error.shouldBeInstanceOf<NoRoomTypeAvailable>()
        }

        Then("{employee} gets a booking denied error") { _: EmployeeId -> 
            error.shouldBeInstanceOf<BookingDenied>()
        }
    }
    
}
