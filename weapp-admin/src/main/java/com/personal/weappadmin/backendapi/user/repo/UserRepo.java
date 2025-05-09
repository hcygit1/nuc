package com.personal.weappadmin.backendapi.user.repo;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@MyBatisRepository
public interface UserRepo extends CrudRepo<User,Long> {

    User getByMobile(@Param("mobile") String mobile);
    void updatePassword(@Param("password") String password,@Param("mobile") String mobile,@Param("id") Long id);
    List<User> findAllUser();
    List<User> findAllUserIncludeUnEable(@Param("mobile") String mobile,@Param("loginName") String loginName,@Param("age") String age,@Param("sex") String sex,@Param("address") String address,PageBounds pageBounds);
    void deletedUser(@Param("id") Long id);
}
