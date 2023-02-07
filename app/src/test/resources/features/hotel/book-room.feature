Feature: Book a room
    As an employee,
    I want to book a room,
    So that my hotel can host my guest.

    Scenario: Booking confirmation received upon successful booking
        Given "Diana Prince" works for "Meliá"
        And "Robert Johnson" is a hotel manager
        And "Robert Johnson" added "Hotel Los Lebreros"
        And "Hotel Los Lebreros" room 230 is a "single" room
        When "Diana Prince" books a "single" room in "Hotel Los Lebreros" from 10/02/2023 to 12/02/2023
        Then "Diana Prince" gets a booking confirmation for room 230 in "Hotel Los Lebreros" from 10/02/2023 to 12/02/2023

    Scenario: Check out date same as check in date 
        Given "Bruce Wayne" works for "NH"
        And "Rubin Monkey" is a hotel manager 
        And "Rubin Monkey" added "Hotel NH Barcelona"
        And "Hotel NH Barcelona" room 540 is a "junior suite" room 
        When "Bruce Wayne" books a "junior suite" room in "Hotel NH Barcelona" from 15/05/2023 to 15/05/2023 
        Then "Bruce Wayne" gets an invalid timeframe for booking error

    Scenario: Booking in a hotel that does not exist
        Given "Peter Parker" works for "Travelodge"
        And there is no such hotel as "Hotel Humility"
        When "Peter Parker" books a "double" room in "Hotel Humility" from 20/06/2023 to 22/06/2023 
        Then "Peter Parker" gets a hotel does not exist error

    Scenario: Hotel without the provided room type
        Given "Optimus Prime" works for "Hilton"
        And "Steve Harris" is a hotel manager
        And "Steve Harris" added "Hilton London"
        And "Hilton London" doesn't have any "single" rooms
        When "Optimus Prime" books a "single" room in "Hilton London" from 21/01/2024 to 21/02/2024 
        Then "Optimus Prime" gets a no room type available error

    Scenario: Booking policies prevent the employee from booking a room of the given type 
        Given "Bruce Banner" works for "easyHotel"
        And "Scott Lang" is a hotel manager 
        And "Scott Lang" added "Hotel Marvel"
        And "Bruce Banner" can book "double" rooms 
        When "Bruce Banner" books a "junior suite" room in "Hotel Marvel" from 12/12/2023 to 15/12/2023 
        Then "Bruce Banner" gets a booking denied error
