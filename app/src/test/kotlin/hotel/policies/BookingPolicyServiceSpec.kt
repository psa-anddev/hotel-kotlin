package hotel.policies

import io.kotest.matchers.*
import io.kotest.core.spec.style.*
import io.mockk.*
import hotel.company.CompanyService
import hotel.company.EmployeeRepository
import hotel.EmployeeId
import hotel.CompanyId
import hotel.Employee
import hotel.RoomType
import hotel.RoomType.*
import kotlin.collections.emptyList

class BookingPolicyServiceSpec: ShouldSpec({
   should("be able to book all room types when no policies are defined") {
       val employeesRepository = mockk<EmployeeRepository>()
       val companyPoliciesRepository = mockk<CompanyPoliciesRepository>()
       val employeePoliciesRepository = mockk<EmployeePoliciesRepository>()
       val bookingPolicyService = BookingPolicyService(employeesRepository, companyPoliciesRepository, employeePoliciesRepository)
       val employeeId = EmployeeId(80)
       val companyId = CompanyId(85)

       every { employeesRepository.findBy(employeeId) } returns Employee(companyId, employeeId)
       every { employeePoliciesRepository.findBy(employeeId) } returns emptyList()
       every { companyPoliciesRepository.findBy(companyId) } returns emptyList()

       bookingPolicyService.isBookingAllowed(employeeId, RoomType.SINGLE) shouldBe true
       bookingPolicyService.isBookingAllowed(employeeId, RoomType.DOUBLE) shouldBe true
       bookingPolicyService.isBookingAllowed(employeeId, RoomType.JUNIOR_SUITE) shouldBe true
       bookingPolicyService.isBookingAllowed(employeeId, RoomType.MASTER_SUITE) shouldBe true

   }

   should("be able to book a single room when allowed by company policy") {
       val employeesRepository = mockk<EmployeeRepository>()
       val companyPoliciesRepository = mockk<CompanyPoliciesRepository>()
       val employeePoliciesRepository = mockk<EmployeePoliciesRepository>()
       val bookingPolicyService = BookingPolicyService(employeesRepository, companyPoliciesRepository, employeePoliciesRepository)
       val employeeId = EmployeeId(80)
       val companyId = CompanyId(85)

       every { employeesRepository.findBy(employeeId) } returns Employee(companyId, employeeId)
       every { employeePoliciesRepository.findBy(employeeId) } returns emptyList()
       every { companyPoliciesRepository.findBy(companyId) } returns listOf(SINGLE)

       bookingPolicyService.isBookingAllowed(employeeId, SINGLE) shouldBe true
   }

   should("not be able to book a double room when company policy allows only single room bookings") {
       val employeesRepository = mockk<EmployeeRepository>()
       val companyPoliciesRepository = mockk<CompanyPoliciesRepository>()
       val employeePoliciesRepository = mockk<EmployeePoliciesRepository>()
       val bookingPolicyService = BookingPolicyService(employeesRepository, companyPoliciesRepository, employeePoliciesRepository)
       val employeeId = EmployeeId(80)
       val companyId = CompanyId(85)

       every { employeesRepository.findBy(employeeId) } returns Employee(companyId, employeeId)
       every { employeePoliciesRepository.findBy(employeeId) } returns emptyList()
       every { companyPoliciesRepository.findBy(companyId) } returns listOf(SINGLE)

       bookingPolicyService.isBookingAllowed(employeeId, DOUBLE) shouldBe false
   }

   should("be able to book a single room when allowed by employee policy") {
       val employeesRepository = mockk<EmployeeRepository>()
       val companyPoliciesRepository = mockk<CompanyPoliciesRepository>()
       val employeePoliciesRepository = mockk<EmployeePoliciesRepository>()
       val bookingPolicyService = BookingPolicyService(employeesRepository, companyPoliciesRepository, employeePoliciesRepository)
       val employeeId = EmployeeId(80)
       val companyId = CompanyId(85)

       every { employeesRepository.findBy(employeeId) } returns Employee(companyId, employeeId)
       every { employeePoliciesRepository.findBy(employeeId) } returns listOf(SINGLE)
       every { companyPoliciesRepository.findBy(companyId) } returns emptyList()

       bookingPolicyService.isBookingAllowed(employeeId, SINGLE) shouldBe true
   }

   should("not be able to book a double room when employee policy allows only single room bookings") {
       val employeesRepository = mockk<EmployeeRepository>()
       val companyPoliciesRepository = mockk<CompanyPoliciesRepository>()
       val employeePoliciesRepository = mockk<EmployeePoliciesRepository>()
       val bookingPolicyService = BookingPolicyService(employeesRepository, companyPoliciesRepository, employeePoliciesRepository)
       val employeeId = EmployeeId(80)
       val companyId = CompanyId(85)

       every { employeesRepository.findBy(employeeId) } returns Employee(companyId, employeeId)
       every { employeePoliciesRepository.findBy(employeeId) } returns listOf(SINGLE)
       every { companyPoliciesRepository.findBy(companyId) } returns emptyList()

       bookingPolicyService.isBookingAllowed(employeeId, DOUBLE) shouldBe false
   }

   should("be able to book a single room when employee is allowed to boook single and junior suites but only double by company policy") {
       val employeesRepository = mockk<EmployeeRepository>()
       val companyPoliciesRepository = mockk<CompanyPoliciesRepository>()
       val employeePoliciesRepository = mockk<EmployeePoliciesRepository>()
       val bookingPolicyService = BookingPolicyService(employeesRepository, companyPoliciesRepository, employeePoliciesRepository)
       val employeeId = EmployeeId(80)
       val companyId = CompanyId(85)

       every { employeesRepository.findBy(employeeId) } returns Employee(companyId, employeeId)
       every { employeePoliciesRepository.findBy(employeeId) } returns listOf(SINGLE, JUNIOR_SUITE)
       every { companyPoliciesRepository.findBy(companyId) } returns listOf(DOUBLE)

       bookingPolicyService.isBookingAllowed(employeeId, SINGLE) shouldBe true
   }


   should("add company policies") {
       val companyPoliciesRepository = mockk<CompanyPoliciesRepository>()
       val service = BookingPolicyService(mockk(), companyPoliciesRepository, mockk())
       val companyId = CompanyId(80)
       val roomTypes = listOf(SINGLE, JUNIOR_SUITE)

       every { companyPoliciesRepository.add(companyId, roomTypes) } just Runs

       service.setCompanyPolicy(companyId, SINGLE, JUNIOR_SUITE)

       verify { companyPoliciesRepository.add(companyId, roomTypes) }
   }

   should("set employee policies") {
       val employeePoliciesRepository = mockk<EmployeePoliciesRepository>()
       val service = BookingPolicyService(mockk(), mockk(), employeePoliciesRepository)
       val employeeId = EmployeeId((1..Int.MAX_VALUE).random())

       every { employeePoliciesRepository.add(employeeId, listOf(DOUBLE, MASTER_SUITE)) } just Runs

       service.setEmployeePolicy(employeeId, DOUBLE, MASTER_SUITE)

       verify { employeePoliciesRepository.add(employeeId, listOf(DOUBLE, MASTER_SUITE)) }
   }
})
