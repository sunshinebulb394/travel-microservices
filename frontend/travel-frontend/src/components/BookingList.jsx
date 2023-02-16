import { useState } from "react";
import SearchBox from "./SearchBox";

function BookingList(){
    const [bookings, setBookings] = useState([]);
    const [updateBooking, setUpdateBooking] = useState({});
    const [successMessage, setSuccessMessage] = useState("");
      
    const handleSearch = async (query) => {
        try {
            const response = await fetch(`http://localhost:8081/booking/search?id=${query}`, {
                headers: {
                  'Content-Type': 'text/plain',
                  Authorization: 'Bearer gkb0201219789',
                },
              });
          
          const data = await response.json();
          setBookings(data);
          console.log(data);
        } catch (err) {
          console.log(err);
        }
      };
    
    const handleUpdate = (booking) => {
        setUpdateBooking(booking);
    }
    
    const handleFormSubmit = async (e) => {
      e.preventDefault();
      const formData = new FormData(e.target);
      const updatedBooking = {
        passengerName: formData.get("passengerName"),
        pickupTime: formData.get("pickupTime"),
        travelDate: formData.get("travelDate"),
        pickupLocation: formData.get("pickupLocation"),
        destination: formData.get("destination"),
        busType: formData.get("busType"),
        phoneNumber: formData.get("phoneNumber"),
      };
    
      try {
        const response = await fetch(
          `http://localhost:8081/booking/update-booking?booking-id=${updateBooking.id}`,
          {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
              Authorization: "Bearer gkb0201219789",
            },
            body: JSON.stringify(updatedBooking),
          }
        );
        console.log(response.status);
        if (response.ok) { 
          setSuccessMessage("Booking successful");
          const data = await response.json();
          setUpdateBooking({});
          handleSearch(data.id);
         
        } else {
          console.log("Server responded with an error");
        }
      } catch (err) {
        console.log(err);
      }
    };
    
    
      return (
        <div>
          <SearchBox onSearch={handleSearch} />
          <table>
            <thead>
              <tr>
                <th>Booking Number</th>
                <th>Passenger Name</th>
                <th>Pickup Time</th>
                <th>Travel Date</th>
                <th>Pickup Location</th>
                <th>Destination</th>
                <th>Bus Type</th>
                <th>Phone Number</th>
              </tr>
            </thead>
            <tbody>
              {bookings.map((booking) => (
                <tr key={booking.id}>
                  <td>{booking.bookingNumber}</td>
                  <td>{booking.passengerName}</td>
                  <td>{booking.pickupTime}</td>
                  <td>{booking.travelDate}</td>
                  <td>{booking.pickupLocation}</td>
                  <td>{booking.destination}</td>
                  <td>{booking.busType}</td>
                  <td>{booking.phoneNumber}</td>
                  <td><button type="button" className="btn btn-success btn-sm" onClick={() => handleUpdate(booking)}>
                  <i class="bi bi-pencil-square"></i> &nbsp;Update
                 </button></td>
                 <td><button type="button" className="btn btn-secondary btn-sm">
                 <i class="bi bi-ticket"></i> Generate Ticket</button></td>
                </tr>
                ))}
                </tbody>
                </table>
                {Object.keys(updateBooking).length > 0 && (
    <div>
      <h3>Update Booking</h3>
      <form onSubmit={handleFormSubmit}>
        <div className="form-group">
          <label htmlFor="passengerName">Passenger Name</label>
          <input
            type="text"
            name="passengerName"
            id="passengerName"
            defaultValue={updateBooking.passengerName}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="pickupTime">Pickup Time</label>
          <input
            type="time"
            name="pickupTime"
            id="pickupTime"
            defaultValue={updateBooking.pickupTime}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="travelDate">Travel Date</label>
          <input
            type="date"
            name="travelDate"
            id="travelDate"
            defaultValue={updateBooking.travelDate}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="pickupLocation">Pickup Location</label>
          <input
            type="text"
            name="pickupLocation"
            id="pickupLocation"
            defaultValue={updateBooking.pickupLocation}
            required
          />
        </div>
        <div className="form-group">
            <label htmlFor="destination">Destination</label>
            <select name="destination" id="destination" defaultValue={updateBooking.destination} >
                <option value="Koforidua">Koforidua</option>
                <option value="Kumasi">Kumasi</option>
                <option value="Cape Coast">Cape Coast</option>
                <option value="Bolgatanga">Bolgatanga</option>
                <option value="Accra">Accra</option>
            </select>
            </div>
        <div className="form-group">
          <label htmlFor="busType">Bus Type</label>
          <select
            name="busType"
            id="busType"
            defaultValue={updateBooking.busType}
            required
          >
            <option value="VIP">VIP</option>
            <option value="VVIP">VVIP</option>
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="phoneNumber">Phone Number</label>
          <input
            type="tel"
            name="phoneNumber"
            id="phoneNumber"
            defaultValue={updateBooking.phoneNumber}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Update Booking
        </button>
      </form>
      
    </div>
  )}
  <p> {successMessage && <p>{successMessage}</p>}</p>
  
</div>
      );


}
export default BookingList;
