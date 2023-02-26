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
import BusTable from './components/dashboard_components/BusTable';
import AddBus from './components/dashboard_components/AddBus';
import EditBus from './components/dashboard_components/EditBus';
import DriverTable from './components/dashboard_components/DriverTable';
import EditDriver from './components/dashboard_components/EditDriver';
import AddDriver from './components/dashboard_components/AddDriver';
import Bookings from './components/dashboard_components/Bookings';
import Users from './components/dashboard_components/Users';

function App() {

  return (
    <Router>
       <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" redirectUrl={'/'} element={<Login />} />
        <Route path="/" element={<Index />} />
        <Route path="/dashboard" element={<Dashboard />}>
          <Route path="bus-table" element={<BusTable />} />
          <Route path="addbus" element={<AddBus />} />
          <Route path="editbus/:id" element={<EditBus />} />
          <Route path="driver-table" element={<DriverTable />} />
          <Route path="editdriver/:id" element={<EditDriver />} />
          <Route path="addDriver" element={<AddDriver/>} />
          <Route path="bookings" element={<Bookings/>} />
          <Route path="users" element={<Users/>} />
        </Route>
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/booking" element={<Booking />} />
        <Route path="/bookingList" element={<BookingList />} />
      </Routes>
      
    </Router>
    
  );
}

export default App;
