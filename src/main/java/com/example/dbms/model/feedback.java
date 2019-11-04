package com.example.dbms.model;

import java.sql.Date;

public class feedback {

    String rid;
    Date date;
    String feedbacktext;

    public feedback(){

    }

    public feedback(String rid,Date date,String feedbacktext){
        this.rid=rid;
        this.date=date;
        this.feedbacktext=feedbacktext;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the feedbacktext
     */
    public String getFeedbacktext() {
        return feedbacktext;
    }

    /**
     * @return the rid
     */
    public String getRid() {
        return rid;
    }
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param feedbacktext the feedbacktext to set
     */
    public void setFeedbacktext(String feedbacktext) {
        this.feedbacktext = feedbacktext;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(String rid) {
        this.rid = rid;
    }
    
}