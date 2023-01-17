package hotel.policies

import hotel.EmployeeId
import hotel.RoomType
import hotel.company.EmployeeRepository
import hotel.CompanyId

class BookingPolicyService(
    private val employeesRepository: EmployeeRepository,
    private val companyPoliciesRepository: CompanyPoliciesRepository, 
    private val employeePoliciesRepository: EmployeePoliciesRepository) {

    fun setCompanyPolicy(companyId: CompanyId, roomType: RoomType, vararg additionalRoomTypes: RoomType) {
        companyPoliciesRepository.add(companyId, listOf(roomType) + additionalRoomTypes)
    }
   fun isBookingAllowed(employee: EmployeeId, roomType: RoomType): Boolean {
       return true
   } 
}
