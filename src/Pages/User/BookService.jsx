import React, { useState } from "react";
import axios from "axios";
import "./BookService.css";
import UserNavbar from "../../components/UserNavbar";
import Footer from "../../components/Footer";
import toast from "react-hot-toast";

const BookService = () => {
  const [otherItems, setOtherItems] = useState("");
  const [city, setCity] = useState("");
  const [branches, setBranches] = useState([]);
  const [selectedBranch, setSelectedBranch] = useState(null);
  const [services, setServices] = useState([]);
  const [selectedService, setSelectedService] = useState(null);

  const [shirtCount, setShirtCount] = useState(0);
  const [pantCount, setPantCount] = useState(0);
  const [deliveryType, setDeliveryType] = useState("SELF");
  const [paymentType, setPaymentType] = useState("COD");
  const [address, setAddress] = useState("");

  const userString = localStorage.getItem("user");
  const user = userString ? JSON.parse(userString) : null;
  const userId = user?.id;
  const useremail = user?.email;
  const phone = user?.phone;

  // Search City
  const searchCity = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/branch/city/${city}`);
      setBranches(res.data);
      setSelectedBranch(null);
      setServices([]);
    } catch {
      toast.error("Error fetching branches");
    }
  };

  // Select Branch
  const selectBranch = async (branch) => {
    setSelectedBranch(branch);
    const res = await axios.get(
      `http://localhost:8080/service/branch/${branch.id}`
    );
    setServices(res.data);
  };

  // Total Price
  const extraCount = otherItems ? 1 : 0;
  const total = selectedService
    ? (Number(shirtCount) + Number(pantCount) + extraCount) *
      Number(selectedService.pricePerItem)
    : 0;

  // Booking
  const handleBooking = async () => {
    if (!selectedBranch) return toast.error("Please select a branch");
    if (!selectedService) return toast.error("Please select a service");
    if (!address.trim()) return toast.error("Address is required");
    if (shirtCount < 0 || pantCount < 0)
      return toast.error("Invalid item count");
    if (shirtCount === 0 && pantCount === 0 && !otherItems)
      return toast.error("Add at least one item");

    try {
      const bookingRes = await axios.post("http://localhost:8080/booking/create", {
        userId,
        branchId: selectedBranch.id,
        serviceId: selectedService.id,
        shirtCount,
        pantCount,
        otherItems,
        deliveryType,
        paymentType,
        address,
      });

      const booking = bookingRes.data;

      if (paymentType === "COD") {
        toast.success("Order placed successfully 🎉");
        setSelectedService(null);
        return;
      }

      const paymentRes = await axios.post(
        "http://localhost:8080/payment/create-order",
        {
          bookingId: booking.id,
          amount: booking.totalPrice,
          email: useremail,
          phone: phone,
        }
      );

      const { sessionId } = paymentRes.data;
      const cashfree = new window.Cashfree({ mode: "sandbox" });
      cashfree.checkout({
        paymentSessionId: sessionId,
        returnUrl: `http://localhost:5173/payment-success?order_id=${booking.orderId}`,
      });
    } catch (err) {
      console.error(err);
      if (err.response && err.response.data) {
        const errors = err.response.data;
        if (typeof errors === "object") Object.values(errors).forEach((msg) => toast.error(msg));
        else toast.error(errors);
      } else toast.error("Booking failed ❌");
    }
  };

  return (
    <>
      <UserNavbar />
      <div className="book-container">
        <h1>Book Laundry Service 🧺</h1>

        {/* CITY SEARCH */}
        <div className="city-search">
          <input
            type="text"
            placeholder="Enter city..."
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />
          <button onClick={searchCity}>Search</button>
        </div>

        {/* BRANCHES */}
        {branches.length === 0 ? (
          <p>No branches available in your selected city 😔</p>
        ) : (
          <div className="branch-list">
            {branches.map((b) => (
              <div
                key={b.id}
                className={`branch-card ${selectedBranch?.id === b.id ? "active" : ""}`}
                onClick={() => selectBranch(b)}
              >
                <h3>{b.name}</h3>
                <p>{b.address}</p>
              </div>
            ))}
          </div>
        )}

        {/* SERVICES */}
        {services.length > 0 && (
          <div className="services">
            <h2>Select Service</h2>
            {services.map((s) => (
              <div
                key={s.id}
                className={`service-card ${selectedService?.id === s.id ? "active" : ""}`}
                onClick={() => setSelectedService(s)}
              >
                <h4>{s.name}</h4>
                <p>{s.description}</p>
                <span>₹{s.pricePerItem}</span>
              </div>
            ))}
          </div>
        )}

        {/* PREMIUM MODAL FORM */}
        {selectedService && (
          <div className="modal">
            <div className="modal-form">
              <h2>Complete Your Booking</h2>
              <div className="form-group">
                <label>Shirts:</label>
                <input
                  type="number"
                  min="0"
                  value={shirtCount}
                  onChange={(e) => setShirtCount(Number(e.target.value))}
                />
              </div>
              <div className="form-group">
                <label>Pants:</label>
                <input
                  type="number"
                  min="0"
                  value={pantCount}
                  onChange={(e) => setPantCount(Number(e.target.value))}
                />
              </div>
              <div className="form-group">
                <label>Other Items:</label>
                <input
                  type="text"
                  placeholder="e.g. 2 Saree, 1 Curtain"
                  value={otherItems}
                  onChange={(e) => setOtherItems(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label>Delivery Type:</label>
                <select onChange={(e) => setDeliveryType(e.target.value)}>
                  <option value="SELF">Self Pickup</option>
                  <option value="HOME">Home Delivery</option>
                </select>
              </div>
              <div className="form-group">
                <label>Payment Type:</label>
                <select onChange={(e) => setPaymentType(e.target.value)}>
                  <option value="COD">Cash on Delivery</option>
                  <option value="ONLINE">Online Payment</option>
                </select>
              </div>
              <div className="form-group">
                <label>Address:</label>
                <textarea
                  placeholder="Enter Address"
                  value={address}
                  onChange={(e) => setAddress(e.target.value)}
                />
              </div>
              <h3>Total: ₹{total || 0}</h3>
              <div className="modal-buttons">
                <button className="confirm-btn" onClick={handleBooking}>
                  Confirm Booking
                </button>
                <button
                  className="cancel-btn"
                  onClick={() => setSelectedService(null)}
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
      <Footer />
    </>
  );
};

export default BookService;