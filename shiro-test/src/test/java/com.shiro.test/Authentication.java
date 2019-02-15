package com.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * 新的测试类，用于测试shiro认证
 * @author furao
 * @date 2019/2/15 21:13
 */
public class Authentication  {

    //设置全局变量，以供defaultSecurityManager使用。里面存储了一个账户
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        //对simpleAccountRealm中添加一个账户
        simpleAccountRealm.addAccount("zhangsan","123456");
    }

    @Test
    public void testAuthentication(){

        //1. 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //在环境中设置一个simpleAccountRealm
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //利用SecurityUtils生成一个subject 作用是进行登陆
        Subject subject = SecurityUtils.getSubject();

        //生成一个token，作用是记录登陆的用户名和密码，让subject登陆
        //如果用户名错误，则会抛出“UnknownAccountException”异常
        //如果密码错误，则会抛出“IncorrectCredentialsException”异常
        UsernamePasswordToken passwordToken = new UsernamePasswordToken("zhangsan", "123456");

        //subject利用token登陆
        subject.login(passwordToken);

        //此时已经登陆了。打印出来登陆状态
        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        //进行退出登录
        subject.logout();
        //此时再打印登陆状态，会显示false
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
    }
}
