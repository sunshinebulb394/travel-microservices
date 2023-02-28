import React from 'react';
import { Nav, NavItem, NavDropdown, NavLink } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

// import the CSS styles
import '../../css/dashboard_css/Sidebar.css';

function Sidebar() {
  const [authenticated, setAuthenticated] = useState(true);
  const navigate = useNavigate();


  const handleLogout = async () => {
    const token = localStorage.getItem("token");

    const response = await fetch("http://localhost:8081/api/users/logout", {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    if (response.ok) {
      localStorage.removeItem("token");
      setAuthenticated(false);
      navigate("/login-register");
    }
  }
  return (
    <div className="position-fixed left-0 top-0 d-flex flex-column">
       
      <Nav
        variant="pills"
        className="flex-column"
        style={{
          // set custom font and colors
          fontFamily: 'Montserrat, sans-serif',
          backgroundColor: 'darkgreen',
          color: '#fff',
          width: '10pc',
          height:'100pc'
        }}
      >
         <h3>Dashboard</h3>
         <NavDropdown title="Buses" id="nav-dropdown" >
          <NavDropdown.Item >
            <NavLink
              
              as={Link}
              to="/dashboard/bus-table"
              // add active effect
              activeClassName="active"
            >
              View Bus List
            </NavLink>
          </NavDropdown.Item>
          <NavDropdown.Item>
            <NavLink
              as={Link}
              to="addbus"
              // add active effect
              activeClassName="active"
            >
              Add Bus Details
            </NavLink>
          </NavDropdown.Item>
         
        
        </NavDropdown>
        <NavDropdown title="Drivers" id="nav-dropdown">
          <NavDropdown.Item>
            <NavLink
              as={Link}
              to="/dashboard/driver-table"
              // add active effect
              activeClassName="active"
            >
             View Driver List
            </NavLink>
          </NavDropdown.Item>
          <NavDropdown.Item>
            <NavLink
              as={Link}
              to="addDriver"
              // add active effect
              activeClassName="active"
            >
              Add Driver Details
            </NavLink>
          </NavDropdown.Item>
         
        
        </NavDropdown>
        
        <NavItem>
          <NavLink
            as={Link}
            to="/dashboard/bookings"
            // add active effect
            activeClassName="active">
            Bookings
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            as={Link}
            to="/dashboard/users"
            // add active effect
            activeClassName="active"
          >
            Users
          </NavLink>
        </NavItem>
        <NavItem>
          {authenticated && (
          <NavLink onClick={handleLogout} >
            Logout</NavLink>)}
         
        </NavItem>
      </Nav>
    </div>
  );
}


export default Sidebar;