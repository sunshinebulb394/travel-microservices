import { useState } from 'react';
import '../../css/dashboard_css/AddBus.css';
import { useNavigate } from 'react-router-dom';

const AddBus = () => {
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
  
  
  const navigate = useNavigate();
  const [addsuccess, setAddsuccess] = useState(false);

  

  const token = localStorage.getItem("token");
  const authenticated = localStorage.getItem("authenticated");
  if (!token || !authenticated) {
    navigate("/login");
  } 
  let handleSubmit = async (e) => {
    console.log(bus);
   
    e.preventDefault();
    try {
       fetch("http://localhost:8081/api/bus/add", {
        method: "POST",
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(bus),
      })
        .then((res) => {
          if(res.status === 201){
            setAddsuccess(true);
           setBus({
            busType: '',
            insuranceNumber: '',
            registrationNumber: '',
            purchaseDate: '',
            capacity: '',
            insuranceCompany: '',
            insuranceExpiryDate: '',
            roadWorthyExpiryDate: '',
            isAvailable: '',
           })
          }
        });
    } catch (err) {
      console.log(err);
    }
  };
 

  

  return (
    <>
    
    <form className="search-form" onSubmit={handleSubmit} >
    {addsuccess && <p>Bus added successfully!</p>}

    <label>
     Bus Type:
        <select value={bus.busType} name="busType" onChange={(e) => setBus({...bus,busType:e.target.value})}>
        <option value="">--Choose bus type--</option>
        <option value="VIP">VIP</option>
        <option value="VVIP">VVIP</option>
        
       </select>
    </label>
      <label>
        insurance number:
        <input type="text" value={bus.insuranceNumber} name="insuranceNumber"  onChange={(e) => setBus({...bus,insuranceNumber:e.target.value})}/>
      </label>
      <label>
        registration number:
        <input type="text" value={bus.registrationNumber} name="registrationNumber" onChange={(e) =>setBus({...bus,registrationNumber:e.target.value})} />
      </label>
      <label>
        Purchase Date:
        <input type="date" value={bus.purchaseDate} name="purchaseDate" onChange={(e) => setBus({...bus,purchaseDate:e.target.value})} />
      </label>
      <label>
      Insurance Company:
        <input type="text" value={bus.insuranceCompany} name="insuranceCompany" onChange={(e) => setBus({...bus,insuranceCompany:e.target.value})} />
      </label>
      <label>
      Insurance Expiry Date:
        <input type="date" value={bus.insuranceExpiryDate} name="insuranceExpiryDate" onChange={(e) => setBus({...bus,insuranceExpiryDate:e.target.value})} />
      </label>
      <label>
      Road Worthy Expiry Date:
        <input type="date" value={bus.roadWorthyExpiryDate} name="roadWorthyExpiryDate" onChange={(e) => setBus({...bus,roadWorthyExpiryDate:e.target.value})} />
      </label>
      <label>
      Capacity:
        <input type="number" value={bus.capacity} name="capacity" onChange={(e) => setBus({...bus,capacity:e.target.value})} />
      </label>
      <label>
      is Available
        <input type="text" value={bus.isAvailable} name="isAvailable" onChange={(e) => setBus({...bus,isAvailable:e.target.value})}/>
      </label>

      <button class="search-button"type="submit">Add</button>
    </form>
    <h3>
    
    </h3>
    </>
  );
};

export default AddBus;
