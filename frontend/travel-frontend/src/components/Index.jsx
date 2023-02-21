import React from 'react';
import { Link } from 'react-router-dom';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

function Index() {
  return (
   
    <div>
   <Navbar />
    <div class="container-fluid">
      <div class="justify ">
      <Link to="/booking">
      <button type="button" className="btn btn-success btn-sm">
        <i className="bi bi-bus-front-fill"></i> &nbsp; Book a Trip
      </button>
    </Link>
    </div>
  <h1>Travel App</h1>
<Footer />
</div>
    

   
      <h1>Welcome to my website!</h1>
      </div>

  );
}

export default Index;
