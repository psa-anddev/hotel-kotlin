package hotel

import java.time.LocalDate

data class Booking(
    val employeeId: EmployeeId,  
    val hotelId: Int, 
    val roomNumber: Int, 
    val from: LocalDate,
    val to: LocalDate)
