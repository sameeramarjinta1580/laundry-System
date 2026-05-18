package com.laundry.dto.request;


import com.laundry.entity.DeliveryAgent;
import com.laundry.entity.User;

public class AgentRequest {

    private User user;
    private DeliveryAgent agent;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DeliveryAgent getAgent() {
        return agent;
    }

    public void setAgent(DeliveryAgent agent) {
        this.agent = agent;
    }
}