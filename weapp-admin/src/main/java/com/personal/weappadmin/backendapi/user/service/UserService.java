package com.personal.weappadmin.backendapi.user.service;/*
 *Created by LLP on 2023年07月10
 */

import com.alibaba.fastjson.JSONObject;
import com.personal.weappadmin.constant.Constants;
import com.personal.weappadmin.core.service.CrudService;
import com.personal.weappadmin.exception.MyException;
import com.personal.weappadmin.rbac.model.RbacUserRole;
import com.personal.weappadmin.rbac.repo.RbacRoleRepo;
import com.personal.weappadmin.rbac.repo.RbacRoleResourceRepo;
import com.personal.weappadmin.rbac.repo.RbacUserRoleRepo;
import com.personal.weappadmin.backendapi.user.model.User;
import com.personal.weappadmin.backendapi.user.repo.UserRepo;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author author
 * @ClassName UserController
 * @Description TODO
 * @time 2023/7/10 20:35
 **/

@Service
public class UserService extends CrudService<UserRepo,User,Long> {

    @Autowired
    RbacRoleRepo rbacRoleRepo;
    @Autowired
    RbacRoleResourceRepo rbacRoleResourceRepo;
    @Autowired
    RbacUserRoleRepo rbacUserRoleRepo;

    public Object findAllUser(){
        return curdRepo.findAllUser();
    }


    public Object findAllUserIncludeUnEable(String mobile,String loginName,String age,String sex,String address,PageBounds pageBounds){
        return curdRepo.findAllUserIncludeUnEable(mobile,loginName,age,sex,address,pageBounds);
    }


    public void updatePassWord(JSONObject jsonObject, User user, HttpServletRequest request){
        //是否是同一用户
        if(!jsonObject.getString("mobile").equals(user.getMobile())){
            throw new MyException("当前登录用户有误");
        }
        //原始密码是否和新密码一致
        User user1=curdRepo.getByMobile(user.getMobile());
        if(!jsonObject.getString("password").equals(user1.getPassword())){
            throw new MyException("原始密码有误");
        }
        if(jsonObject.getString("newPassword").equals(user1.getPassword())){
            throw new MyException("新密码不能与原始密码一致");
        }
        if(!jsonObject.getString("newPassword").equals(jsonObject.getString("confirmNewPassword"))){
            throw new MyException("两次新密码不一致");
        }
        curdRepo.updatePassword(jsonObject.getString("newPassword"),user.getMobile(),user1.getId());
        //清除session
        request.getSession().removeAttribute(Constants.SESSION_USER);
    }


    public void  deleted(Long id,Long loginId){
        if(id.longValue()==loginId.longValue()){
            throw new MyException("不可删除本人");
        }
        curdRepo.deletedUser(id);
    }


    @Transactional
    public void  addUser(User user){
        User user1=curdRepo.getByMobile(user.getMobile());
        if(user1!=null){
            throw new MyException("该用户已注册");
        }
        user.setEnabled(true);
        curdRepo.add(user);
        //角色赋权
        setResource(user.getId(),15L);
    }

    public void setResource(Long userId,Long roleId){
        rbacUserRoleRepo.deleteByUserId(userId);
        //再添加新的角色
        RbacUserRole rbacUserRole=new RbacUserRole();
        rbacUserRole.setUserId(userId);
        rbacUserRole.setRoleId(roleId);
        rbacUserRole.setOpaId(999L);
        rbacUserRoleRepo.add(rbacUserRole);
    }
}
