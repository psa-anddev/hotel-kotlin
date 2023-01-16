package hotel.policies

import hotel.EmployeeId
import hotel.RoomType
import hotel.company.EmployeeRepository

class BookingPolicyService(
    private val employeesRepository: EmployeeRepository,
    private val companyPoliciesRepository: CompanyPoliciesRepository, 
    private val employeePoliciesRepository: EmployeePoliciesRepository) {

   fun isBookingAllowed(employee: EmployeeId, roomType: RoomType): Boolean {
       return true
   } 
}
