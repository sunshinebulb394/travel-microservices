import { useState } from 'react';
import '../../css/dashboard_css/AddBus.css';
import { useNavigate } from 'react-router-dom';

const AddDriver = () => {
  const navigate = useNavigate();
  const [driver, setDriver] = useState({
    name: '',
    contact: '',
    driversLicense: ''
  });
  const [addsuccess, setAddsuccess] = useState(false);

  const token = localStorage.getItem("token");
  const authenticated = localStorage.getItem("authenticated");
  if (!token || !authenticated) {
    navigate("/login");
  }

  let handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch("http://localhost:8081/api/driver", {
        method: "POST",
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(driver),
      });
      if (res.status === 201) {
        setAddsuccess(true);
        setDriver({
          name: '',
          contact: '',
          driversLicense: ''
        });
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
      <form className="search-form" onSubmit={handleSubmit}>
        {addsuccess && <p>Driver added successfully!</p>}

        <label>
          Name
          <input type="text" value={driver.name} name="name" onChange={(e) => setDriver({...driver, name: e.target.value})} />
        </label>
        <label>
          Contact
          <input type="text" value={driver.contact} name="contact" onChange={(e) => setDriver({...driver, contact: e.target.value})} />
        </label>
        <label>
          Driver License
          <input type="text" value={driver.driversLicense} name="driversLicense" onChange={(e) => setDriver({...driver, driversLicense: e.target.value})} />
        </label>

        <button className="search-button" type="submit">Add</button>
      </form>
    
    </>
  );
};

export default AddDriver;
