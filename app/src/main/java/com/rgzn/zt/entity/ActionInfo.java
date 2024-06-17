package com.rgzn.zt.entity;

import java.io.Serializable;

public class ActionInfo implements Serializable {
    private int Action_id;
    private int Action_img;
    private String Action_title;
    private String Action_details;
    private int timeState;


    public ActionInfo(int action_id, int action_img, String action_title, String action_details, int timeState) {
        Action_id = action_id;
        Action_img = action_img;
        Action_title = action_title;
        Action_details = action_details;
        this.timeState = timeState;
    }

    public int getAction_id() {
        return Action_id;
    }

    public void setAction_id(int action_id) {
        Action_id = action_id;
    }

    public int getAction_img() {
        return Action_img;
    }

    public void setAction_img(int action_img) {
        Action_img = action_img;
    }

    public String getAction_title() {
        return Action_title;
    }

    public void setAction_title(String action_title) {
        Action_title = action_title;
    }

    public String getAction_details() {
        return Action_details;
    }

    public void setAction_details(String action_details) {
        Action_details = action_details;
    }

    public int getTimeState() {
        return timeState;
    }

    public void setTimeState(int timeState) {
        this.timeState = timeState;
    }
}
