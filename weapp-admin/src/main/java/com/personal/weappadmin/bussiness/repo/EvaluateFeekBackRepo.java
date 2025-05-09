package com.personal.weappadmin.bussiness.repo;

import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import com.personal.weappadmin.bussiness.model.EvaluateFeekBack;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface EvaluateFeekBackRepo extends CrudRepo<EvaluateFeekBack,Long> {

    public List<EvaluateFeekBack> findList();
    public List<EvaluateFeekBack> findMyList(@Param("userId") Long userId);
    public String getAvgStarsByUserId(@Param("userId") Long userId);
}
