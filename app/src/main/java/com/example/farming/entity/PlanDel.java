/*饶丝雨*/
package com.example.farming.entity;

import java.util.Date;

public class PlanDel {
    private Long id;

    private String temDate;

    private String reason;

    private Long planId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemDate() {
        return temDate;
    }

    public void setTemDate(String temDate) {
        this.temDate = temDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }
}