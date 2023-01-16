package hotel.steps.status

import hotel.*

class CompanyStatus {
    private val companyMap = mutableMapOf<String, CompanyId>()

    fun findBy(name: String): CompanyId =
        companyMap.getOrPut(name) { CompanyId(companyMap.size + 1) }
}
