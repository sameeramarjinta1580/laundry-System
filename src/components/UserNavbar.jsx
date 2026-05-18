import React from "react";
import { useNavigate, useLocation } from "react-router-dom";
import "./UserNavbar.css";
import WashifyLogo from "../assets/WashifyLogo";
import { FaHome, FaShoppingBag, FaUser, FaSignOutAlt } from "react-icons/fa";

const UserNavbar = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const handleLogout = () => {
    if (window.confirm("Are you sure you want to logout?")) {
      localStorage.removeItem("user");
      navigate("/login");
    }
  };

  const isActive = (path) => location.pathname === path;

  return (
    <div className="user-navbar">

      {/* LOGO */}
      <div className="nav-left" onClick={() => navigate("/home")}>
        <WashifyLogo width={120} />
      </div>

      {/* LINKS */}
      <div className="nav-links">

        <div
          className={`nav-item ${isActive("/home") ? "active" : ""}`}
          onClick={() => navigate("/home")}
        >
          <FaHome /> <span>Home</span>
        </div>

        <div
          className={`nav-item ${isActive("/book") ? "active" : ""}`}
          onClick={() => navigate("/book")}
        >
          <FaShoppingBag /> <span>Book</span>
        </div>

        <div
          className={`nav-item ${isActive("/my-orders") ? "active" : ""}`}
          onClick={() => navigate("/my-orders")}
        >
          <FaShoppingBag /> <span>Orders</span>
        </div>

        <div
          className={`nav-item ${isActive("/my-profile") ? "active" : ""}`}
          onClick={() => navigate("/my-profile")}
        >
          <FaUser /> <span>Profile</span>
        </div>

        <div className="logout-btn" onClick={handleLogout}>
          <FaSignOutAlt /> Logout
        </div>

      </div>
    </div>
  );
};

export default UserNavbar;