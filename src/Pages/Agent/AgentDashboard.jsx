import React, { useEffect, useState } from "react";
import axios from "axios";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import "./AgentDashboard.css";
import WashifyLogo from "../../assets/WashifyLogo";

const AgentDashboard = () => {

    const [activeTab, setActiveTab] = useState("bookings");
    const [bookings, setBookings] = useState([]);
    const [agentId, setAgentId] = useState(null);

    // ✅ GET AGENT FROM LOGGED USER
    const loadAgent = async () => {
        try {
            const user = JSON.parse(localStorage.getItem("user"));
            console.log("USER:", user);

            const res = await axios.get(
                `http://localhost:8080/agent/by-user/${user.id}`
            );

            console.log("AGENT RESPONSE:", res.data);

            setAgentId(res.data.id);

        } catch (err) {
            console.log("AGENT ERROR:", err);
            toast.error("Agent not found");
        }
    };

    // ✅ LOAD BOOKINGS
    const loadBookings = async (id) => {
        try {
            const res = await axios.get(
                `http://localhost:8080/agent/bookings/${id}`
            );
            console.log("BOOKINGS RESPONSE:", res.data);
            setBookings(res.data);
        } catch (err) {
            console.log(err);
            toast.error("Failed to load bookings");
        }
    };

    // ✅ UPDATE STATUS
    const updateStatus = async (bookingId, status) => {
        try {
            await axios.put(
                `http://localhost:8080/agent/status?bookingId=${bookingId}&status=${status}`
            );

            toast.success("Status Updated");
            loadBookings(agentId);

        } catch (err) {
            toast.error("Error updating status");
        }
    };

    // ✅ LOAD AGENT FIRST
    useEffect(() => {
        loadAgent();
    }, []);

    // ✅ LOAD BOOKINGS AFTER AGENT ID IS SET
    useEffect(() => {
        if (agentId) {
            loadBookings(agentId);
        }
    }, [agentId]);
    const navigate = useNavigate();

    const handleLogout = () => {
        if (window.confirm("Are you sure you want to logout?")) {
            localStorage.removeItem("user");   // remove logged user
            toast.success("Logged out successfully");
            navigate("/login");                // redirect to login page
        }
    };

    return (
        <div className="dashboard">

            {/* SIDEBAR */}
            <div className="sidebar">
                <WashifyLogo width={150} />
                <h2 style={{color:'white'}}>Agent Panel</h2>
                <ul>
                    <li
                        className={activeTab === "bookings" ? "active" : ""}
                        onClick={() => setActiveTab("bookings")}
                    >
                        My Bookings
                    </li>
                    <li className="logout" onClick={handleLogout}>
                        Logout
                    </li>
                </ul>
            </div>

            {/* ✅ MAIN CONTENT (IMPORTANT FIX) */}
            <div className="content">

                {activeTab === "bookings" && (
                    <>
                        <h2>Assigned Bookings</h2>

                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>User</th>
                                    <th>Address</th>
                                    <th>Items</th>
                                    <th>Price</th>
                                    <th>Status</th>
                                    <th>Payment</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>

                            <tbody>
                                {bookings.length > 0 ? (
                                    bookings.map((b) => (
                                        <tr key={b.id}>
                                            <td>{b.id}</td>
                                            <td>{b.user?.name}</td>
                                            <td>{b.address}</td>

                                            <td>
                                                Shirt: {b.shirtCount} <br />
                                                Pant: {b.pantCount} <br />
                                                Other: {b.otherItems || "None"}
                                            </td>

                                            <td>₹ {b.totalPrice}</td>
                                            <td>{b.orderStatus}</td>
                                            <td>{b.paymentStatus}</td>

                                            <td>
                                                {b.orderStatus === "ASSIGNED" && (
                                                    <button
                                                        onClick={() =>
                                                            updateStatus(b.id, "PICKED_UP")
                                                        }
                                                    >
                                                        Pickup
                                                    </button>
                                                )}

                                                {b.orderStatus === "PICKED_UP" && (
                                                    <button
                                                        onClick={() =>
                                                            updateStatus(b.id, "DELIVERED")
                                                        }
                                                    >
                                                        Deliver
                                                    </button>
                                                )}

                                                {b.orderStatus === "DELIVERED" && (
                                                    <span>Completed</span>
                                                )}
                                            </td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan="8">No bookings assigned</td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </>
                )}

            </div>
        </div>
    );
};

export default AgentDashboard;