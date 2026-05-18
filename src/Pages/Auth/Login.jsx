import React, { useState } from "react";
import "./Auth.css";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate, Link } from "react-router-dom";
import Navbar from "../../components/Navbar";
import Footer from "../../components/Footer";
import WashifyLogo from "../../assets/WashifyLogo";
import { FaEye, FaEyeSlash, FaEnvelope, FaLock } from "react-icons/fa";

const Login = () => {
  const navigate = useNavigate();

  const [loginData, setLoginData] = useState({
    email: "",
    password: ""
  });

  const [showPass, setShowPass] = useState(false);
  const [loading, setLoading] = useState(false);

  const [showForgot, setShowForgot] = useState(false);
  const [step, setStep] = useState(1);

  const [forgotData, setForgotData] = useState({
    email: "",
    otp: "",
    newPassword: ""
  });

  // 🔐 PASSWORD STRENGTH (for reset)
  const getStrength = (password) => {
    if (password.length < 6) return "weak";
    if (/[A-Z]/.test(password) && /[0-9]/.test(password)) return "strong";
    return "medium";
  };

  const [strength, setStrength] = useState("");

  // ✅ LOGIN
  const handleLogin = async () => {
    setLoading(true);
    try {
      const res = await axios.post("http://localhost:8080/auth/login", loginData);
      localStorage.setItem("user", JSON.stringify(res.data));

      toast.success("Login Success");

      const role = res.data.role;

      if (role === "ADMIN") navigate("/admin");
      else if (role === "AGENT") navigate("/agent");
      else navigate("/home");

    } catch {
      toast.error("Invalid Credentials");
    }
    setLoading(false);
  };

  // ===== OTP FLOW =====
  const sendOtp = async () => {
    try {
      await axios.post(`http://localhost:8080/auth/forgot-password?email=${forgotData.email}`);
      toast.success("OTP Sent");
      setStep(2);
    } catch {
      toast.error("Error sending OTP");
    }
  };

  const verifyOtp = async () => {
    try {
      await axios.post(`http://localhost:8080/auth/verify-otp?email=${forgotData.email}&otp=${forgotData.otp}`);
      toast.success("OTP Verified");
      setStep(3);
    } catch {
      toast.error("Invalid OTP");
    }
  };

  const resetPassword = async () => {
    try {
      await axios.post(`http://localhost:8080/auth/reset-password?email=${forgotData.email}&newPassword=${forgotData.newPassword}`);
      toast.success("Password Updated");

      setShowForgot(false);
      setStep(1);
      setForgotData({ email: "", otp: "", newPassword: "" });

    } catch {
      toast.error("Error updating password");
    }
  };

  return (
    <>
      <Navbar />

      <div className="auth-container">

        {/* LEFT */}
        <div className="auth-left">
          <WashifyLogo width={350} />
          <p>Smart Laundry. Delivered.</p>
        </div>

        {/* RIGHT */}
        <div className="auth-right">
          <div className="auth-form">
            <h2>Login</h2>

            {/* EMAIL */}
            <div className="input-group">
              <FaEnvelope className="input-icon left"/>
              <input
                placeholder=" "
                value={loginData.email}
                onChange={(e) =>
                  setLoginData({ ...loginData, email: e.target.value })
                }
              />
              <label>Email</label>
            </div>

            {/* PASSWORD */}
            <div className="input-group">
              <FaLock className="input-icon left"/>
              <input
                type={showPass ? "text" : "password"}
                placeholder=" "
                value={loginData.password}
                onChange={(e) =>
                  setLoginData({ ...loginData, password: e.target.value })
                }
              />
              <label>Password</label>

              <span className="input-icon right" onClick={() => setShowPass(!showPass)}>
                {showPass ? <FaEyeSlash /> : <FaEye />}
              </span>
            </div>

            {/* LOGIN BUTTON */}
            <button onClick={handleLogin}>
              {loading ? "Logging in..." : "Login"}
            </button>

            {/* FORGOT */}
            <span
              className="link-text"
              onClick={() => {
                setShowForgot(true);
                setStep(1);
              }}
            >
              Forgot Password?
            </span>

            <Link to="/signup" className="link-text">
              New user? Create account
            </Link>
          </div>
        </div>
      </div>

      {/* 🔐 PREMIUM OTP MODAL */}
      {showForgot && (
        <div className="modal">
          <div className="modal-content">

            {/* STEP 1 */}
            {step === 1 && (
              <>
                <h3>Enter Email</h3>
                <input
                  placeholder="Email"
                  onChange={(e) =>
                    setForgotData({ ...forgotData, email: e.target.value })
                  }
                />
                <button onClick={sendOtp}>Send OTP</button>
              </>
            )}

            {/* STEP 2 */}
            {step === 2 && (
              <>
                <h3>Verify OTP</h3>
                <input
                  placeholder="Enter OTP"
                  onChange={(e) =>
                    setForgotData({ ...forgotData, otp: e.target.value })
                  }
                />
                <button onClick={verifyOtp}>Verify</button>
              </>
            )}

            {/* STEP 3 */}
            {step === 3 && (
              <>
                <h3>Reset Password</h3>
                <input
                  type="password"
                  placeholder="New Password"
                  onChange={(e) => {
                    setForgotData({
                      ...forgotData,
                      newPassword: e.target.value
                    });
                    setStrength(getStrength(e.target.value));
                  }}
                />

                {forgotData.newPassword && (
                  <span className={`strength ${strength}`}>
                    {strength.toUpperCase()}
                  </span>
                )}

                <button onClick={resetPassword}>
                  Reset Password
                </button>
              </>
            )}

            <span  style={{ color: 'black', cursor: 'pointer' }} onClick={() => setShowForgot(false)}>
              Close
            </span>

          </div>
        </div>
      )}

      <Footer />
    </>
  );
};

export default Login;