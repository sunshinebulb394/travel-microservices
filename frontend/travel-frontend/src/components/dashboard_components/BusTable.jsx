import React, { useEffect, useState } from 'react';
import jwt_decode from "jwt-decode";
import '../../css/dashboard_css/Table.css'
import { useNavigate } from 'react-router-dom';
import { Modal, Button } from 'react-bootstrap';

const Table = () => {
  const [data,setData] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const token = localStorage.getItem("token");
  const navigate = useNavigate();
  const [showModal, setShowModal] = useState(false);
  const [busId, setBusId] = useState(null);
  const [deleteMessage, setDeleteMessage] = useState("");
  
  const handleClose = () => setShowModal(false);

  const handleConfirm = (id) => {
    fetch(`http://localhost:8081/api/bus/${id}`,{
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      }
    }).then((res) => {
     if (res.status === 204) {
      // Remove the deleted record from the state
      setData(data.filter(item => item.id !== id));
      setDeleteMessage("Bus deleted successfully");
    }else if(res.status === 400){
      setDeleteMessage("You are not authorized to delete this bus");
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
    fetch('http://localhost:8081/api/bus/get-all-buses',{

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

  const filteredBuses = data.filter(bus =>
  (bus.id && bus.id.toString().toLowerCase().includes(searchTerm.toLowerCase())) ||
  (bus.busType && bus.busType.toLowerCase().includes(searchTerm.toLowerCase())) ||
  (bus.insuranceNumber && bus.insuranceNumber.toLowerCase().includes(searchTerm.toLowerCase())) ||
  (bus.registrationNumber && bus.registrationNumber.toLowerCase().includes(searchTerm.toLowerCase())) ||
  (bus.seats && bus.seats.toString().toLowerCase().includes(searchTerm.toLowerCase())) ||
  (bus.isAvailable && bus.isAvailable.toLowerCase().includes(searchTerm.toLowerCase())) 
   
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
          Are you sure you want to Delete this Bus?
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" style={{ backgroundColor:'red' }} onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={() =>handleConfirm(busId)}>
            Confirm
          </Button>
        </Modal.Footer>
      </Modal>
      {deleteMessage && 
  <div style={{ display: 'flex', justifyContent: 'flex-start', marginLeft: '21pc'}}>
    <div className="alert alert-success" role="alert">{deleteMessage}</div>
  </div>
}


   
    <h1>Buses</h1>
    <input type="text" placeholder="Search" value={searchTerm} onChange={handleSearch} />
    <table className='my-table bookings-table'>
    <thead>
        <tr>
         <th>ID</th>
         <th>Bus Type</th>
         <th>Insurance Number</th>
         <th>Registration Number</th>
         <th>Purchase Date</th>
         <th>Date Added</th>
         <th>Capacity</th>
         <th>Insurance Company</th>
         <th>Insurance Expiry Date</th>
         <th>Road worthy Expiry Date</th>
         <th>Availability</th>
         <th></th>
         <th></th>
        

     
        </tr>
      </thead>
      <tbody>
        
      {filteredBuses.map(item =>(
         <tr key={item.id}>
         <td>{item.id}</td>
         <td>{item.busType}</td>
         <td>{item.insuranceNumber}</td>
         <td>{item.registrationNumber}</td>
         <td>{item.purchaseDate}</td>
         <td>{item.dateAdded}</td>
         <td>{item.capacity}</td>
         <td>{item.insuranceCompany}</td>
         <td>{item.insuranceExpiryDate}</td>
         <td>{item.roadWorthyExpiryDate}</td>
         <td>{item.isAvailable}</td>
         <td> <button type="button" className="btn btn-warning btn-sm"  onClick={() => navigate(`/dashboard/editbus/${item.id}`)}>
         <i class="bi bi-pencil"></i>
      </button></td>
         <td> <button type="button" onClick={() => {setShowModal(true);setBusId(item.id)}} className="btn btn-danger btn-sm">
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