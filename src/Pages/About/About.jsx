import React, { useEffect } from "react";
import Navbar from "../../components/Navbar";
import Footer from "../../components/Footer";
import "./About.css";

import AOS from "aos";
import "aos/dist/aos.css";

// Icons
import { FaTruck, FaMoneyBillWave, FaUsers } from "react-icons/fa";
import { MdOutlineLocalLaundryService, MdTrackChanges } from "react-icons/md";
import { FaCreditCard } from "react-icons/fa";

const About = () => {

  useEffect(() => {
    AOS.init({ duration: 1000, once: true });
  }, []);

  return (
    <>
      <Navbar />

      {/* HERO */}
      <section className="about-hero" data-aos="fade-up">
        <h1>About Washify</h1>
        <p>Smart Laundry. Delivered.</p>
      </section>

      {/* STORY */}
      <section className="about-story">
        <div className="story-container">
          <div className="story-img" data-aos="fade-right">
            <MdOutlineLocalLaundryService />
          </div>

          <div className="story-text" data-aos="fade-left">
            <h2>Our Story</h2>
            <p>
              Washify is a modern laundry platform designed to simplify your daily life.
              We connect users, laundry services, and delivery agents into one seamless
              system with real-time tracking and smart management.
            </p>
          </div>
        </div>
      </section>

      {/* FEATURES */}
      <section className="about-features">
        <h2 data-aos="fade-up">What We Offer</h2>

        <div className="feature-cards">
          <div className="feature-card" data-aos="zoom-in">
            <MdOutlineLocalLaundryService />
            <h3>Easy Booking</h3>
            <p>Book laundry in seconds</p>
          </div>

          <div className="feature-card" data-aos="zoom-in">
            <FaTruck />
            <h3>Doorstep Delivery</h3>
            <p>Pickup & delivery at your door</p>
          </div>

          <div className="feature-card" data-aos="zoom-in">
            <MdTrackChanges />
            <h3>Real-time Tracking</h3>
            <p>Track orders live</p>
          </div>

          <div className="feature-card" data-aos="zoom-in">
            <FaCreditCard />
            <h3>Secure Payments</h3>
            <p>Safe & easy payments</p>
          </div>
        </div>
      </section>

      {/* HOW IT WORKS */}
      <section className="about-steps">
        <h2 data-aos="fade-up">How It Works</h2>

        <div className="steps">
          <div className="step" data-aos="fade-up">
            <span>1</span>
            <p>Book your laundry</p>
          </div>

          <div className="step" data-aos="fade-up">
            <span>2</span>
            <p>Pickup by agent</p>
          </div>

          <div className="step" data-aos="fade-up">
            <span>3</span>
            <p>Processing</p>
          </div>

          <div className="step" data-aos="fade-up">
            <span>4</span>
            <p>Delivered</p>
          </div>
        </div>
      </section>

      {/* STATS */}
      <section className="stats">
        <div className="stat-box" data-aos="zoom-in">
          <FaUsers />
          <h2>500+</h2>
          <p>Happy Customers</p>
        </div>

        <div className="stat-box" data-aos="zoom-in">
          <MdOutlineLocalLaundryService />
          <h2>1000+</h2>
          <p>Orders Completed</p>
        </div>

        <div className="stat-box" data-aos="zoom-in">
          <FaTruck />
          <h2>50+</h2>
          <p>Delivery Agents</p>
        </div>
      </section>

      {/* MISSION */}
      <section className="about-section" data-aos="fade-up">
        <h2>Our Mission</h2>
        <p>
          Our mission is to simplify laundry services using technology,
          providing fast, reliable, and affordable solutions.
        </p>
      </section>

      <Footer />
    </>
  );
};

export default About;