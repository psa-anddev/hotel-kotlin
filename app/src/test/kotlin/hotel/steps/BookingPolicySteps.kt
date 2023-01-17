package hotel.steps

import hotel.*
import hotel.policies.*
import hotel.RoomType.*
import io.cucumber.java8.*
import hotel.steps.status.EmployeeStatus
import io.kotest.matchers.*

class BookingPolicySteps(private val bookingPolicyService: BookingPolicyService): En {
    private var bookingAllowed: Boolean? = null

    init {
        ParameterType("room-type", "\"(single|double|junior suite|master suite)\"") { typeString: String ->
            when(typeString) {
                "single" -> SINGLE
                "double" -> DOUBLE
                "junior suite" -> JUNIOR_SUITE
                "master suite" -> MASTER_SUITE
                else -> throw Exception("Wrong type: $typeString")
            }
        }

        ParameterType("policy-value", "(allowed|denied)") { string: String -> string == "allowed" }

        Given("no company booking policy has been established for {company}") { company: CompanyId ->
        }

        Given("no employee booking policy has been established for {employee}") { employee: EmployeeId ->
        }

        Given("{company} employees can book {room-type}") { companyId: CompanyId, roomType: RoomType ->
            bookingPolicyService.setCompanyPolicy(companyId, roomType)
        }

        Given("{employee} can book {room-type} rooms") { employeeId: EmployeeId, roomType: RoomType -> 
            bookingPolicyService.setEmployeePolicy(employeeId, roomType)
        }

        When("I check if {employee} can book a {room-type} room") { employee: EmployeeId, roomType: RoomType ->
            bookingAllowed = bookingPolicyService.isBookingAllowed(employee, roomType)
        }

        Then("{employee} is {policy-value} to book a {room-type} room") { employee: EmployeeId, isAllowed: Boolean, roomType: RoomType ->
            bookingAllowed shouldBe isAllowed
        }
    }
}
