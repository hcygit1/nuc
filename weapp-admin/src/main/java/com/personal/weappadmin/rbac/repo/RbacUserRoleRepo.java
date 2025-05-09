package com.personal.weappadmin.rbac.repo;


import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import com.personal.weappadmin.rbac.model.RbacUserRole;
import org.apache.ibatis.annotations.Param;


@MyBatisRepository
public interface RbacUserRoleRepo extends CrudRepo<RbacUserRole,Long> {
    RbacUserRole getRoleByUserId(@Param("userId") Long userId);
    void deleteByUserId(@Param("userId") Long userId);
}
