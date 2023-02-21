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
      navigate("/login");
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
          backgroundColor: '#333',
          color: '#fff',
          width: '10pc',
          height:'100pc'
        }}
      >
         <h3>Dashboard</h3>
        <NavItem>
          <NavLink
            as={Link}
            to="/buses"
            // add active effect
            activeclassName="active"
          >
            Buses
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink
            as={Link}
            to="/drivers"
            // add active effect
            activeClassName="active"
          >
            Drivers
          </NavLink>
        </NavItem>
        <NavDropdown title="Messages" id="nav-dropdown">
          <NavDropdown.Item>
            <NavLink
              as={Link}
              to="/messages/inbox"
              // add active effect
              activeClassName="active"
            >
              Drivers
            </NavLink>
          </NavDropdown.Item>
          <NavDropdown.Item>
            <NavLink
              as={Link}
              to="/messages/sent"
              // add active effect
              activeClassName="active"
            >
              Sent
            </NavLink>
          </NavDropdown.Item>
          <NavDropdown.Item>
            <NavLink
              as={Link}
              to="/messages/spam"
              // add active effect
              activeClassName="active"
            >
              Spam
            </NavLink>
          </NavDropdown.Item>
          <NavDropdown.Item>
            <NavLink
              as={Link}
              to="/messages/trash"
              // add active effect
              activeClassName="active"
            >
              Trash
            </NavLink>
          </NavDropdown.Item>
        </NavDropdown>
        <NavItem>
          <NavLink
            as={Link}
            to="/settings"
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