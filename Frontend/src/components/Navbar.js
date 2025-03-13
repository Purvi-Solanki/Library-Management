import React from "react";
import { useNavigate } from "react-router-dom";
import logo from "../assets/logo.png"; // Update to use logo image
import '../styles/components/Navbar.css';
const Navbar = () => {
  const navigate = useNavigate(); 

  const handleLogout = () => {
    localStorage.removeItem("email"); 
    console.log("User logged out, redirecting..."); 
    navigate("/", { replace: true });
  };

  return (
    <nav className="navbar">
      <div className="navbar-content">
        <div className="navbar-brand">
          <img src={logo} alt="Logo" className="nav-logo" />
          <div className="brand-text">LIBRARY MANAGEMENT SYSTEM</div>
        </div>
        <button className="btn btn-outline logout-btn" onClick={handleLogout}>
          <span className="logout-text">Logout</span>
        </button>
      </div>
    </nav>
  );
};

export default Navbar;
