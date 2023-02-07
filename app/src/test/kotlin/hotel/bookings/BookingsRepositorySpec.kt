package hotel.bookings

import io.kotest.matchers.*
import io.kotest.matchers.collections.*
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

    should("return an empty list if there are no bookings for the given room") {
        val repository = BookingsRepository()

        repository.findBy(200, 103).shouldBeEmpty()
    }

    should("return the only registered booking when finding for the same hotel and room") {
        val repository = BookingsRepository()
        val hotelId = 1066
        val roomNumber = 201
        val expectedBooking = repository.add(EmployeeId(123), hotelId, roomNumber, LocalDate.now(), LocalDate.now().plusDays(15))

        repository.findBy(hotelId, roomNumber) should containAll(expectedBooking)
    }

    should("only return bookings for the same hotel and room") {
        val repository = BookingsRepository()
        val hotelId = 1066
        val roomNumber = 201
        repository.add(EmployeeId(321), 1245, 302, LocalDate.now().minusDays(20), LocalDate.now())
        val expectedBooking = repository.add(EmployeeId(123), hotelId, roomNumber, LocalDate.now(), LocalDate.now().plusDays(15))

        repository.findBy(hotelId, roomNumber) should containOnly(expectedBooking)
    }
})
