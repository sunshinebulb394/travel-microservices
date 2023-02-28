import React from 'react';
import { useState, useEffect } from 'react';
import {
  MDBBtn,
  MDBContainer,
  MDBRow,
  MDBCol,
  MDBCard,
  MDBCardBody,
  MDBInput,
  MDBCheckbox,
  MDBIcon
}
from 'mdb-react-ui-kit';

function App() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [firstname, setFirstname] = useState('');
  const [lastname, setLastname] = useState('');
  const [token, setToken] = useState(null);
  const [message, setMessage] = useState("");

  useEffect(() => {
    console.log('Token updated:', token);
  }, [token]);

  const handleSubmit = async (event) => {
    event.preventDefault();

    // Send the form data to the API
    const response = await fetch('http://localhost:8081/api/users/register/user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email,
        password,
        firstname,
        lastname,
      }),
    });

    if (response.ok) {
      const data = await response.json(); // Extract the JSON response data
      const tokn = data.token; // Extract the token from the response data
      setToken(tokn); // Store the token in state
      console.log('User created successfully');
      setMessage("User created successfully");
      setEmail("");
      setPassword("");
      setFirstname("");
      setLastname("");

      // TODO: Store the token in state or local storage
    } else {
      console.error('Error creating user:', response.statusText);
      // TODO: Handle registration error
    }
  };

  return (
    <MDBContainer fluid className='p-4 background-radial-gradient overflow-hidden'>
   
      <form onSubmit={handleSubmit}>
      <MDBRow>
     
      

        <MDBCol md='4' className='center'>
        {message && <div className="alert alert-success" role="alert">{message}</div>}
          <div id="radius-shape-1" className="position-absolute rounded-circle shadow-5-strong"></div>
          <div id="radius-shape-2" className="position-absolute shadow-5-strong"></div>

          <MDBCard className='my-5 bg-glass'>
            <MDBCardBody className='p-5'>

              <MDBRow>
                <MDBCol col='6'>
                  <MDBInput wrapperClass='mb-4' label='First name' id='form1' type='text' value={firstname} onChange={(e) => setFirstname(e.target.value)}/>
                </MDBCol>

                <MDBCol col='6'>
                  <MDBInput wrapperClass='mb-4' label='Last name' id='form2' type='text' value={lastname} onChange={(e) => setLastname(e.target.value)}/>
                </MDBCol>
              </MDBRow>

              <MDBInput wrapperClass='mb-4' label='Email' id='form3' type='email' value={email} onChange={(e) => setEmail(e.target.value)} />
              <MDBInput wrapperClass='mb-4' label='Password' id='form4' value={password} onChange={(e) => setPassword(e.target.value)} type='password'/>

              
              <MDBBtn className='w-100 mb-4' type="submit" size='md'>Register</MDBBtn>

              <div className="text-center">
              <p> <a href='/login-register'>Login</a></p>
            
              </div>

            </MDBCardBody>
          </MDBCard>

        </MDBCol>

      </MDBRow>
      </form>
    </MDBContainer>
  );
}

export default App;