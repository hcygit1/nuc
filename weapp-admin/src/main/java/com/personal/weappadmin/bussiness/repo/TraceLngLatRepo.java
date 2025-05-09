package com.personal.weappadmin.bussiness.repo;

import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import com.personal.weappadmin.bussiness.model.TraceLngLat;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2025-02-27
 */
@MyBatisRepository
public interface TraceLngLatRepo extends CrudRepo<TraceLngLat,Long> {
    List<double[]> findList(@Param("orderId")Long orderId);
}
