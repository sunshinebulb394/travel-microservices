import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import jwt_decode from "jwt-decode";
import Table from "./Table";
import Sidebar from "./SideBar";

const Dashboard = () => {
  const [authenticated, setAuthenticated] = useState(false);
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [role, setRole] = useState("");
  const [authorize,setAuthroize] = useState(false);


  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/login");
    } else {
      const decoded = jwt_decode(token);
      setRole(decoded.role);
      if(role === "ADMIN"){
        setAuthroize(true);
        console.log("you are authorized to view this page")
      }else console.log("you are not authorized to view this page")
      setUsername(decoded.sub);
      setAuthenticated(true);
    }
  }, [navigate, role]);

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
  };

  return (
    <div>
      <Sidebar/>
      <div style={{textAlign: 'right', padding: '20px', fontFamily: 'Arial'}}>
  <h1>Welcome, {username}!</h1>
</div>

      {
        authenticated && authorize &&(
          <Table/>
        )
      }
    </div>
  );
};

export default Dashboard;
