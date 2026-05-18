import React from "react";
import AOS from "aos";
import "aos/dist/aos.css";
import { useEffect } from "react";
import Navbar from "../../components/Navbar";
import Footer from "../../components/Footer";
import "./Landing.css";
import { Link } from "react-router-dom";
import { FaTshirt, FaBolt, FaShieldAlt } from "react-icons/fa";
import { MdLocalLaundryService } from "react-icons/md";
import { GiClothes } from "react-icons/gi";
import { BiDollar } from "react-icons/bi";

const LandingPage = () => {
  useEffect(() => {
  AOS.init({ duration: 1000, once: true });
}, []);
  return (
    <>
      <Navbar />

      {/* HERO */}
      <section className="hero" data-aos="fade-up">
        <div className="hero-content">
          <h1>Laundry made simple</h1>
          <p>
            Book, track & get your laundry delivered at your doorstep with ease
          </p>
          <Link to="/login">
            <button className="hero-btn">Get Started</button>
          </Link>
        </div>
      </section>

      {/* SERVICES */}
      <section className="services">
        <h2>Our Services</h2>
        <div className="cards">
          <div className="card" data-aos="fade-up">
            <div className="icon"><MdLocalLaundryService /></div>
            <h3>Wash & Fold</h3>
            <p>Quick and clean washing service for daily clothes</p>
          </div>

          <div className="card" data-aos="zoom-in">
            <div className="icon"><FaTshirt /></div>
            <h3>Ironing</h3>
            <p>Perfect ironing to keep your clothes crisp</p>
          </div>

          <div className="card" data-aos="fade-up">
            <div className="icon"><GiClothes /></div>
            <h3>Dry Cleaning</h3>
            <p>Premium care for your delicate garments</p>
          </div>
        </div>
      </section>

      {/* WHY US */}
      <section className="why">
        <h2>Why Choose Washify?</h2>
        <div className="cards">
          <div className="card" data-aos="fade-up">
            <div className="icon"><FaBolt /></div>
            <h3>Fast Delivery</h3>
            <p>Lightning fast pickup & delivery</p>
          </div>

         <div className="card" data-aos="fade-up">
            <div className="icon"><BiDollar /></div>
            <h3>Affordable</h3>
            <p>Best prices with top quality service</p>
          </div>

          <div className="card" data-aos="fade-up">
            <div className="icon"><FaShieldAlt /></div>
            <h3>Trusted Service</h3>
            <p>100% safe & reliable laundry care</p>
          </div>
        </div>
      </section>

      <Footer />
    </>
  );
};

export default LandingPage;