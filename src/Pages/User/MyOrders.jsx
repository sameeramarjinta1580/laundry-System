import React, { useEffect, useState } from "react";
import axios from "axios";
import "./MyOrders.css";
import UserNavbar from "../../components/UserNavbar";
import Footer from "../../components/Footer";
import { GiTShirt } from "react-icons/gi";

const MyOrders = () => {
  const [orders, setOrders] = useState([]);
  const userString = localStorage.getItem("user");
  const user = userString ? JSON.parse(userString) : null;
  const userId = user?.id;

  useEffect(() => {
    if (!userId) {
      alert("Please login first");
      return;
    }
    fetchOrders();
  }, [userId]);

  const fetchOrders = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/booking/user/${userId}`);
      setOrders(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  const cancelOrder = async (id) => {
    if (!window.confirm("Cancel this order?")) return;
    await axios.delete(`http://localhost:8080/booking/cancel/${id}`);
    fetchOrders();
  };

  return (
    <>
      <UserNavbar />

      <div className="orders-container">
        <h1>My Orders 📦</h1>

        {orders.length === 0 ? (
          <p className="no-orders">No orders found. Book a service now!</p>
        ) : (
          <div className="orders-grid">
            {orders.map((o) => (
              <div className="order-card" key={o.id}>
                <div className="order-header">
                  <h3>{o.serviceName}</h3>
                  <div className="status-badges">
                    <span className={`payment ${o.paymentStatus}`}>
                      {o.paymentStatus}
                    </span>
                    <span className={`status ${o.orderStatus}`}>
                      {o.orderStatus}
                    </span>
                  </div>
                </div>

                <p className="branch-info">🏢 {o.branchName}</p>

                <div className="order-items">
                  <p><GiTShirt className="item-icon" /> Shirts: {o.shirtCount}</p>
                  <p>Pants: {o.pantCount}</p>
                  <p> Other: {o.otherItems || "None"}</p>
                </div>

                <p className="total-price">💰 ₹{o.totalPrice}</p>

                {o.orderStatus !== "DELIVERED" && (
                  <button className="cancel-btn" onClick={() => cancelOrder(o.id)}>
                    Cancel Order
                  </button>
                )}
              </div>
            ))}
          </div>
        )}
      </div>

      <Footer />
    </>
  );
};

export default MyOrders;