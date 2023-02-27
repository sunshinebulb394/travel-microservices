import { Form, InputGroup, Row, Button } from 'react-bootstrap';
import { useState } from 'react';
import Navbar from './Navbar';
import { Link } from 'react-router-dom';
import Footer from './Footer';
function BookingForm(){
     const [booking, setBooking] = useState(null); 
      const [message, setMessage] = useState("");

    const [passenger, setPassenger] = useState({
        passengerName: "",
        pickupTime: "",
        travelDate: "",
        pickupLocation: "",
        destination: "",
        busType: "",
        phoneNumber: "",
      });
      const handleChange = (e) => {
        setPassenger({ ...passenger, [e.target.name]: e.target.value });
      }

      const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(passenger);
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
              setMessage("Booking successful");
              resetButton()
              
          } 
          } catch (err) {
            console.log(err);
            setMessage("BookingFailed");
            // Handle any errors here
          }
        
      }
      const resetButton = () => {
        setPassenger({
            passengerName: "",
            pickupTime: "",
            travelDate: "",
            pickupLocation: "",
            destination: "",
            busType: "",
            phoneNumber: "",
        });
      }

      return (
        <>
        <Navbar/>
      <div className="container mt-3 mb-3 bg-light p-4">
 {message && 
  <div style={{ display: 'flex', justifyContent: 'flex-start', marginLeft: '21pc'}}>
    <div className="alert alert-success" role="alert">{message}Your booking number :{booking.bookingNumber}</div>
  </div>}
          <form className="container mt-3 mb-3" onSubmit={handleSubmit}>
    <Row className="mb-3">
        <Form.Group controlId="formBasicEmail" className="col col-sm-6">
            <Form.Label>Passenger Name</Form.Label>
            <Form.Control type="name" name="passengerName" value={passenger.passengerName} onChange={handleChange} className="form-control" />
        </Form.Group>
        <Form.Group controlId="formBasicEmail" className="col col-sm-6">
            <Form.Label>Pickup Time</Form.Label>
            <Form.Control type="time" name="pickupTime" value={passenger.pickupTime} onChange={handleChange} className="form-control" />
        </Form.Group>
    </Row>
    <Row className="mb-3">
        <Form.Group controlId="formBasicMobile" className="col col-sm-6">
            <Form.Label>Mobile Number</Form.Label>
            <InputGroup>
                <InputGroup.Text id="basic-addon1">+233</InputGroup.Text>
                <Form.Control aria-label="Mobile Number" type="text" aria-describedby="basic-addon1" className="form-control" name="phoneNumber" value={passenger.phoneNumber} onChange={handleChange} />
            </InputGroup>
        </Form.Group>
        <Form.Group controlId="formGridCheckbox" className="col col-sm-6">
            <Form.Label>Destination</Form.Label>
            <Form.Select className="form-control" name="destination" value={passenger.destination} onChange={handleChange}>
                <option value="Choose...">Choose...</option>
                <option value="Koforidua">Koforidua</option>
                <option value="Bolgatanga">Bolgatanga</option>
                <option value="Cape Coast">Cape Coast</option>
                <option value="Ho">Ho</option>
                <option value="KumasiHo">Kumasi</option>
            </Form.Select>
        </Form.Group>
    </Row>
    <Row className="mb-3">
        <Form.Group className=" col col-sm-6" controlId="formGridAddress1">
            <Form.Label>Travel Date</Form.Label>
            <Form.Control className="form-control" type="date" name="travelDate" value={passenger.travelDate} onChange={handleChange} />
        </Form.Group>
        <Form.Group controlId="formGridState" className="col col-sm-4">
            <Form.Label>Bus Type</Form.Label>
            <Form.Select className="form-control" name="busType" value={passenger.busType} onChange={handleChange}>
                <option value="Choose...">Choose...</option>
                <option value="VIP">VIP</option>
                <option value="VVIP">VVIP</option>
            </Form.Select>
        </Form.Group>
    </Row>
    <Row className="mb-3">
        <Form.Group controlId="formGridCheckbox" className="col col-sm-6">
            <Form.Label>Pickup Location</Form.Label>
            <Form.Select  className="form-control" name="pickupLocation" value={passenger.pickupLocation} onChange={handleChange}>
                <option value="Choose...">Choose...</option>
                <option value="Accra ">Accra</option>
                <option value="Tema">Tema</option>
                <option value="Nungua">Nungua</option>
                <option value="Madina">Madina</option>
            </Form.Select>
        </Form.Group>
    </Row>
    <Row className="mb-3">
        <Form.Group controlId="formGridCheckbox" className="col col-sm-6">
            <button type="submit"  className="me-4 btn btn-success btn-lg btn-block">Submit Booking</button>
            <button type="reset" onClick={resetButton} className="me-4 btn btn-danger btn-lg btn-block">Cancel</button> 
            <Link to="/booking-table">
            <button type='button' className="me-4 btn btn-secondary btn-lg btn-block">View Booking</button> 
            </Link>
        </Form.Group>
    </Row>
</form>
        </div>
        <Footer/>
        </>
      );

}

export default BookingForm;