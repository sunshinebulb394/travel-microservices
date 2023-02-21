import React, { useEffect, useState } from 'react';
import jwt_decode from "jwt-decode";
import '../../css/dashboard_css/Table.css'

const Table = (props) => {
  const [data,setData] = useState([]);
  const token = localStorage.getItem("token");


  
  
  useEffect(() => {
    const decoded = jwt_decode(token);
   // localStorage.setItem("role",decoded.role);
   

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
  }, []); // Empty array of dependencies
  
  return (
    <table className='my-table'>
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
         <th></th>

     
        </tr>
      </thead>
      <tbody>
        
      {data.map(item =>(
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
         <td style={{ width: '50px' }}> <button type="button" className="btn btn-warning btn-sm">
         <i class="bi bi-pencil"></i>
      </button></td>
         <td style={{ width: '50px' }}> <button type="button" className="btn btn-danger btn-sm">
         <i class="bi bi-trash"></i> 
      </button></td>
         </tr>
        ))}
      </tbody>
    </table>
  );
};

export default Table;