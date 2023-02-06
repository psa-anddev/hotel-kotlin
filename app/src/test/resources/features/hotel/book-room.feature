Feature: Book a room
    As an employee,
    I want to book a room,
    So that my hotel can host my guest.

    Scenario: Booking confirmation received upon successful booking
        Given "Diana Prince" works for "Meliá"
        And "Robert Johnson" is a hotel manager
        And "Robert Johnson" added "Hotel Los Lebreros"
        And "Hotel Los Lebreros" room 230 is a "single" room
