import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../css/dashboard_css/Bookings.css';
import { Modal, Button } from 'react-bootstrap';

function Bookings(){
    const [bookings, setBookings] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [showModal, setShowModal] = useState(false);
    const token = localStorage.getItem("token");
    const [bookingId, setBookingId] = useState(null);
    const [deleteMessage, setDeleteMessage] = useState("");
    const API_URL = 'http://localhost:8081/api/bus/all-bookings';


    const handleClose = () => setShowModal(false);

    const handleConfirm = (id) => {
      fetch(`http://localhost:8081/booking/delete-bookings/${id}`,{
        method: "DELETE"
      }).then((res) => {
       if (res.status === 204) {
        // Remove the deleted record from the state
        setBookings(bookings.filter(item => item.id !== id));
        setDeleteMessage("Booking deleted successfully");
      }else if(res.status === 400){
        setDeleteMessage("You are not authorized to delete this Booking");
      }else{setDeleteMessage("Something went wrong");}
        setShowModal(false);
      })
      // Handle the confirm action here
      console.log('Confirm button clicked!');
    };
    
    useEffect(() => {
        const token = localStorage.getItem("token");
        axios.get(API_URL,{
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        .then(response => {
          console.log(response.status);
            console.log(response.data)
            setBookings(response.data);
        })
        .catch(error => {
            console.log(error);
        });
    }, []);
  
    const filteredBookings = bookings.filter(booking =>
        (booking.bookingNumber && booking.bookingNumber.toLowerCase().includes(searchTerm.toLowerCase())) ||
        (booking.passengerName && booking.passengerName.toLowerCase().includes(searchTerm.toLowerCase())) ||
        (booking.destination && booking.destination.toLowerCase().includes(searchTerm.toLowerCase()))
    );
    
  
    const handleSearch = event => {
        setSearchTerm(event.target.value);
    };
  
    return (
        <>
      
        <div className="container">
        <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Confirm action</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to Delete this Booking?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" style={{ backgroundColor:'red' }} onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={() =>handleConfirm(bookingId)}>
            Confirm
          </Button>
        </Modal.Footer>
      </Modal>
      {deleteMessage && 
  <div style={{ display: 'flex', justifyContent: 'flex-start', marginLeft: '21pc'}}>
    <div className="alert alert-success" role="alert">{deleteMessage}</div>
  </div>
}

            <h1>Bookings</h1>
            <input type="text" placeholder="Search" value={searchTerm} onChange={handleSearch} />
            <table className="bookings-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Booking Number</th>
                        <th>Pickup Time</th>
                        <th>Travel Date</th>
                        <th>Created At</th>
                        <th>Pickup Location</th>
                        <th>Destination</th>
                        <th>Passenger Name</th>
                        <th>Bus Type</th>
                        <th>Phone Number</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {filteredBookings.map(booking => (
                        <tr key={booking.id}>
                            <td>{booking.id}</td>
                            <td>{booking.bookingNumber}</td>
                            <td>{booking.pickupTime}</td>
                            <td>{booking.travelDate}</td>
                            <td>{booking.createdAt}</td>
                            <td>{booking.pickupLocation}</td>
                            <td>{booking.destination}</td>
                            <td>{booking.passengerName}</td>
                            <td>{booking.busType}</td>
                            <td>{booking.phoneNumber}</td>
                            <td> <button type="button" onClick={() => {setShowModal(true);setBookingId(booking.id)}} className="btn btn-danger btn-sm">
         <i class="bi bi-trash"></i> 
      </button></td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
        </>
    );
}

export default Bookings;
