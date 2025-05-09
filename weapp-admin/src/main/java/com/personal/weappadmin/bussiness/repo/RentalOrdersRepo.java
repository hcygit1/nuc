package com.personal.weappadmin.bussiness.repo;
import com.personal.weappadmin.bussiness.model.RentalOrders;
import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.personal.weappadmin.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* <p>
    *  Mapper 接口
    * </p>
*
* @author author
* @since 2025-02-19
*/
@MyBatisRepository
public interface RentalOrdersRepo extends CrudRepo<RentalOrders,Long> {
List<Map<String,Object>> findList(@Param("userId")Long userId);
List<Map<String,Object>> findListOrders(@Param("mobile")String mobile);
List<Map<String,Object>> findListByCarId(@Param("carId")Long carId);
}
