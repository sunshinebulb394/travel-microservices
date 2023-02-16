import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Navbar from './components/Navbar';
import Index from './components/Index';
import About from './components/About';
import Contact from './components/Contact';
import Footer from './components/Footer';
import Booking from './components/Booking';
import SearchBox from './components/SearchBox';
import ResultsPage from './components/ResultsPage';
import { useState } from 'react';
import BookingList from './components/BookingList';

function App() {

  return (
    <div>
   <Router>
   <Navbar/>
    <Routes>
      <Route path="/" element={<Index />} />
      <Route path="/about" element={<About />} />
      <Route path="/contact" element={<Contact />} />
      <Route path="/booking" element={<Booking />} />
      <Route path="/bookingList" element={<BookingList />} />
    </Routes>
   <Footer/>
   </Router>

  
  
    
    </div>
  );
}

export default App;
