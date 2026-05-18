import React, { useEffect, useState } from "react";
import axios from "axios";
import "./AdminDashboard.css";
import toast from "react-hot-toast";
import WashifyLogo from "../../assets/WashifyLogo";

const AdminDashboard = () => {

  const [activeTab, setActiveTab] = useState("dashboard");

  const [dashboard, setDashboard] = useState({});
  const [bookings, setBookings] = useState([]);
  const [agents, setAgents] = useState([]);
  const [users, setUsers] = useState([]);

  const [loading, setLoading] = useState(false);

  const [showModal, setShowModal] = useState(false);
  const [editAgent, setEditAgent] = useState(null);
  const [search, setSearch] = useState("");
  const [services, setServices] = useState([]);
  const [branches, setBranches] = useState([]);

  const [agentForm, setAgentForm] = useState({
    name: "",
    email: "",
    phone: "",
    address: "",
    city: "",
    active: true
  });
  const [showBranchModal, setShowBranchModal] = useState(false);
  const [editBranch, setEditBranch] = useState(null);

  const [branchForm, setBranchForm] = useState({
    name: "",
    city: "",
    address: "",
    openingTime: "",
    closingTime: "",
    active: true
  });

  // ================= LOAD =================


  const loadDashboard = async () => {
    const res = await axios.get("http://localhost:8080/admin/dashboard");
    setDashboard(res.data);
  };

  const loadBookings = async () => {
    setLoading(true);
    const res = await axios.get("http://localhost:8080/admin/bookings");
    setBookings(res.data);
    setLoading(false);
  };

  const loadAgents = async () => {
    const res = await axios.get("http://localhost:8080/agent");
    setAgents(res.data);
  };
  const loadServices = async () => {
    const res = await axios.get("http://localhost:8080/service/all");
    setServices(res.data);
  };
  const loadBranches = async () => {
    const res = await axios.get("http://localhost:8080/branch/all");
    setBranches(res.data);
  }
  const loadUsers = async () => {
    try {
      const res = await axios.get("http://localhost:8080/admin/user");
      setUsers(res.data);
    } catch (err) {
      toast.error("Failed to load users");
    }
  };
  useEffect(() => {
    loadDashboard();
    loadBookings();
    loadAgents();
    loadServices();
    loadBranches();

  }, []);
  useEffect(() => {
    if (activeTab === "users") {
      loadUsers();
    }
  }, [activeTab]);
  const [showServiceModal, setShowServiceModal] = useState(false);
  const [editService, setEditService] = useState(null);

  const [serviceForm, setServiceForm] = useState({
    name: "",
    description: "",
    pricePerItem: "",
    branchId: "",
    image: null
  });

  const openAddService = () => {
    setEditService(null);
    setServiceForm({
      name: "",
      description: "",
      pricePerItem: "",
      branchId: "",
      image: null
    });
    setShowServiceModal(true);
  };

  const openEditService = (s) => {
    setEditService(s);
    setServiceForm({
      name: s.name,
      description: s.description,
      pricePerItem: s.pricePerItem,
      branchId: s.branchId,
      image: null
    });
    setShowServiceModal(true);
  };

 const saveService = async () => {

  if (!serviceForm.name.trim()) {
    return toast.error("Service name required");
  }

  if (!serviceForm.description.trim()) {
    return toast.error("Description required");
  }

  if (!serviceForm.pricePerItem || serviceForm.pricePerItem <= 0) {
    return toast.error("Valid price required");
  }

  if (!serviceForm.branchId) {
    return toast.error("Select a branch");
  }

  // 👉 Image required only for ADD
  if (!editService && !serviceForm.image) {
    return toast.error("Image is required");
  }

  try {
    const formData = new FormData();
    formData.append("name", serviceForm.name);
    formData.append("description", serviceForm.description);
    formData.append("pricePerItem", serviceForm.pricePerItem);
    formData.append("branchId", serviceForm.branchId);

    if (serviceForm.image) {
      formData.append("image", serviceForm.image);
    }

    if (editService) {
      await axios.put(
        `http://localhost:8080/service/update/${editService.id}`,
        formData
      );
      toast.success("Service Updated");
    } else {
      await axios.post(
        "http://localhost:8080/service/add",
        formData
      );
      toast.success("Service Added");
    }

    setShowServiceModal(false);
    loadServices();

  } catch {
    toast.error("Error saving service");
  }
};

  const deleteService = async (id) => {
    if (!window.confirm("Delete service?")) return;

    await axios.delete(`http://localhost:8080/service/delete/${id}`);
    toast.success("Deleted");
    loadServices();
  };
  const openAddBranch = () => {
    setEditBranch(null);
    setBranchForm({
      name: "",
      city: "",
      address: "",
      openingTime: "",
      closingTime: "",
      active: true
    });
    setShowBranchModal(true);
  };

  const openEditBranch = (b) => {
    setEditBranch(b);
    setBranchForm({
      name: b.name,
      city: b.city,
      address: b.address,
      openingTime: b.openingTime,
      closingTime: b.closingTime,
      active: b.active
    });
    setShowBranchModal(true);
  };
 const saveBranch = async () => {

  if (!branchForm.name.trim()) {
    return toast.error("Branch name required");
  }

  if (!branchForm.city.trim()) {
    return toast.error("City required");
  }

  if (!branchForm.address.trim()) {
    return toast.error("Address required");
  }

  if (!branchForm.openingTime || !branchForm.closingTime) {
    return toast.error("Opening & Closing time required");
  }

  try {
    if (editBranch) {
      await axios.put(
        `http://localhost:8080/branch/update/${editBranch.id}`,
        branchForm
      );
      toast.success("Branch Updated");
    } else {
      await axios.post(
        "http://localhost:8080/branch/add",
        branchForm
      );
      toast.success("Branch Added");
    }

    setShowBranchModal(false);
    loadBranches();

  } catch {
    toast.error("Error saving branch");
  }
};
  const deleteBranch = async (id) => {
    if (!window.confirm("Delete branch?")) return;

    await axios.delete(`http://localhost:8080/branch/delete/${id}`);
    toast.success("Deleted");
    loadBranches();
  };

  // ================= LOGOUT =================

  const handleLogout = () => {
    localStorage.clear();
    toast.success("Logged out");
    window.location.href = "/login";
  };

  // ================= BOOKING =================

  const assignAgent = async (bookingId, agentId) => {
    if (!agentId) return;

    await axios.put(
      `http://localhost:8080/agent/assign?bookingId=${bookingId}&agentId=${agentId}`
    );

    toast.success("Agent Assigned");
    loadBookings();
  };

  const updateStatus = async (id, status) => {
    if (status === "Change") return;

    await axios.put(
      `http://localhost:8080/admin/update-status?id=${id}&status=${status}`
    );

    toast.success("Status Updated");
    loadBookings();
  };

  // ================= AGENTS =================

  const openAddModal = () => {
    setEditAgent(null);
    setAgentForm({
      name: "",
      email: "",
      phone: "",
      address: "",
      city: "",
      active: true
    });
    setShowModal(true);
  };

  const openEditModal = (agent) => {
    setEditAgent(agent);
    setAgentForm({
      name: agent.user?.name || "",
      email: agent.user?.email || "",
      phone: agent.user?.phone || "",
      address: agent.address || "",
      city: agent.city || "",
      active: agent.active ?? true
    });
    setShowModal(true);
  };

  const saveAgent = async () => {

  // ✅ VALIDATIONS
  if (!agentForm.name.trim()) {
    return toast.error("Name is required");
  }

  if (!agentForm.email.includes("@")) {
    return toast.error("Valid email required");
  }

  if (!/^\d{10}$/.test(agentForm.phone)) {
    return toast.error("Phone must be 10 digits");
  }

  if (!agentForm.address.trim()) {
    return toast.error("Address required");
  }

  if (!agentForm.city.trim()) {
    return toast.error("City required");
  }

  try {
    const payload = {
      user: {
        name: agentForm.name,
        email: agentForm.email,
        phone: agentForm.phone
      },
      agent: {
        address: agentForm.address,
        city: agentForm.city,
        active: agentForm.active
      }
    };

    if (editAgent) {
      await axios.put(
        `http://localhost:8080/admin/update/${editAgent.id}`,
        payload
      );
      toast.success("Agent Updated");
    } else {
      await axios.post("http://localhost:8080/agent/add", payload);
      toast.success("Agent Added");
    }

    setShowModal(false);
    loadAgents();

  } catch {
    toast.error("Error saving agent");
  }
};

  const deleteAgent = async (id) => {
    if (!window.confirm("Delete agent?")) return;

    try {
      await axios.delete(`http://localhost:8080/admin/agent/delete/${id}`);
      toast.success("Deleted");
      loadAgents();
    } catch {
      toast.error("Delete failed");
    }
  };

  const filteredAgents = agents.filter((a) =>
    (a.user?.name || "").toLowerCase().includes(search.toLowerCase())
  );

  // ================= UI =================

  return (
    <div className="admin-container">

      {/* SIDEBAR */}
      <div className="sidebar">
        <WashifyLogo width={150}/>
        <h2 style={{color:'white'}}> Admin</h2>
        <ul>
          <li onClick={() => setActiveTab("dashboard")}>Dashboard</li>
          <li onClick={() => setActiveTab("bookings")}>Bookings</li>
          <li onClick={() => setActiveTab("agents")}>Agents</li>
          <li onClick={() => setActiveTab("branches")}>Branches</li>
          <li onClick={() => setActiveTab("services")}>Services</li>
          <li onClick={() => setActiveTab("users")}>Users</li>
          <li className="logout" onClick={handleLogout}>
            Logout
          </li>
        </ul>
      </div>

      {/* MAIN */}
      <div className="main-content">

        {/* DASHBOARD */}
        {activeTab === "dashboard" && (
          <div className="dashboard-cards">
            <div className="card">Users: {dashboard.totalUsers}</div>
            <div className="card">Bookings: {dashboard.totalBookings}</div>
            <div className="card">Services: {dashboard.totalServices}</div>
            <div className="card">Branches: {dashboard.totalBranches}</div>
          </div>
        )}

        {/* BOOKINGS */}
        {activeTab === "bookings" && (
          <div>
            <h2>Bookings</h2>
           {bookings.length === 0 && <p>No bookings available</p>}
            {loading ? <p>Loading...</p> : (
              
              <table>
                <thead>
                  <tr>
                    <th>User</th>
                    <th>Phone</th>
                    <th>Service</th>
                    <th>Branch</th>
                    <th>Items</th>
                    <th>Price</th>
                    <th>Status</th>
                    <th>Payment</th>
                    <th>Agent</th>
                    <th>Assign</th>
                  </tr>
                </thead>

                <tbody>
                  {bookings.map((b) => (
                    <tr key={b.id}>
                      <td>{b.userName}</td>
                      <td>{b.userPhone}</td>
                      <td>{b.serviceName}</td>
                      <td>{b.branchName}</td>
                      <td>
                        Shirt: {b.shirtCount} <br />
                        Pant: {b.pantCount} <br />
                        Other: {b.otherItems || "None"}
                      </td>
                      <td>₹{b.totalPrice}</td>

                      <td>
                        <span className={`status ${b.orderStatus}`}>
                          {b.orderStatus}
                        </span>

                        <select onChange={(e) =>
                          updateStatus(b.id, e.target.value)
                        }>
                          <option>Change</option>
                          <option value="CONFIRMED">CONFIRMED</option>
                          <option value="IN_PROGRESS">IN_PROGRESS</option>
                          <option value="DELIVERED">DELIVERED</option>
                        </select>
                      </td>

                      <td>
                        <span className={`pay ${b.paymentStatus}`}>
                          {b.paymentStatus}
                        </span>
                      </td>

                      <td>
                        {b.agentName ? (
                          <span className="assigned">{b.agentName}</span>
                        ) : (
                          <span className="not-assigned">Not Assigned</span>
                        )}
                      </td>

                      <td>
                        <select onChange={(e) =>
                          assignAgent(b.id, e.target.value)
                        }>
                          <option value="">Select</option>
                          {agents.map((a) => (
                            <option key={a.id} value={a.id}>
                              {a.user?.name}
                            </option>
                          ))}
                        </select>
                      </td>

                    </tr>
                  ))}
                </tbody>
              </table>
            )}
          </div>
        )}

        {/* AGENTS */}
        {activeTab === "agents" && (
          <div>
            <h2>Agents</h2>

            <div className="top-bar">
              <input
                placeholder="Search agent..."
                value={search}
                onChange={(e) => setSearch(e.target.value)}
              />
              <button onClick={openAddModal}>+ Add Agent</button>
            </div>

            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Phone</th>
                  <th>City</th>
                  <th>Actions</th>
                </tr>
              </thead>

              <tbody>
                {filteredAgents.map((a) => (
                  <tr key={a.id}>
                    <td>{a.user?.name}</td>
                    <td>{a.user?.phone}</td>
                    <td>{a.city}</td>
                    <td>
                      <button onClick={() => openEditModal(a)}>Edit</button>
                      <button onClick={() => deleteAgent(a.id)}>Delete</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>

            {/* MODAL */}
            {showModal && (
              <div className="modal">
                <div className="modal-content">

                  <h3>{editAgent ? "Edit Agent" : "Add Agent"}</h3>

                  <input placeholder="Name" value={agentForm.name}
                    onChange={(e) => setAgentForm({ ...agentForm, name: e.target.value })}
                  />

                  <input placeholder="Email" value={agentForm.email}
                    onChange={(e) => setAgentForm({ ...agentForm, email: e.target.value })}
                  />

                  <input placeholder="Phone" value={agentForm.phone}
                    onChange={(e) => setAgentForm({ ...agentForm, phone: e.target.value })}
                  />

                  <input placeholder="Address" value={agentForm.address}
                    onChange={(e) => setAgentForm({ ...agentForm, address: e.target.value })}
                  />

                  <input placeholder="City" value={agentForm.city}
                    onChange={(e) => setAgentForm({ ...agentForm, city: e.target.value })}
                  />

                  <label>
                    Active:
                    <input type="checkbox" checked={agentForm.active}
                      onChange={(e) => setAgentForm({ ...agentForm, active: e.target.checked })}
                    />
                  </label>

                  <div className="modal-actions">
                    <button onClick={saveAgent}>Save</button>
                    <button onClick={() => setShowModal(false)}>Cancel</button>
                  </div>

                </div>
              </div>
            )}
          </div>
        )}
        {/* SERVICES */}
        {/* SERVICES CRUD */}
        {activeTab === "services" && (
          <div>
            <h2>Services</h2>

            <button onClick={openAddService}>+ Add Service</button>

            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Price</th>
                  <th>Branch</th>
                  <th>Image</th>
                  <th>Actions</th>
                </tr>
              </thead>

              <tbody>
                {services.map((s) => (
                  <tr key={s.id}>
                    <td>{s.name}</td>
                    <td>₹{s.pricePerItem}</td>
                    <td>{s.branchName}</td>

                    <td>
                      <img src={s.imageUrl} width="50" />
                    </td>

                    <td>
                      <button onClick={() => openEditService(s)}>Edit</button>
                      <button onClick={() => deleteService(s.id)}>Delete</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>

            {/* MODAL */}
            {showServiceModal && (
              <div className="modal">
                <div className="modal-content">
                  <h3>{editService ? "Edit" : "Add"} Service</h3>

                  <input placeholder="Name"
                    value={serviceForm.name}
                    onChange={(e) =>
                      setServiceForm({ ...serviceForm, name: e.target.value })
                    }
                  />

                  <input placeholder="Description"
                    value={serviceForm.description}
                    onChange={(e) =>
                      setServiceForm({ ...serviceForm, description: e.target.value })
                    }
                  />

                  <input placeholder="Price"
                    value={serviceForm.pricePerItem}
                    onChange={(e) =>
                      setServiceForm({ ...serviceForm, pricePerItem: e.target.value })
                    }
                  />

                  <select
                    value={serviceForm.branchId}
                    onChange={(e) =>
                      setServiceForm({ ...serviceForm, branchId: e.target.value })
                    }
                  >
                    <option>Select Branch</option>
                    {branches.map(b => (
                      <option key={b.id} value={b.id}>
                        {b.name}
                      </option>
                    ))}
                  </select>

                  <input type="file"
                    onChange={(e) =>
                      setServiceForm({ ...serviceForm, image: e.target.files[0] })
                    }
                  />

                  <button onClick={saveService}>Save</button>
                  <button onClick={() => setShowServiceModal(false)}>Cancel</button>
                </div>
              </div>
            )}

          </div>
        )}
        {activeTab === "branches" && (
          <div>
            <h2>Branches</h2>

            <button onClick={openAddBranch}>+ Add Branch</button>

            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>City</th>
                  <th>Address</th>
                  <th>Timing</th>
                  <th>Status</th>
                  <th>Services</th>
                  <th>Actions</th>
                </tr>
              </thead>

              <tbody>
                {branches.map((b) => (
                  <tr key={b.id}>
                    <td>{b.name}</td>
                    <td>{b.city}</td>
                    <td>{b.address}</td>
                    <td>{b.openingTime} - {b.closingTime}</td>
                    <td>{b.active ? "Active" : "Inactive"}</td>
                    <td>{b.totalServices}</td>

                    <td>
                      <button onClick={() => openEditBranch(b)}>Edit</button>
                      <button onClick={() => deleteBranch(b.id)}>Delete</button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>

            {/* MODAL */}
            {showBranchModal && (
              <div className="modal">
                <div className="modal-content">
                  <h3>{editBranch ? "Edit" : "Add"} Branch</h3>

                  <input placeholder="Name"
                    value={branchForm.name}
                    onChange={(e) =>
                      setBranchForm({ ...branchForm, name: e.target.value })
                    }
                  />

                  <input placeholder="City"
                    value={branchForm.city}
                    onChange={(e) =>
                      setBranchForm({ ...branchForm, city: e.target.value })
                    }
                  />

                  <input placeholder="Address"
                    value={branchForm.address}
                    onChange={(e) =>
                      setBranchForm({ ...branchForm, address: e.target.value })
                    }
                  />

                  <input placeholder="Opening Time"
                    value={branchForm.openingTime}
                    onChange={(e) =>
                      setBranchForm({ ...branchForm, openingTime: e.target.value })
                    }
                  />

                  <input placeholder="Closing Time"
                    value={branchForm.closingTime}
                    onChange={(e) =>
                      setBranchForm({ ...branchForm, closingTime: e.target.value })
                    }
                  />

                  <select
                    value={branchForm.active}
                    onChange={(e) =>
                      setBranchForm({
                        ...branchForm,
                        active: e.target.value === "true"
                      })
                    }
                  >
                    <option value="true">Active</option>
                    <option value="false">Inactive</option>
                  </select>

                  <button onClick={saveBranch}>Save</button>
                  <button onClick={() => setShowBranchModal(false)}>Cancel</button>
                </div>
              </div>
            )}
          </div>
        )}
        {activeTab === "users" && (
          <div>
            <h2>Users</h2>

            <table>
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Phone</th>
                  <th>Address</th>
                </tr>
              </thead>

              <tbody>
                {users.length > 0 ? (
                  users.map((u) => (
                    <tr key={u.id}>
                      <td>{u.name}</td>
                      <td>{u.email}</td>
                      <td>{u.phone}</td>
                      <td>{u.address}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4">No users found</td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>

  );
};

export default AdminDashboard;