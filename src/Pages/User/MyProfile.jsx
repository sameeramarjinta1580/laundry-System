import React, { useEffect, useState } from "react";
import axios from "axios";
import "./MyProfile.css";
import UserNavbar from "../../components/UserNavbar";
import Footer from "../../components/Footer";
import toast from "react-hot-toast";
import { FaUserCircle } from "react-icons/fa";

const MyProfile = () => {
  const userData = JSON.parse(localStorage.getItem("user"));
  const userId = userData?.id;

  const [user, setUser] = useState({});
  const [edit, setEdit] = useState(false);

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8080/auth/profile?userId=${userId}`
      );
      setUser(res.data);
    } catch {
      toast.error("Failed to load profile");
    }
  };

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const updateProfile = async () => {
    try {
      await axios.put(
        `http://localhost:8080/auth/update-profile`,
        null,
        {
          params: {
            userId,
            name: user.name,
            phone: user.phone,
            address: user.address,
          },
        }
      );

      toast.success("Profile updated ✅");
      localStorage.setItem("user", JSON.stringify(user));
      setEdit(false);
    } catch {
      toast.error("Update failed ❌");
    }
  };

  return (
    <>
      <UserNavbar />

      <div className="profile-container">
        <div className="profile-card">
          <div className="avatar">
            {user.profilePic ? (
              <img src={user.profilePic} alt="avatar" />
            ) : (
              <FaUserCircle className="default-avatar" />
            )}
          </div>

          <h2>My Profile</h2>

          <div className="form-group">
            <label>Name</label>
            <input
              name="name"
              value={user.name || ""}
              disabled={!edit}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Email</label>
            <input value={user.email || ""} disabled />
          </div>

          <div className="form-group">
            <label>Phone</label>
            <input
              name="phone"
              value={user.phone || ""}
              disabled={!edit}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Address</label>
            <textarea
              name="address"
              value={user.address || ""}
              disabled={!edit}
              onChange={handleChange}
            />
          </div>

          <div className="buttons">
            {!edit ? (
              <button className="edit-btn" onClick={() => setEdit(true)}>
                Edit Profile
              </button>
            ) : (
              <>
                <button className="save-btn" onClick={updateProfile}>
                  Save
                </button>
                <button className="cancel-btn" onClick={() => setEdit(false)}>
                  Cancel
                </button>
              </>
            )}
          </div>
        </div>
      </div>

      <Footer />
    </>
  );
};

export default MyProfile;