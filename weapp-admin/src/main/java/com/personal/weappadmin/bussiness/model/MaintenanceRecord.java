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

public class MaintenanceRecord implements Serializable {

private static final long serialVersionUID=1L;

    private Long id;
    private Long carId;
    private String remark;
    private Date mainTime;
    private Date createTime;
    private Date updateTime;

    public Date getMainTime() {
        return mainTime;
    }

    public void setMainTime(Date mainTime) {
        this.mainTime = mainTime;
    }

    public Long getId() {
        return id;
        }


        public void setId(Long id) {
        this.id = id;
    }
        public Long getCarId() {
        return carId;
        }


        public void setCarId(Long carId) {
        this.carId = carId;
    }
        public String getRemark() {
        return remark;
        }


        public void setRemark(String remark) {
        this.remark = remark;
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

