// src/components/Navbar.jsx
import React from "react";
import "./Navbar.css";
import { Link } from "react-router-dom";
import WashifyLogo from "../assets/WashifyLogo";

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="logo">
        <WashifyLogo width={120} />
      </div>

      <ul className="nav-links">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/about">About Us</Link></li>
        <li><Link to="/signup" className="login-btn">Sign Up</Link></li>
        <li><Link to="/login" className="login-btn">Sign In</Link></li>
      </ul>
    </nav>
  );
};

export default Navbar;