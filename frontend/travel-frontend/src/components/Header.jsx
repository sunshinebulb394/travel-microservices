import { React } from "react";
import '../css/Header.css';
import { Link } from 'react-router-dom';

function Header() {
    return (
      <header>
        <img src="https://example.com/logo.png" alt="Logo" />
        <nav>
          <ul>
            <li><Link to="/about">About</Link></li>
            <li><Link to="/contact">Contact</Link></li>
          </ul>
        </nav>
      </header>
    );
  }
  
  export default Header;