package hotel.bookings

import io.kotest.matchers.*
import io.kotest.assertions.throwables.*
import io.kotest.core.spec.style.*
import io.mockk.*
import java.time.*
import hotel.Booking
import hotel.EmployeeId

class BookingsRepositorySpec: ShouldSpec({
    should("return the booking that was added") {
        val repository = BookingsRepository()
        val employeeId = EmployeeId(1540)
        val hotelId = 620
        val roomNumber = 507
        val from = LocalDate.parse("2023-04-01")
        val to = LocalDate.parse("2023-04-10")
        val expectedBooking = 
            Booking(employeeId, hotelId, roomNumber, from, to)

        val actualBooking = 
            repository.add(employeeId, hotelId, roomNumber, from, to)

        actualBooking shouldBe expectedBooking
    }
})
