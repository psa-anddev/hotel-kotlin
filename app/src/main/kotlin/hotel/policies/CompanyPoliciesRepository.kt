package hotel.policies

import hotel.CompanyId
import hotel.RoomType
import kotlin.collections.mutableMapOf

class CompanyPoliciesRepository {
    private val policiesMap = mutableMapOf<CompanyId, List<RoomType>>()

    fun findBy(companyId: CompanyId): List<RoomType> =
        policiesMap[companyId] ?: emptyList()

    fun add(companyId: CompanyId, roomTypes: List<RoomType>) {
        policiesMap += (companyId to roomTypes)
    }
}
