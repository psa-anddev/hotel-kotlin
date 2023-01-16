package hotel.company

import hotel.*
import kotlin.collections.mutableListOf

class EmployeeRepository {
    private val employees = mutableListOf<Employee>()

   fun add(company: CompanyId, employee: EmployeeId) {
       employees += Employee(company, employee)
   } 

   fun findBy(employee: EmployeeId): Employee =
       employees.find { it.employee == employee } ?: throw Exception("Not found")
}
