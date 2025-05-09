package com.personal.weappadmin.web.weappLogin.repo;/*
 *Created by LLP on 2024年01月04
 */

import com.personal.weappadmin.config.mybatisConfig.MyBatisRepository;
import com.personal.weappadmin.core.repo.CrudRepo;
import com.personal.weappadmin.web.weappLogin.model.WeappUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author author
 * @ClassName UserRepo
 * @Description TODO
 * @time 2024/1/4 14:47
 **/
@MyBatisRepository
public interface WeappUserRepo extends CrudRepo<WeappUser,Long> {
    WeappUser getByMobile(@Param("mobile")String mobile);
    WeappUser getByOpenId(@Param("openId")String openId);
    List<WeappUser> findAllUser(@Param("mobile")String mobile);

    void updateInfo(@Param("userId")Long userId,@Param("imageUrl")String imageUrl,@Param("nickName")String nickName);
}
