package com.cqnu.dlzf.bean;

import java.util.Date;

public class UserFriends {
    private String friendListId;

    private User user;

    private User friend;

    private String latestChartContent;

    private Date latestChartTime;

    public String getLatestChartContent() {
        return latestChartContent;
    }

    public void setLatestChartContent(String latestChartContent) {
        this.latestChartContent = latestChartContent;
    }

    public Date getLatestChartTime() {
        return latestChartTime;
    }

    public void setLatestChartTime(Date latestChartTime) {
        this.latestChartTime = latestChartTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFriendListId() {
        return friendListId;
    }

    public void setFriendListId(String friendListId) {
        this.friendListId = friendListId == null ? null : friendListId.trim();
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}