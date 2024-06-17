package com.rgzn.zt.entity;

public class PlanInfo {
    private int plan_id;
    private String username;
    private int action_id;
    private int action_img;
    private String action_title;
    private int action_timeState;
    private int action_count;

    public PlanInfo(int plan_id, String username, int action_id, int action_img, String action_title, int action_timeState, int action_count) {
        this.plan_id = plan_id;
        this.username = username;
        this.action_id = action_id;
        this.action_img = action_img;
        this.action_title = action_title;
        this.action_timeState = action_timeState;
        this.action_count = action_count;
    }

    public int getAction_count() {
        return action_count;
    }

    public void setAction_count(int action_count) {
        this.action_count = action_count;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAction_id() {
        return action_id;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }

    public int getAction_img() {
        return action_img;
    }

    public void setAction_img(int action_img) {
        this.action_img = action_img;
    }

    public String getAction_title() {
        return action_title;
    }

    public void setAction_title(String action_title) {
        this.action_title = action_title;
    }

    public int getAction_timeState() {
        return action_timeState;
    }

    public void setAction_timeState(int action_timeState) {
        this.action_timeState = action_timeState;
    }
}
