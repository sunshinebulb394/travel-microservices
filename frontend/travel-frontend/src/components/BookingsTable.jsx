import { Modal, Button } from 'react-bootstrap';
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../css/dashboard_css/Bookings.css';
import axios from 'axios';
import Footer from './Footer';
import Navbar from './Navbar';
import EditBooking from './EditbookingForm';
function ViewBookings(){
    const navigate = useNavigate();
    const [bookings, setBookings] = useState([]);
    const [updateBooking, setUpdateBooking] = useState({});
    const [showModal, setShowModal] = useState(false);
    const [query, setQuery] = useState("");
    const [bookingId, setBookingId] = useState(null);
    const [message, setMessage] = useState("");
    const [ticket, setTicket] = useState(null);
    const [isLoading, setIsLoading] = useState(false);



    // const handleEdit = async (bookingNumber, bookingId) => {
    //   try {
    //     const response = await fetch(`http://localhost:8081/api/ticket/retrieve-ticket?ticket-number=${bookingNumber}`);
    //     if (response.status === 200) {
    //       setMessage("Ticket generated successfully, you cannot edit this booking");
    //     } else if (response.status === 404) {
    //       navigate(`/editbooking/${bookingId}`);
    //     } else {
    //       setMessage("Edit Failed");
    //     }
    //   } catch (error) {
    //     if (error.response && error.response.status === 404) {
    //       navigate(`/editbooking/${bookingId}`);
    //     }
    //   }
    // };

    const handleUpdate =async (booking) => {
      try {
        const response = await fetch(`http://localhost:8081/api/ticket/retrieve-ticket?ticket-number=${booking.bookingNumber}`);
        if (response.status === 200) {
          setMessage("Ticket generated successfully, you cannot edit this booking");
        } else if (response.status === 404) {
          setUpdateBooking(booking);
        } else {
          setMessage("Edit Failed");
        }
      } catch (error) {
        if (error.response && error.response.status === 404) {
          setUpdateBooking(booking);
        }
      }
     
  }


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
      setMessage("Booking updated successfully")
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



{message && 
  <div style={{ display: 'flex', justifyContent: 'flex-start', marginLeft: '21pc'}}>
    <div className="alert alert-success" role="alert">{message}</div>
  </div>}

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
                     <button  type="button" onClick={()=>{handleUpdate(booking)}}>Edit</button>
                    </td>
                 
                   <td> <button  type="button" onClick={() => {setShowModal(true);setBookingId(booking.id);getTicket(booking.bookingNumber)}} className="btn btn-primary btn-sm">
                   <i class="bi bi-ticket-perforated ">  Get ticket</i>
                    </button>
                   </td>

                        </tr>
                        
                    ))}
                </tbody>
            </table>
              
            {Object.keys(updateBooking).length > 0 && (
    <div>
      <h3>Update Booking</h3>
      <form  onSubmit={handleFormSubmit} >
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
  {/* <p> {successMessage && <p>{successMessage}</p>}</p> */}

  

        </div>
        <Footer />
        </>
    );
}
export default ViewBookings;