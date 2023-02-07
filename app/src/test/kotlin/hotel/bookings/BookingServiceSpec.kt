package hotel.bookings

import io.kotest.matchers.*
import io.kotest.core.spec.style.*
import io.mockk.*
import hotel.hotel.HotelService
import hotel.*
import hotel.policies.BookingPolicyService
import kotlin.collections.emptyList
import java.time.LocalDate

class BookingServiceSpec: ShouldSpec({
   should("book a room") {
       val hotelService = mockk<HotelService>()
       val bookingPolicyService = mockk<BookingPolicyService>()
       val bookingsRepository = mockk<BookingsRepository>()
       val bookingService = 
           BookingService(hotelService, bookingPolicyService, bookingsRepository)
       val employeeId = EmployeeId(1066)
       val hotelId = 500
       val roomNumber = 300
       val hotelInfo = 
            HotelInfo(
                Hotel(500, "Premier Inn Croydon"),
                listOf(Room(roomNumber, RoomType.SINGLE)))
       val from = LocalDate.parse("2023-02-20")
       val to = LocalDate.parse("2023-02-22")
       val expectedBooking = Booking(employeeId, hotelId, roomNumber, from, to)

       every { hotelService.findHotelBy(hotelId) } returns hotelInfo
       every { bookingPolicyService.isBookingAllowed(employeeId, RoomType.SINGLE) } returns true
       every { bookingsRepository.findBy(hotelId, roomNumber) } returns emptyList()
       every { bookingsRepository.add(employeeId, hotelId, roomNumber, from, to) } returns expectedBooking

       val actualBooking = bookingService.book(employeeId, hotelId, RoomType.SINGLE, from, to)

       actualBooking shouldBe expectedBooking
   }
})
