package com.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    @Test
    public void testAuthentication(){


        //1. 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken passwordToken = new UsernamePasswordToken("Mark", "123456");

        subject.login(passwordToken);

        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        //此时判断是否拥有角色
        //如果没有角色，会报UnauthorizedException异常
        subject.checkRole("admin");

        //此时潘顿是否拥有某些权限
        //如果没有某些权限，则报UnauthorizedException
        subject.checkPermission("user:delete");


    }
}
