package hotel.steps

import hotel.*
import hotel.company.*
import hotel.steps.status.*
import io.cucumber.java8.En
import kotlin.io.println
import kotlin.collections.mutableMapOf 

class EmployeeSteps(
    private val employeeStatus: EmployeeStatus, 
    private val companyStatus: CompanyStatus,
    private val companyService: CompanyService): En {

    init {
        ParameterType("employee", "\"(.*)\"") { employeeName: String ->
           employeeStatus.findBy(employeeName)
        }

        ParameterType("company", "\"(.*)\"") { companyName: String ->
            companyStatus.findBy(companyName)
        }

        Given("{employee} works for {company}") { employee: EmployeeId, company: CompanyId ->
            companyService.addEmployee(company, employee)
        }
    }
}
