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
   fun isBookingAllowed(employeeId: EmployeeId, roomType: RoomType): Boolean {
       val employee = employeesRepository.findBy(employeeId)
       val companyPolicy = companyPoliciesRepository.findBy(employee.company)
       return companyPolicy.isEmpty() || companyPolicy.contains(roomType)
   } 
}
