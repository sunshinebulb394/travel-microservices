import { useState } from "react";
import "./dashboard_components/Dashboard";
import { useNavigate } from "react-router-dom";

const Login = () => {
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
        navigate("/dashboard");
      }
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <div>
      <p>Welcome Back</p>
      <form onSubmit={handleSubmit}>
    <label htmlFor="email">Email</label>
      <input
        type="email"
        name="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
<label htmlFor="password">Password</label>
      <input
        type="password"
        name="Password"
        onChange={(e) => setPassword(e.target.value)}
      />
      <input type="submit" value="Submit" />
      </form>
    </div>
  );
};

export default Login;
