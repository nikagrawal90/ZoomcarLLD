# ZoomcarLLD
LLD for Zoomcar like system

## Design

### Functional Requirements
1. User should be able to search vehicles available on a specific date
2. User should be able to book any vehicle for a date after choosing his payment method
3. User should be able to search and sort based on Vehicle, Time, Price
4. User Should see data related to car like no of km used, model, etc

### Non Functional Requirements

### Models
1. User (id, name, contactNo, email, bookings, accountDetails: Nullable, vehiclesOwned)
2. Vehicle (id, numberPlate: String, distance, Model, BasePricePerHour, owner: User, available: bool, startTime: Nullable, endTime: Nullable)
3. Booking (id, user, vehicle, startTime, endTime, paymentDetails, totalPayment)

### Controller Layer
UserController (UserService) -> (addUser, getBookings, getActiveBooking: Optional<Booking>, searchVehicle, bookVehicle (paymentMethod), addVehicle, verifyPayment)

### Service Layer
1. UserService (UserRepository) -> (addUser)
2. VehicleService (VehicleRepository) -> (addVehicle, searchVehicle)
3. BookingService (BookingRepository, VehicleRepository) -> (getBookings, getActiveBooking, bookVehicle: paymentId, -sendPaymentToOwner: private)

### Repository Layer
1. UserRepository -> (save, getBookingByUserId, getActiveBookingByUserId)
2. VehicleRepository -> (save, searchVehicle)
3. BookingRepository -> (save)

### Database Table Design
TBD
