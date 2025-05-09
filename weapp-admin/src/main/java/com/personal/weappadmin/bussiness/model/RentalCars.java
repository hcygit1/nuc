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

public class RentalCars implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;
    private String model;
    private String brand;
    private Integer price;
    private String availability;
    private String vehicleModel;
    private String endurance;
    private String photosUrl;
    private String glbFileUrl;
    private String lng;
    private String lat;
    private String status;
    private int width;
    private int height;
    private String address;
    private String classify;
    private Boolean deleted;
    private Date createTime;
    private Date updateTime;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Long getId() {
        return id;
        }


        public void setId(Long id) {
        this.id = id;
    }
        public String getModel() {
        return model;
        }


        public void setModel(String model) {
        this.model = model;
    }
        public String getBrand() {
        return brand;
        }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
        public String getAvailability() {
        return availability;
        }


        public void setAvailability(String availability) {
        this.availability = availability;
    }
        public String getVehicleModel() {
        return vehicleModel;
        }


        public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
        public String getEndurance() {
        return endurance;
        }


        public void setEndurance(String endurance) {
        this.endurance = endurance;
    }
        public String getPhotosUrl() {
        return photosUrl;
        }


        public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }
        public String getGlbFileUrl() {
        return glbFileUrl;
        }


        public void setGlbFileUrl(String glbFileUrl) {
        this.glbFileUrl = glbFileUrl;
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
        public String getStatus() {
        return status;
        }


        public void setStatus(String status) {
        this.status = status;
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

