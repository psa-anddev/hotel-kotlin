package hotel.steps.status 

import hotel.*

class EmployeeStatus {
    private val employeeMap = mutableMapOf<String, EmployeeId>()
    
    fun findBy(name: String) = 
        employeeMap.getOrPut(name) { EmployeeId(employeeMap.size + 1) }
}
