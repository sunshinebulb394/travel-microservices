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
          <Link to="/" className="navbar-link">Buses </Link>
          <Link to="/" className="navbar-link">Drivers </Link>
          <Link to="/login" className="navbar-link">Login</Link>
          <Link to="/Signup" className="navbar-link">Signup</Link>
          <Link to="/about" className="navbar-link">About</Link>
          <Link to="/contact" className="navbar-link">Contact</Link>
          
        </div>
  
      </div>
    </nav>
    </div>
  );
}

export default Navbar;
