package com.personal.weappadmin.rbac.repo;


import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import com.personal.weappadmin.rbac.model.RbacRole;

import java.util.List;


@MyBatisRepository
public interface RbacRoleRepo extends CrudRepo<RbacRole,Long> {
    List<RbacRole> selectRoles();
}
