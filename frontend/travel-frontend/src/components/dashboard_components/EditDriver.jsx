import { useState, useEffect  } from 'react';
import '../../css/dashboard_css/AddBus.css';
import { useNavigate,useParams } from 'react-router-dom';

const EditDriver = () => {
  const [driver, setDriver] = useState({
    contact: '',
    name: '',
    driversLicense: ''
 
  });
  const [error, setError] = useState('');
  const { id } = useParams();
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  useEffect(() => {
    fetch(`http://localhost:8081/api/driver/driver-id?id=${id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json()) // Parse the response as JSON
      .then((data) => {
        // Update the state with the existing bus details
        setDriver(data);
      })
      .catch((error) => {
        setError(error.message);
      });
  }, [id, token]);
  
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Send the updated data to the API
    fetch(`http://localhost:8081/api/driver/update/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(driver),
    })
      .then((res) => {
        console.log(res.status);
        // Navigate back to the table after successful update
        navigate('/dashboard/driver-table');
      })
      .catch((error) => {
        setError('Error updating data');
      });
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setDriver((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  return (
    <>
    
    <form className="search-form" onSubmit={handleSubmit} >
    <h1>Edit Driver</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      <div className="form-group">
          <label htmlFor="busType">Driver</label>
          <input
           
            type="text"
            className="form-control"
            id="name"
            name="name"
            value={driver.name}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="driversLicense">Drivers License</label>
          <input
          disabled
            type="text"
            className="form-control"
            id="driversLicense"
            name="driversLicense"
            value={driver.driversLicense}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="contact">Phone Number</label>
          <input
            
            type="text"
            className="form-control"
            id="contact"
            name="contact"
            value={driver.contact} 
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

export default EditDriver;
