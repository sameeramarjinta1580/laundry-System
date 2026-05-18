// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./Pages/landing/LandingPage";
import Login from "./Pages/Auth/Login";
import Signup from "./Pages/Auth/Signup";
import {Toaster} from "react-hot-toast";
import About from "./Pages/About/About";
import Home from "./Pages/User/Home";
import BookService from "./Pages/User/BookService";
import PaymentSuccess from "./Pages/User/PaymentSuccess";
import MyOrders from "./Pages/User/MyOrders";
import MyProfile from "./Pages/User/MyProfile";
import AdminDashboard from "./Pages/Admin/AdminDashboard";
import AgentDashboard from "./Pages/Agent/AgentDashboard";
function App() {
  return (
    
    <>
      <Toaster position='top-right'/>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/" element={<LandingPage />} />
        <Route path="/about" element={<About />} />
        <Route path="/home" element={<Home />} />
        <Route path="/book" element={<BookService />} />
        <Route path="/payment-success" element={<PaymentSuccess />} />
        <Route path="/my-orders" element={<MyOrders />} />
        <Route path="/my-profile" element={<MyProfile />} />
        <Route path="/admin" element={<AdminDashboard />} />
        <Route path="/agent" element={<AgentDashboard />} />
      </Routes>
    </>
  );
}

export default App;