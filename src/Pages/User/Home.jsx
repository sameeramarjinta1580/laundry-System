import React, { useEffect, useState } from "react";
import axios from "axios";
import "./Home.css";
import { useNavigate } from "react-router-dom";
import UserNavbar from "../../components/UserNavbar";
import Footer from "../../components/Footer";
import {
  MdLocationOn,
  MdDeliveryDining,
  MdSchedule,
} from "react-icons/md";
import { AiOutlineClockCircle } from "react-icons/ai";
import { GiWashingMachine, GiTShirt } from "react-icons/gi";
import { FaQuoteLeft } from "react-icons/fa";
import { AiOutlineDown, AiOutlineUp } from "react-icons/ai";

const Home = () => {
  const [branches, setBranches] = useState([]);
  const [servicesMap, setServicesMap] = useState({});
  const [expandedBranch, setExpandedBranch] = useState(null);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    fetchBranches();
  }, []);

  const fetchBranches = async () => {
    try {
      const res = await axios.get("http://localhost:8080/branch/active");
      setBranches(res.data);
      res.data.forEach((b) => fetchServices(b.id));
      setLoading(false);
    } catch {
      setLoading(false);
    }
  };

  const fetchServices = async (branchId) => {
    try {
      const res = await axios.get(
        `http://localhost:8080/service/branch/${branchId}`
      );
      setServicesMap((prev) => ({ ...prev, [branchId]: res.data }));
    } catch {}
  };

  const toggleBranch = (id) => {
    // Only one branch can be expanded at a time
    setExpandedBranch((prev) => (prev === id ? null : id));
  };

  if (loading) return <div className="loader">Loading branches...</div>;

  return (
    <>
      <UserNavbar />

      <div className="home-container">
        {/* HERO */}
        <div className="hero">
          <h1>Smart Laundry Service 🧺</h1>
          <p>Fast pickup. Quality wash. Doorstep delivery.</p>
          <button className="hero-btn" onClick={() => navigate("/book")}>
            Book a Pickup
          </button>
        </div>

        {/* BRANCHES */}
        {branches.length === 0 ? (
          <div className="empty-state">
            <h3>No branches available 😔</h3>
            <p>We are expanding soon!</p>
          </div>
        ) : (
          <div className="branch-grid">
            {branches.map((branch, idx) => (
              <div
                key={branch.id}
                className="branch-card"
                style={{ animationDelay: `${idx * 0.1}s` }}
              >
                <h3>{branch.name}</h3>
                <p className="city">
                  <MdLocationOn /> {branch.city}
                </p>
                <p className="address">{branch.address}</p>
                <div>
                  <span className="time-badge">
                    <AiOutlineClockCircle /> Open: {branch.openingTime}
                  </span>
                  <span className="time-badge">
                    <AiOutlineClockCircle /> Close: {branch.closingTime}
                  </span>
                </div>

                {/* Services preview */}
                <div className="services-preview">
                  {servicesMap[branch.id]?.slice(0, 3).map((s) => (
                    <span key={s.id} className="service-chip">
                      <GiTShirt /> {s.name} ₹{s.pricePerItem}
                    </span>
                  ))}
                </div>

                {/* VIEW MORE BUTTON */}
                <button onClick={() => toggleBranch(branch.id)}>
                  {expandedBranch === branch.id ? (
                    <>
                      Hide Details <AiOutlineUp />
                    </>
                  ) : (
                    <>
                      View More <AiOutlineDown />
                    </>
                  )}
                </button>

                {/* EXPANDED SECTION */}
                <div
                  className={`expanded-section ${
                    expandedBranch === branch.id ? "open" : ""
                  }`}
                >
                  <h4>All Services</h4>
                  {servicesMap[branch.id]?.length > 0 ? (
                    servicesMap[branch.id].map((s, idx) => (
                      <div
                        key={s.id}
                        className="service-row"
                        style={{ animationDelay: `${idx * 0.1}s` }}
                      >
                        <img
                          src={s.imageUrl}
                          alt={s.name}
                          className="service-image"
                        />
                        <div className="service-info">
                          <span>{s.name}</span>
                          <p>Price: ₹{s.pricePerItem}</p>
                          <p>{s.description}</p>
                          <p>City: {s.city}</p>
                          <p>Branch: {s.branchName}</p>
                          <button className="book-service-btn">
                            Book Now
                          </button>
                        </div>
                      </div>
                    ))
                  ) : (
                    <p>No services available</p>
                  )}
                </div>
              </div>
            ))}
          </div>
        )}

        {/* HOW IT WORKS */}
        <div className="how-it-works">
          <h2>How It Works</h2>
          <div className="steps-grid">
            <div className="step-card">
              <MdSchedule className="step-icon" />
              <p>Schedule Pickup</p>
            </div>
            <div className="step-card">
              <GiWashingMachine className="step-icon" />
              <p>We Wash Clothes</p>
            </div>
            <div className="step-card">
              <MdDeliveryDining className="step-icon" />
              <p>Fast Delivery</p>
            </div>
          </div>
        </div>

        {/* TESTIMONIALS */}
        <div className="testimonials">
          <h2>What Our Customers Say</h2>
          <div className="testimonial-cards">
            <div className="testimonial-card">
              <FaQuoteLeft className="quote-icon" />
              <p>"Excellent service! Clothes were spotless."</p>
              <span>- John Doe</span>
            </div>
            <div className="testimonial-card">
              <FaQuoteLeft className="quote-icon" />
              <p>"Quick pickup and delivery. Highly recommend!"</p>
              <span>- Jane Smith</span>
            </div>
          </div>
        </div>
      </div>

      <Footer />
    </>
  );
};

export default Home;