import React, { useEffect, useState } from 'react';
import jwt_decode from "jwt-decode";
import '../../css/dashboard_css/Table.css'
import { useNavigate } from 'react-router-dom';
import { Modal, Button } from 'react-bootstrap';

const Table = () => {
  const [data,setData] = useState([]);
  const token = localStorage.getItem("token");
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');
  const [showModal, setShowModal] = useState(false);
  const [driverId, setDriverId] = useState(null);
  const [deleteMessage, setDeleteMessage] = useState("");
  
  const handleClose = () => setShowModal(false);

  const handleConfirm = (id) => {
    fetch(`http://localhost:8081/api/driver/delete?id=${id}`,{
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      }
    }).then((res) => {
     if (res.status === 204) {
      // Remove the deleted record from the state
      setData(data.filter(item => item.id !== id));
      setDeleteMessage("Driver deleted successfully");
    }else if(res.status === 400){
      setDeleteMessage("You are not authorized to delete this driver");
    }else{setDeleteMessage("Something went wrong");}
      setShowModal(false);
    })
    // Handle the confirm action here
    console.log('Confirm button clicked!');
  };

  
  useEffect(() => {
    const decoded = jwt_decode(token);
   // localStorage.setItem("role",decoded.role);
   console.log(decoded);

    // Fetch the data from the API
    fetch('http://localhost:8081/api/driver',{
     headers: {
        Authorization: `Bearer ${token}`,
    }})
      .then((response) => response.json())  
      .then((data) => {
        // Store the data in the state variable
        console.log(data);
        setData(data);
       
      });
  }, [token]); // Empty array of dependencies

  const filteredDrivers = data.filter(driver =>
   (driver.id && driver.id.toString().toLowerCase().includes(searchTerm.toLowerCase())) ||
    (driver.name && driver.name.toLowerCase().includes(searchTerm.toLowerCase())) ||
    (driver.driversLicense && driver.driversLicense.toLowerCase().includes(searchTerm.toLowerCase()))
     
  );
  
  
  const handleSearch = event => {
      setSearchTerm(event.target.value);
  };
  
  return (
    <>
    <div className='container'>
     <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Confirm action</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Are you sure you want to Delete this Driver?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" style={{ backgroundColor:'red' }} onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={() =>handleConfirm(driverId)}>
            Confirm
          </Button>
        </Modal.Footer>
      </Modal>
      {deleteMessage && 
  <div style={{ display: 'flex', justifyContent: 'flex-start', marginLeft: '21pc'}}>
    <div className="alert alert-success" role="alert">{deleteMessage}</div>
  </div>
}


    <h1>Drivers</h1>
    <input type="text" placeholder="Search" value={searchTerm} onChange={handleSearch} />
    <table className='my-table bookings-table'>
    <thead>
        <tr>
         <th>ID</th>
         <th>Driver Name</th>
         <th>Driver License</th>
         <th>Phone Number</th>
         <th></th>
         <th></th>
        </tr>
      </thead>
      <tbody>
        
      {filteredDrivers.map(item =>(
         <tr key={item.id}>
         <td>{item.id}</td>
         <td>{item.name}</td>
         <td>{item.driversLicense}</td>
         <td>{item.contact}</td>
      
         <td> <button type="button" className="btn btn-warning btn-sm"  onClick={() => navigate(`/dashboard/editdriver/${item.id}`)}>
         <i class="bi bi-pencil"></i>
      </button></td>
         <td> <button type="button" onClick={() => {setShowModal(true);setDriverId(item.id)}} className="btn btn-danger btn-sm">
         <i class="bi bi-trash"></i> 
      </button></td>
         </tr>
        ))}
      </tbody>
    </table>
    </div>
    </>
  );
};

export default Table;