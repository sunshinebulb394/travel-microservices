import { useState, useEffect  } from 'react';
import '../../css/dashboard_css/AddBus.css';
import { useNavigate,useParams } from 'react-router-dom';

const EditBus = () => {
  const [bus, setBus] = useState({
    busType: '',
    insuranceNumber: '',
    registrationNumber: '',
    purchaseDate: '',
    capacity: '',
    insuranceCompany: '',
    insuranceExpiryDate: '',
    roadWorthyExpiryDate: '',
    isAvailable: '',
  });
  const [error, setError] = useState('');
  const { id } = useParams();
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  useEffect(() => {
    // Make a GET request to the API to get the existing bus details
    fetch(`http://localhost:8081/api/bus/${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (response.ok) {
          return response.json();
        }
        throw new Error('Error getting data');
      })
      .then((data) => {
        // Update the state with the existing bus details
        
        setBus(data);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [id, token]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Send the updated data to the API
    fetch(`http://localhost:8081/api/bus/update/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(bus),
    })
      .then((res) => {
        console.log(res.status);
        // Navigate back to the table after successful update
        navigate('/dashboard/bus-table');
      })
      .catch((error) => {
        setError('Error updating data');
      });
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setBus((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  return (
    <>
    
    <form className="search-form" onSubmit={handleSubmit} >
    <h1>Edit Bus</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <div className="form-group">
          <label htmlFor="busType">Bus Type</label>
          <input
            disabled
            type="text"
            className="form-control"
            id="busType"
            name="busType"
            value={bus.busType}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="insuranceNumber">Insurance Number</label>
          <input
            type="text"
            className="form-control"
            id="insuranceNumber"
            name="insuranceNumber"
            value={bus.insuranceNumber}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="registrationNumber">Registration Number</label>
          <input
            disabled
            type="text"
            className="form-control"
            id="registrationNumber"
            name="registrationNumber"
            value={bus.registrationNumber}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="purchaseDate">Purchase Date</label>
          <input
          disabled
            type="text"
            className="form-control"
            id="purchaseDate"
            name="purchaseDate"
            value={bus.purchaseDate}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="insuranaceCompany">Insurance Company</label>
          <input
            type="text"
            className="form-control"
            id="insuranceCompany"
            name="insuranceCompany"
            value={bus.insuranceCompany}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="insuranceExpiryDate">Insurance Expiry Date</label>
          <input
            type="text"
            className="form-control"
            id="insuranceExpiryDate"
            name="insuranceExpiryDate"
            value={bus.insuranceExpiryDate}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="roadWorthyExpiryDate">Road Worthy Expiry Date</label>
          <input
            type="text"
            className="form-control"
            id="roadWorthyExpiryDate"
            name="roadWorthyExpiryDate"
            value={bus.roadWorthyExpiryDate}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="capacity">Capacity</label>
          <input
            disabled
            type="text"
            className="form-control"
            id="capacity"
            name="capacity"
            value={bus.capacity}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="isAvailable">Is Available</label>
          <input
            type="text"
            className="form-control"
            id="isAvailable"
            name="isAvailable"
            value={bus.isAvailable}
            onChange={handleChange}
          />
        </div>

      <button class="search-button"type="submit">Update</button>
    </form>
    <h3>
    
    </h3>
    </>
  );
};

export default EditBus;
