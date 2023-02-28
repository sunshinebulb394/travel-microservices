import { useNavigate, useParams } from 'react-router-dom';
import { Form, InputGroup, Row, Button } from 'react-bootstrap';
import Navbar from './Navbar';
import { useState, useEffect } from 'react';
import Footer from './Footer';
import { Link } from 'react-router-dom';
import axios from 'axios';

function EditBooking(props) {
  const navigate = useNavigate();
  const { id } = useParams();
  const [message, setMessage] = useState("");
  const [error, setError] = useState('');
  const [booking, setBooking] = useState({
    id: '',
    bookingNumber: '',
    busType: '',
    createdAt: '',
    destination: '',
    pickupTime: '',
    travelDate: '',
    pickupLocation: '',
    phoneNumber: '',
    passengerName: '',
  });

    const pass = props.bookObj;
    console.log(pass);
  useEffect(() => {
    fetch(`http://localhost:8081/booking/id?id=${id}`, {
      headers: {
        Authorization: `Bearer gkb0201219789`,
      },
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        }
        throw new Error('Error getting data');
      })
      .then((data) => {
        console.log(data);
        // Update the state with the existing bus details
        setBooking(data);
        console.log("booking");
        console.log(booking);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [id]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.put(`http://localhost:8081/booking/update-booking?booking-id=${id}`, booking, {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer gkb0201219789",
        },
      });
      console.log(res.status);
      navigate('/dashboard/bus-table');
    } catch (error) {
      console.log(error);
      setError(error.message);
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    console.log(`Setting ${name} to ${value}`);
    setBooking((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  return (
    <>
     <h1>{pass.bookingNumber}</h1>
    </>
  );
}

export default EditBooking;
