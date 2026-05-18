import React, { useState } from "react";
import "./Auth.css";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate, Link } from "react-router-dom";
import WashifyLogo from "../../assets/WashifyLogo";
import Navbar from "../../components/Navbar";
import Footer from "../../components/Footer";
import { FaEye, FaEyeSlash, FaUser, FaEnvelope, FaPhone, FaMapMarkerAlt, FaLock } from "react-icons/fa";
import Confetti from "react-confetti";

const Signup = () => {
  const navigate = useNavigate();

  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
    phone: "",
    address: ""
  });

  const [showPass, setShowPass] = useState(false);
  const [strength, setStrength] = useState("");
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  const handleChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  // 🔐 Password Strength
  const getStrength = (password) => {
    if (password.length < 6) return "weak";
    if (/[A-Z]/.test(password) && /[0-9]/.test(password)) return "strong";
    return "medium";
  };

  const handlePassword = (e) => {
    setData({ ...data, password: e.target.value });
    setStrength(getStrength(e.target.value));
  };

  // ✅ VALIDATION
  const validate = () => {
    if (!data.name || data.name.length < 3) {
      toast.error("Name must be at least 3 characters");
      return false;
    }

    if (!data.email.includes("@")) {
      toast.error("Invalid email");
      return false;
    }

    if (!data.password || data.password.length < 6) {
      toast.error("Password must be at least 6 characters");
      return false;
    }

    if (!/^[0-9]{10}$/.test(data.phone)) {
      toast.error("Phone must be 10 digits");
      return false;
    }

    if (!data.address) {
      toast.error("Address is required");
      return false;
    }

    return true;
  };

  const handleSignup = async () => {
    if (!validate()) return;

    setLoading(true);

    try {
      await axios.post("http://localhost:8080/auth/register", data);
      setSuccess(true);
      toast.success("Registered Successfully");

      setTimeout(() => {
        navigate("/login");
      }, 1500);

    } catch (err) {
      if (err.response && err.response.data) {
        Object.values(err.response.data).forEach((msg) => {
          toast.error(msg);
        });
      } else {
        toast.error("Signup Failed");
      }
    }

    setLoading(false);
  };

  return (
    <>
      <Navbar />
      {success && <Confetti />}

      <div className="auth-container">

        {/* LEFT */}
        <div className="auth-left">
          <WashifyLogo width={350} />
          <p>Smart Laundry. Delivered.</p>
        </div>

        {/* RIGHT */}
        <div className="auth-right">
          <div className="auth-form">
            <h2>Sign Up</h2>

            {/* NAME */}
            <div className="input-group">
              <FaUser className="input-icon left"/>
              <input name="name" placeholder=" " onChange={handleChange} />
              <label>Name</label>
            </div>

            {/* EMAIL */}
            <div className="input-group">
              <FaEnvelope className="input-icon left"/>
              <input name="email" placeholder=" " onChange={handleChange} />
              <label>Email</label>
            </div>

            {/* PASSWORD */}
            <div className="input-group">
              <FaLock className="input-icon left"/>
              <input
                type={showPass ? "text" : "password"}
                placeholder=" "
                onChange={handlePassword}
              />
              <label>Password</label>

              <span className="input-icon right" onClick={() => setShowPass(!showPass)}>
                {showPass ? <FaEyeSlash /> : <FaEye />}
              </span>
            </div>

            {/* STRENGTH */}
            {data.password && (
              <span className={`strength ${strength}`}>
                {strength.toUpperCase()}
              </span>
            )}

            {/* PHONE */}
            <div className="input-group">
              <FaPhone className="input-icon left"/>
              <input name="phone" placeholder=" " onChange={handleChange} />
              <label>Phone</label>
            </div>

            {/* ADDRESS */}
            <div className="input-group">
              <FaMapMarkerAlt className="input-icon left"/>
              <input name="address" placeholder=" " onChange={handleChange} />
              <label>Address</label>
            </div>

            {/* BUTTON */}
            <button onClick={handleSignup}>
              {loading ? "Creating Account..." : "Register"}
            </button>

            <Link to="/login" className="link-text">
              Already have an account? Login
            </Link>

          </div>
        </div>

      </div>

      <Footer />
    </>
  );
};

export default Signup;