// src/components/Footer.jsx
import React from "react";
import "./Footer.css";

const Footer = () => {
  return (
    <footer className="footer">
      <h2 style={{color:'white'}}>Washify</h2>
      <p>Smart Laundry. Delivered.</p>

      <div className="socials">
        <i className="fab fa-instagram"></i>
        <i className="fab fa-facebook"></i>
        <i className="fab fa-youtube"></i>
      </div>
    </footer>
  );
};

export default Footer;