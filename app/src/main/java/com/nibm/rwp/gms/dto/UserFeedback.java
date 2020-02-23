package com.nibm.rwp.gms.dto;

public class UserFeedback {
    private String user_id;
    private String comment;

    public UserFeedback() {

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserFeedback(String user_id) {
        this.user_id = user_id;
    }

    private String status;
}
