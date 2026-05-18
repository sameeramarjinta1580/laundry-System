package com.laundry.dto.response;

public class AdminDashboardResponse {

    private long totalUsers;
    private long totalServices;
    private long totalBookings;
    private long totalBranches;
    public long getTotalBranches() {
		return totalBranches;
	}

	public void setTotalBranches(long totalBranches) {
		this.totalBranches = totalBranches;
	}

	public AdminDashboardResponse() {
		super();
	}

	public AdminDashboardResponse(long totalUsers, long totalServices, long totalBookings) {
		super();
		this.totalUsers = totalUsers;
		this.totalServices = totalServices;
		this.totalBookings = totalBookings;
	}

	public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalServices() {
        return totalServices;
    }

    public void setTotalServices(long totalServices) {
        this.totalServices = totalServices;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(long totalBookings) {
        this.totalBookings = totalBookings;
    }
}