import { useState } from "react";
import "../css/SearchForm.css"
import { Link } from 'react-router-dom';


function Booking() {
   
    const [passenger, setPassenger] = useState({
        passengerName: "",
        pickupTime: "",
        travelDate: "",
        pickupLocation: "",
        destination: "",
        busType: "",
        phoneNumber: "",
       
      });
      const [booking, setBooking] = useState(null); 
      const [successMessage, setSuccessMessage] = useState("");
      const [failedMessage, setfailedMessage] = useState("");
      const handleChange = (event) => {
        setPassenger({ ...passenger, [event.target.name]: event.target.value });
      };
    
      const handleSubmit = async (event) => {
        event.preventDefault();
        console.log(passenger);
        // Add your code to submit the form data to your backend here
        try {
          const response = await fetch("http://localhost:8081/booking/add", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: "Bearer gkb0201219789",
            },
            body: JSON.stringify(passenger),
          });
          //console.log(response.json());
          setBooking(await response.json());
          
        // Handle the response data here
        console.log(response.status);
        if (response.status === 201) {
            console.log(booking);
            setPassenger({
                passengerName: "",
                pickupTime: "",
                travelDate: "",
                pickupLocation: "",
                destination: "",
                busType: "",
                phoneNumber: ""
              });
              setSuccessMessage("Booking successful");
        } else {
            setfailedMessage("BookingFailed");
        }
        } catch (err) {
          console.log(err);
          // Handle any errors here
        }
      };
      

    
      return (
        
        <div>
            <Link to="/bookinglist">
            <button type="button" className="btn btn-success btn-sm">
            <i class="bi bi-book"></i> &nbsp; View Bookings
            </button>
        </Link>
            
        <form onSubmit={handleSubmit}>
           
          <label>
            Passenger Name:
            <input
              type="text"
              name="passengerName"
              value={passenger.passengerName}
              onChange={handleChange}
            />
          </label>
          <br />
          <label>
            Pickup Time:
            <input
              type="time"
              name="pickupTime"
              value={passenger.pickupTime}
              onChange={handleChange}
            />
          </label>
          <br />
          <label>
            Date For Travel:
            <input
              type="date"
              name="travelDate"
              value={passenger.dateBooked}
              onChange={handleChange}
            />
          </label>
          <br/>
          <label>
            Pickup Location:
            <input
              type="text"
              name="pickupLocation"
              value={passenger.pickupLocation}
              onChange={handleChange}
            />
          </label>
          <br />
          <label>
            Phone Number:
            <input
              type="text"
              name="phoneNumber"
              value={passenger.phoneNumber}
              onChange={handleChange}
            />
          </label>
          <br />
          <label>
            Destination:
            <select name="destination" value={passenger.destination} onChange={handleChange}>
                <option value="">Select a destination</option>
                <option value="Koforidua">Koforidua</option>
                <option value="Kumasi">Kumasi</option>
                <option value="Cape Coast">Cape Coast</option>
                <option value="Bolgatanga">Bolgatanga</option>
                <option value="Ho">Accra</option>
            </select>
            </label>
          <br />
          <label>
                Bus Type:
                <select name="busType" value={passenger.busType} onChange={handleChange}>
                    <option value="">Select a bus type</option>
                    <option value="VIP">VIP</option>
                    <option value="VVIP">VVIP</option>
                </select>
                </label>

          <br />
          <button type="submit">Submit</button>
        </form>
        {successMessage && <p>{successMessage}</p>}
        {failedMessage && <p>{failedMessage}</p>}
        {booking && <p>Your booking number is : {booking.bookingNumber}</p>}
        </div>
      );
}

export default Booking;