import { useState } from "react";
import SearchBox from "./SearchBox";
import { Modal, Button } from 'react-bootstrap';
import '../css/ModalPrint.css';
import Footer from './Footer';
import Navbar from './Navbar';

function BookingList(){
    const [bookings, setBookings] = useState([]);
    const [updateBooking, setUpdateBooking] = useState({});
    const [successMessage, setSuccessMessage] = useState("");
    const [showModal, setShowModal] = useState(false);
    const [responseData, setResponseData] = useState(null);
    const GenerateTicketModal = ({ show, onHide, response }) => {
      return (
        <Modal id='modal-print'show={show} onHide={onHide}>
          <Modal.Header  closeButton>
            <Modal.Title>Ticket Generated</Modal.Title>
          </Modal.Header>
          <Modal.Body>
          <div className="ticket-info">
          <p><strong>Booking Number:</strong> {responseData?.ticketNumber}</p>
          <p><strong>Passenger Name:</strong> {responseData?.passengerName}</p>
          <p><strong>Cost:GHS</strong> {responseData?.price}</p>
          <p><strong>Destination:</strong> {responseData?.destination}</p>
          <p><strong>Bus Type:</strong> {responseData?.busType}</p>
          <p><strong>Seat Number:</strong> {responseData?.seatNumber}</p>
        </div>
          </Modal.Body>
          <Modal.Footer >
            <Button variant="secondary" onClick={onHide}>
              Close
            </Button>
            <Button variant="primary" onClick={() => window.print()}>
              Print
            </Button>
          </Modal.Footer >
        </Modal>
      );
    };
    
    const handleSearch = async (query) => {
        try {
            const response = await fetch(`http://localhost:8081/booking/search?number=${query}`, {
                headers: {
                  'Content-Type': 'text/plain',
                  Authorization: 'Bearer gkb0201219789',
                },
              });
          console.log(bookings.bookingNumber);
          const data = await response.json();
          setBookings(data);
          console.log(data);
        } catch (err) {
          console.log(err);
        }
      };
      const handleRefresh = async (bookingId) => {
        try {
          const response = await fetch(`http://localhost:8081/booking/id?id=${bookingId}`, {
              headers: {
                'Content-Type': 'text/plain',
                Authorization: 'Bearer gkb0201219789',
              },
            });
        console.log(bookings.bookingNumber);
        const data = await response.json();
        setBookings(data);
        console.log(data);
      } catch (err) {
        console.log(err);
      }
      };
      
     
      const handleGenerateTicket = async (bookingNumber) => {
        try {
          const response = await fetch(`http://localhost:8081/booking/gen-ticket?booking-number=${bookingNumber}`, {
          method: "POST",  
          headers: {
              'Content-Type': 'text/plain',
              Authorization: 'Bearer gkb0201219789',
            },
          });
          console.log(response.status);
          if (response.ok) {
            // TODO: handle successful response
             const responseData = await response.json();
             setShowModal(true);
            setResponseData(responseData);
          } else {
            console.log('Server responded with an error');
          }
        } catch (err) {
          console.log(err);
        }
      };
   
      const handleModalClose = () => {
        setShowModal(false);
        setResponseData(null);
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
        <>
          <Navbar/>
        <div>
         
          <table class="my-table">
          <SearchBox onSearch={handleSearch} />
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
      <th></th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    {bookings.map((booking) => (
      <tr key={booking.id} >
        <td>{booking.bookingNumber}</td>
        <td>{booking.passengerName}</td>
        <td>{booking.pickupTime}</td>
        <td>{booking.travelDate}</td>
        <td>{booking.pickupLocation}</td>
        <td>{booking.destination}</td>
        <td>{booking.busType}</td>
        <td>{booking.phoneNumber}</td>
        <td ><button class="refresh-button" onClick={() => handleRefresh(booking.id)}><i class="bi bi-arrow-clockwise"></i></button></td>
        <td><button type="button" className="btn btn-success btn-sm" onClick={() => handleUpdate(booking)}>
          <i class="bi bi-pencil-square"></i> &nbsp;Update
        </button></td>
        <td><Button type="button" onClick={() => handleGenerateTicket(booking.bookingNumber)} className="btn btn-secondary btn-sm">
          <i class="bi bi-ticket"></i> Generate Ticket</Button></td>
        <GenerateTicketModal show={showModal} onHide={handleModalClose} response={responseData} />
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
  <Footer/>
</>
      );


}
export default BookingList;
