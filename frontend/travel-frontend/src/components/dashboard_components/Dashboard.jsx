import { useEffect, useState } from "react";
import { useNavigate, Outlet } from "react-router-dom";
import jwt_decode from "jwt-decode";
import Sidebar from "./SideBar";



const Dashboard = () => {
  const [authenticated, setAuthenticated] = useState(false);
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [role, setRole] = useState("");
  const [authorize,setAuthroize] = useState(false);

  localStorage.setItem("role",role);
  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      navigate("/login");
    } else {
      const decoded = jwt_decode(token);
      setRole(decoded.role);
      if(role !== null){
        setAuthroize(true);
        console.log("you are authorized to view this page")
      }else console.log("you are not authorized to view this page")
      setUsername(decoded.sub);
      setAuthenticated(true);
    }
  }, [navigate, role]);


  return (
    <>
    <Sidebar/>
    <div style={{textAlign: 'right', padding: '20px', fontFamily: 'Arial'}}>
  <h1>Welcome, {username}!</h1>
</div>
 
       <Outlet/>
      
   </>
  );
};

export default Dashboard;
