import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import "@fortawesome/fontawesome-free/css/all.min.css";
import { useState } from "react";
import "./dashboard_components/Dashboard";
import { useNavigate } from "react-router-dom";
import React from 'react';
import {
  MDBContainer,
  MDBInput,
  MDBCheckbox,
  MDBBtn,
  MDBIcon,
  
}
from 'mdb-react-ui-kit';

function App() {
    const navigate = useNavigate();
      const [email, setEmail] = useState("");
      const [password, setPassword] = useState("");
      const [authenticated, setauthenticated] = useState(localStorage.getItem("authenticated") || false);
      const handleSubmit = async (e) => {
        e.preventDefault();
        try {
          const response = await fetch("http://localhost:8081/api/users/authenticate", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password }),
          });
          const data = await response.json();
          if (data.token) {
            setauthenticated(true);
            localStorage.setItem("authenticated", true);
            localStorage.setItem("token", data.token);
            navigate("/dashboard/bus-table");
          }
        } catch (error) {
          console.error(error);
        }
      };
  return (
    <MDBContainer className="p-3 my-5 d-flex flex-column w-50" >
        <form onSubmit={handleSubmit} >
      <MDBInput wrapperClass='mb-4' label='Email address' id='form1' type='email' name='email' value={email} onChange={(e) => setEmail(e.target.value)}/>
      <MDBInput wrapperClass='mb-4' label='Password' id='form2' type='password'  name="Password"
        onChange={(e) => setPassword(e.target.value)}/>

      <div className="d-flex justify-content-between mx-3 mb-4">
        <MDBCheckbox name='flexCheck' value='' id='flexCheckDefault' label='Remember me' />
        <a href="!#">Forgot password?</a>
      </div>

      <MDBBtn className="mb-4"  type="submit"   value="Submit" >Sign in</MDBBtn>

      <div className="text-center">
        <p>Not a member? <a href="/register-login">Register</a></p>
       
       
      </div>
      </form>
    </MDBContainer>
  );
}

export default App;