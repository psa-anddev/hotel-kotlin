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

    fun setEmployeePolicy(employeeId: EmployeeId, roomType: RoomType, vararg additionalRoomTypes: RoomType) {
        employeePoliciesRepository.add(employeeId, listOf(roomType) + additionalRoomTypes)
    }
   fun isBookingAllowed(employeeId: EmployeeId, roomType: RoomType): Boolean =
       with( employeesRepository.findBy(employeeId)) {
           employee.canBook(roomType) ?: company.canBook(roomType)
       }

   private fun EmployeeId.canBook(roomType: RoomType) : Boolean? {
       with(employeePoliciesRepository.findBy(this)) {
           if (isEmpty())
            return null 
           else 
            return contains(roomType)
       }
   }

   private fun CompanyId.canBook(roomType: RoomType): Boolean =
        with(companyPoliciesRepository.findBy(this)) {
            isEmpty() || contains(roomType)
        }
}
