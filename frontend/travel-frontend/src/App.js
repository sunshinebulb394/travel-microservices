import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Navbar from './components/Navbar';
import Index from './components/Index';
import About from './components/About';
import Contact from './components/Contact';
import Booking from './components/Booking';
import BookingList from './components/BookingList';
import Register from './components/Register';
import Login from './components/Login';
import Dashboard from './components/dashboard_components/Dashboard';
import Table from './components/dashboard_components/Table';

function App() {

  return (
    <Router>
       <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" redirectUrl={'/'} element={<Login />} />
        <Route path="/" element={<Index />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/booking" element={<Booking />} />
        <Route path="/bookingList" element={<BookingList />} />
        <Route path="/buses" element={<Dashboard />} />
      </Routes>
      
    </Router>
    
  );
}

export default App;
