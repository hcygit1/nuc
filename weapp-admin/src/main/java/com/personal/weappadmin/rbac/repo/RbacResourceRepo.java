package com.personal.weappadmin.rbac.repo;


import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import com.personal.weappadmin.rbac.model.RbacResource;
import com.personal.weappadmin.rbac.model.UserMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@MyBatisRepository
public interface RbacResourceRepo extends CrudRepo<RbacResource,Long> {

    List<UserMenu> selectParentMenu(@Param("userId") Long userId);
    UserMenu getTopNode();
    List<Map<String,Object>> getChildrenByParentNode(@Param("id") Long id);
    List<RbacResource> selectChildrenMenu(@Param("id") Long id);
    List<RbacResource> selectChildrenMenuPer(@Param("userId") Long userId,@Param("id") Long id);
    void deletedById(@Param("id") Long id);
    void deletedByParentId(@Param("id") Long id);
    List<Long> findParentIds( @Param("ids") List<Long> ids);
}
