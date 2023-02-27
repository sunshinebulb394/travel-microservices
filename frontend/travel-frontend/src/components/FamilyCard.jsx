import React from 'react'
import {Link} from 'react-router-dom'
import '../css/Card.css';


function FamilyCard() {
  return (
    <>
    <div className='card-container1'>
    {/* <div className='picdiv'> */}
      <div className='card-container'>
        <img width='220px' height='200px' src='https://thumbs.dreamstime.com/b/tour-bus-gray-sightseeing-charter-parked-urban-setting-80448029.jpg' alt=''/>
        <button className="cart">VIP BUSES</button>
       <h2>VIP Buses Info</h2>
       <Link to = '/VipInfo'> Show Details</Link>
    </div>
  
    <div className='card-container'>
    {/* <img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSjw2U1YmG73lFGNcjufp956tSBKCvhrO4QdWM_33L9_w&s' alt=''/> */}
    <img width='220px' height='200px'  src='https://thumbs.dreamstime.com/b/tour-bus-21182249.jpg' alt=''/>
        <button className="cart">VVIP BUSES</button>
       <h2>VVIP Buses Info </h2>
       <Link to = '/VVIP'> Show Details</Link>
    </div>

    <div className='card-container'>
    <img  width='220px' height='200px' src='https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Toyota_Coaster_GX_XZB70.jpg/280px-Toyota_Coaster_GX_XZB70.jpg' alt=''/>
        <button className="cart">MINI VANS</button>
       <h2>MiniBus Info </h2>
       <Link to = '/MiniBus'> Show Details</Link>
    </div>

   

    </div>
    {/* </div> */}
    
    </>
    
  )
}

export default FamilyCard
