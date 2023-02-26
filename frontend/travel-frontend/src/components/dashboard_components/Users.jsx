import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../css/dashboard_css/Bookings.css';
import { Modal, Button } from 'react-bootstrap';

function Users(){
    const [users, setUsers] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [showModal, setShowModal] = useState(false);
    const [userid, setUserId] = useState(null);
    const [deleteMessage, setDeleteMessage] = useState("");
    const [userAccount, setUserAccount] = useState(true);
    const API_URL = 'http://localhost:8081/api/users/all-users';
    const isAdmin = localStorage.getItem("role") === "ROLE_ADMIN";

    const handleClose = () => setShowModal(false);

    const handleConfirm = (id) => {
      if(userAccount){
        setUserAccount(false);
      }
      fetch(`http://localhost:8081/api/users/revoke/${id}`,{
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
        method: "POST"
      }).then((res) => {
       if (res.status === 200) {
        // Remove the deleted record from the state
        setUsers(users.filter(item => item.id !== id));
        if(userAccount){
        setDeleteMessage("User has been revoked successfully");
        }else{
          setDeleteMessage("User has been un-revoked successfully")
        }
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
          console.log(response.data);
          setUsers(response.data);
          console.log(users.username);
        })
        .catch(error => {
            console.log(error);
        });
    }, []);
  
    const filteredUsers = users.filter(user =>
        (user.id && user.id.toString().toLowerCase().includes(searchTerm.toLowerCase())) ||
        (user.email && user.email.toLowerCase().includes(searchTerm.toLowerCase())) ||
        (user.firstname && user.firstname.toLowerCase().includes(searchTerm.toLowerCase()))||
        (user.lastname && user.lastname.toLowerCase().includes(searchTerm.toLowerCase()))
    );
    
  
    const handleSearch = event => {
        setSearchTerm(event.target.value);
    };
  
    return (
        <>
      
        {isAdmin && (<div className="container">
        <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Confirm action</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {userAccount ? "Are you sure you want to revoke this user?" : "Are you sure you want to un-revoke this user?"}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" style={{ backgroundColor:'red' }} onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={() =>handleConfirm(userid)}>
            Confirm
          </Button>
        </Modal.Footer>
      </Modal>
      {deleteMessage && 
  <div style={{ display: 'flex', justifyContent: 'flex-start', marginLeft: '21pc'}}>
    <div className="alert alert-success" role="alert">{deleteMessage}</div>
  </div>
}

            <h1>Users</h1>
            <input type="text" placeholder="Search" value={searchTerm} onChange={handleSearch} />
            <table className="bookings-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>Account Enabled Status</th>

                        <th></th>
                      
                    </tr>
                </thead>
                <tbody>
                    {filteredUsers.map(user => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.firstname}</td>
                            <td>{user.lastname}</td>
                            <td>{user.email}</td>
                            <td>{user.username}</td>
                            <td>{user.accountNonLocked.toString()}</td>
                           <td> <button type="button" onClick={() => {setShowModal(true);setUserId(user.id);setUserAccount(user.accountNonLocked)}} className="btn btn-danger btn-sm">
                           <i class="bi bi-slash-circle-fill"></i> </button></td>
                          

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>)}
        </>
    );
}

export default Users;
