package hotel.policies

import hotel.EmployeeId
import hotel.RoomType
import kotlin.collections.mutableMapOf

class EmployeePoliciesRepository {
    private val employeePolicy = mutableMapOf<EmployeeId, List<RoomType>>()

    fun add(employeeId: EmployeeId, roomTypes: List<RoomType>) {
        employeePolicy[employeeId] = roomTypes
    }
   fun findBy(employeeId: EmployeeId): List<RoomType> {
       return employeePolicy[employeeId] ?: emptyList()
   } 
}
