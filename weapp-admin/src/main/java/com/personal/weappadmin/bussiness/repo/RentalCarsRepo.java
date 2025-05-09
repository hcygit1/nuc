package com.personal.weappadmin.bussiness.repo;

import com.personal.weappadmin.bussiness.model.RentalCars;
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
public interface RentalCarsRepo extends CrudRepo<RentalCars,Long> {
List<RentalCars> findList();
List<RentalCars> getRightData(@Param("classify") String classify,@Param("minPrice") String minPrice,@Param("maxPrice") String maxPrice,
                              @Param("minEndurance") String minEndurance,@Param("maxEndurance") String maxEndurance,@Param("vehicleModel") String vehicleModel);
List<Map<String,Object>> leftArray();
List<Map<String,Object>> getvehicleModelArray();
List<Map<String,Object>> getpoints();
}
