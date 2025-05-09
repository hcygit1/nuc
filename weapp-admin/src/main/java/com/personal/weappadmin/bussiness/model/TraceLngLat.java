package com.personal.weappadmin.bussiness.model;

import java.io.Serializable;
import java.util.Date;
/**
* <p>
    * 
    * </p>
*
* @author author
* @since 2025-02-27
*/

public class TraceLngLat implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;
    private String lng;
    private String lat;
    private Long orderId;
    private Long carId;
    private Date createTime;
    private Date updateTime;

        public Long getId() {
        return id;
        }


        public void setId(Long id) {
        this.id = id;
    }
        public String getLng() {
        return lng;
        }


        public void setLng(String lng) {
        this.lng = lng;
    }
        public String getLat() {
        return lat;
        }


        public void setLat(String lat) {
        this.lat = lat;
    }
        public Long getOrderId() {
        return orderId;
        }


        public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
        public Long getCarId() {
        return carId;
        }


        public void setCarId(Long carId) {
        this.carId = carId;
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

