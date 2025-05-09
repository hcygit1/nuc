package com.personal.weappadmin.rbac.repo;


import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import com.personal.weappadmin.rbac.model.RbacRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@MyBatisRepository
public interface RbacRoleResourceRepo extends CrudRepo<RbacRoleResource,Long> {
    List<String> getResourceIdsByRoleId(@Param("roleId") Long roleId);
    void deletedOriginalResource(@Param("roleId") Long roleId);
    void addRoleResource(@Param("roleId") Long roleId,List<Long> ids,@Param("opaId") Long opaId);
}
