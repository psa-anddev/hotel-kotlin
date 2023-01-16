package hotel.steps

import io.cucumber.java8.En

class HotelSteps: En {
    init {
        Given("{string} belongs to {string}") { hotelName: String, companyName: String ->
        }
    }
}
