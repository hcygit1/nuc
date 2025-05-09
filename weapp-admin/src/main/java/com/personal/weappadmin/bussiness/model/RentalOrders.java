package com.personal.weappadmin.bussiness.model;

import java.io.Serializable;
import java.util.Date;
/**
* <p>
    * 
    * </p>
*
* @author author
* @since 2025-02-19
*/

public class RentalOrders implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;
    private Long userId;
    private Long carId;
    private String payType;
    private String rentalStatus;
    private String totalMoney;
    private String rentalStartTime;
    private String rentalEndTime;
    private String returnlat;
    private String returnlng;
    private String payStatus;
    private String pdfUrl;
    private Boolean deleted;
    private Date createTime;
    private Date updateTime;

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getId() {
        return id;
        }


        public void setId(Long id) {
        this.id = id;
    }
        public Long getUserId() {
        return userId;
        }


        public void setUserId(Long userId) {
        this.userId = userId;
    }
        public Long getCarId() {
        return carId;
        }


        public void setCarId(Long carId) {
        this.carId = carId;
    }
        public String getPayType() {
        return payType;
        }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRentalStartTime() {
        return rentalStartTime;
    }

    public void setRentalStartTime(String rentalStartTime) {
        this.rentalStartTime = rentalStartTime;
    }

    public String getRentalEndTime() {
        return rentalEndTime;
    }

    public void setRentalEndTime(String rentalEndTime) {
        this.rentalEndTime = rentalEndTime;
    }

    public String getReturnlat() {
        return returnlat;
    }

    public void setReturnlat(String returnlat) {
        this.returnlat = returnlat;
    }
        public String getReturnlng() {
        return returnlng;
        }


        public void setReturnlng(String returnlng) {
        this.returnlng = returnlng;
    }
        public String getPayStatus() {
        return payStatus;
        }


        public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }
        public String getPdfUrl() {
        return pdfUrl;
        }


        public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
        public Boolean getDeleted() {
        return deleted;
        }


        public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
        public Date getCreateTime() {
        return createTime;
        }


        public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
        public Date getUpdateTime() {
        return updateTime;
        }


        public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    

}

