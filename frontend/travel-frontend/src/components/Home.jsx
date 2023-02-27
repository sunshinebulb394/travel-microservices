import React from 'react';
import { Link } from 'react-router-dom';
import Navbar from './Navbar';
import Footer from './Footer';
import '../css/Home.css';
import FamilyCard from './FamilyCard';
import pic from '../image/bussignin.jpg'
function Index() {
  return (
   
    <div>
   <Navbar />
   <img width='100%' height='800px' src={pic} alt='bus'></img>
    <div class="container-fluid">
     
      <div class="justify ">
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' ,position:'relative',top:'10px'}}>
  <Link to="/booking-form">
    <button  type="button" className="btn btn-success btn-lg">
      <i className="bi bi-bus-front-fill"></i> &nbsp; Book a Trip
    </button>
  </Link>
</div>

    <FamilyCard/>
   
    </div>
   
    
</div>
<Footer />
</div>

  );
}

export default Index;
