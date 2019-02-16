package com.shiro.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    //初始化一个jdbcDataSource
    DruidDataSource druidDataSource = new DruidDataSource();

    //利用初始化块儿进行设置数据库属性
    {
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
    }

    @Test
    public void testAuthentication(){

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //1.建立一个jdbcrealm
        JdbcRealm jdbcRealm = new JdbcRealm();
        //2.将dataSource放到jdbcrealm
        //此时，没有设置查询语句也可以进行查询用户名和密码，是应为jdbcrealm默认了几个查询语句在代码中
        //可以点进去JdbcRealm看
        jdbcRealm.setDataSource(druidDataSource);
        //注意：使用jdbcRealm时，需启用权限功能
        jdbcRealm.setPermissionsLookupEnabled(true);

        //以下是用自定义sql去进行登陆查询
        String sql = "select password from my_user where username = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        String roleSql = "select role_name from my_user_roles where username = ?";
        jdbcRealm.setUserRolesQuery(roleSql);

        String permissionSql = "select permission from my_roles_permissions where role_name = ?";
        jdbcRealm.setPermissionsQuery(permissionSql);

        defaultSecurityManager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
/*
        使用shiro默认的查询语句、表进行查询用户名以及验证权限、授权
        UsernamePasswordToken passwordToken = new UsernamePasswordToken("zhangsan", "123456");

        subject.login(passwordToken);

        System.out.println("isAuthenticated:"+subject.isAuthenticated());

        subject.checkRole("admin");

        subject.checkPermission("user:add");*/

        //以下是用自定义sql去进行登陆查询
        UsernamePasswordToken passwordToken = new UsernamePasswordToken("lisi", "123321");
        subject.login(passwordToken);
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        subject.checkRole("leader");
        subject.checkPermission("user:select");
    }
}
