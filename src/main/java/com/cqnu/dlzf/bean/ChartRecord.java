package com.cqnu.dlzf.bean;

import java.util.Date;

public class ChartRecord {

    private String recordId;
    private String senderId;
    private String receiverId;
    private String content;
    private Date time;

    public String getRecordId() {
        return recordId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chart_record.record_id
     *
     * @param recordId the value for t_chart_record.record_id
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chart_record.recevier_id
     *
     * @return the value of t_chart_record.recevier_id
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public String getReceiverId() {
        return receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chart_record.recevier_id
     *
     * @param receiverId the value for t_chart_record.recevier_id
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public void setreceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chart_record.sender_id
     *
     * @return the value of t_chart_record.sender_id
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chart_record.sender_id
     *
     * @param senderId the value for t_chart_record.sender_id
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chart_record.content
     *
     * @return the value of t_chart_record.content
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chart_record.content
     *
     * @param content the value for t_chart_record.content
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chart_record.time
     *
     * @return the value of t_chart_record.time
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chart_record.time
     *
     * @param time the value for t_chart_record.time
     *
     * @mbg.generated Tue Dec 21 13:25:28 CST 2021
     */
    public void setTime(Date time) {
        this.time = time;
    }
}