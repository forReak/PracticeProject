package com.shiro.test;

import com.furao.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


/**
 * 此类是测试自定义realm类是否成功的类
 */
public class CustomRealmTest {

    @Test
    public void testAuthentication(){

        CustomRealm customRealm = new CustomRealm();
        //上面自定义realm创建好之后，需要进行设置自定义realm的加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置加密方式
        matcher.setHashAlgorithmName("md5");
        //设置加密次数
        matcher.setHashIterations(1);
        //向自定义realm中设置加密
        customRealm.setCredentialsMatcher(matcher);

        //1. 构建securityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        // 注意：这里设置一个customRealm
        defaultSecurityManager.setRealm(customRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken passwordToken = new UsernamePasswordToken("zhangsan", "123456");

        subject.login(passwordToken);

        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        //此时判断是否拥有角色
        //如果没有角色，会报UnauthorizedException异常
        subject.checkRoles("admin","leader");

        //此时判断是否拥有某些权限
        //如果没有某些权限，则报UnauthorizedException
        subject.checkPermissions("user:delete","user:add");

    }
}
