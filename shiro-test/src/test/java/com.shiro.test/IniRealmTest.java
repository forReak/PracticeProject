package com.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * IniRealm 测试类
 */
public class IniRealmTest {

    @Test
    public void testAuthentication(){
        //新建一个iniRealm，引用resources目录下面的资源
        IniRealm iniRealm = new IniRealm("classpath:user.ini");

        //1. 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 注意：这里设置一个iniRealm
        defaultSecurityManager.setRealm(iniRealm);

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
