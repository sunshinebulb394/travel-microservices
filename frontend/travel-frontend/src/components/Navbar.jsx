import React from 'react';
import { Link } from 'react-router-dom';
import '../css/Navbar.css';

function Navbar() {
  return (
    <div className='navcenter'>
    <nav className="navbar bg-primary" data-bs-theme="light">
   
      <div className="navbar-container">
        <div className="navbar-links">
          <Link to="/" className="navbar-link">Home </Link>
          <Link to="/booking-form" className="navbar-link">BOOK A TRIP</Link>
          <Link to="/booking-table" className="navbar-link">VIEW BOOKINGS</Link>
          
        </div>
  
      </div>
    </nav>
    </div>
  );
}

export default Navbar;
