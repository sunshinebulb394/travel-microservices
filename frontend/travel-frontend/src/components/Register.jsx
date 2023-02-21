import { useState, useEffect } from 'react';

function Register() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [firstname, setFirstname] = useState('');
  const [lastname, setLastname] = useState('');
  const [token, setToken] = useState(null);

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
      console.log('User created successfully with token:', tokn);
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
    <form onSubmit={handleSubmit}>
      <h2>Register</h2>
      <label>
        Email:
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
      </label>
      <br />
      <label>
        Password:
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </label>
      <br />
      <label>
        Firstname:
        <input type="text" value={firstname} onChange={(e) => setFirstname(e.target.value)} />
      </label>
      <br />
      <label>
        Lastname:
        <input type="text" value={lastname} onChange={(e) => setLastname(e.target.value)} />
      </label>

      <button type="submit">Register</button>
    </form>
  );
}

export default Register;
