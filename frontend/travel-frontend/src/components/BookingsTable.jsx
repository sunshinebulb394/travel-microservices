import { Modal, Button } from 'react-bootstrap';
import React, { useState, useEffect } from 'react';
import '../css/dashboard_css/Bookings.css';
import axios from 'axios';
import Footer from './Footer';
import Navbar from './Navbar';
function ViewBookings(){

    const [bookings, setBookings] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [query, setQuery] = useState("");
    const [bookingId, setBookingId] = useState(null);
    const [deleteMessage, setDeleteMessage] = useState("");
    const [ticket, setTicket] = useState(null);
    const [isLoading, setIsLoading] = useState(false);


    const handleClose = () => {
      setShowModal(false);
      setTicket(null);
    };
    
    const handleChange = (event) => {
        setQuery(event.target.value);
      };
    
     const getTicket = async (bookingNumber) => {
  setIsLoading(true);
  try {
    const response = await axios.get(`http://localhost:8081/api/ticket/retrieve-ticket?ticket-number=${bookingNumber}`);
    setTicket(response.data);
  } catch (err) {
    console.log(err);
  } finally {
    setIsLoading(false);
  }
};



      const handleSearch = async (event) => {
        event.preventDefault();


        try {
          const response = await fetch(`http://localhost:8081/booking/search?number=${query}`, {
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
      window.addEventListener('load', function() {
        // Get the button element
        const button = document.getElementById('myButton');
      
        // Check if ticket is not null
        if (ticket !== null) {
          // Disable the button
          button.disabled = true;
        }
      });
      

    return(
        <>
      <Navbar />
        <div className="container mt-5">
        <Modal show={showModal} onHide={handleClose}>
  <Modal.Header closeButton>
    <Modal.Title>Ticket </Modal.Title>
  </Modal.Header>
  {isLoading ? (
    <Modal.Body>Loading...</Modal.Body>
  ) : (
    ticket ? (
      <Modal.Body>
  <div className="row">
    <div className="col-sm-4"><strong>Ticket Number:</strong></div>
    <div className="col-sm-8">{ticket.ticketNumber}</div>
  </div>
  <div className="row">
    <div className="col-sm-4"><strong>Passenger Name:</strong></div>
    <div className="col-sm-8">{ticket.passengerName}</div>
  </div>
  <div className="row">
    <div className="col-sm-4"><strong>Price:</strong></div>
    <div className="col-sm-8">GH₵ {ticket.price}</div>
  </div>
  <div className="row">
    <div className="col-sm-4"><strong>Destination:</strong></div>
    <div className="col-sm-8">{ticket.destination}</div>
  </div>
  <div className="row">
    <div className="col-sm-4"><strong>Bus Type:</strong></div>
    <div className="col-sm-8">{ticket.busType}</div>
  </div>
  <div className="row">
    <div className="col-sm-4"><strong>Pickup Time:</strong></div>
    <div className="col-sm-8">{ticket.pickupTime}</div>
  </div>
  <div className="row">
    <div className="col-sm-4"><strong>Pickup Location:</strong></div>
    <div className="col-sm-8">{ticket.pickupLocation}</div>
  </div>
</Modal.Body>

    ):(<Modal.Body>Your ticket is not ready </Modal.Body>)
  )}
  <Modal.Footer>
    <Button variant="secondary" style={{ backgroundColor: 'red' }} onClick={handleClose}>
      Close
    </Button>
  </Modal.Footer>
</Modal>

     

            <h2 style={{ color:'black' ,backgroundColor:'white' }}>Bookings</h2>
            <form onSubmit={handleSearch}>
                <input type="text"  placeholder="Search..." value={query} onChange={handleChange} />
                <button type="submit" style={{ marginLeft:'10px',marginBottom:'3px' }} className="btn btn-primary btn-sm "><i class="bi bi-search"></i></button>
            </form>
            <br/>
            <br/>
            <table className="bookings-table">
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
                    <td>
                    <td>
                     <button  type="button" id='myButton'>Edit</button>
                    </td>
                     </td>
                   <td> <button  type="button" onClick={() => {setShowModal(true);setBookingId(booking.id);getTicket(booking.bookingNumber)}} className="btn btn-primary btn-sm">
                   <i class="bi bi-ticket-perforated ">  Get ticket</i>
                    </button></td>

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        <Footer />
        </>
    );
}
export default ViewBookings;