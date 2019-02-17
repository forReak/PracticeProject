package com.furao.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {

    private Map<String,String> userName =  new HashMap<>();
    private Map<String,HashSet<String>> userRoles =  new HashMap<>();
    private Map<String, HashSet<String>> rolePermissions =  new HashMap<>();

    {
        this.userName.put("zhangsan","123456");
        this.userRoles.put("zhangsan",new HashSet<String>(){{add("admin");add("leader");}});
        this.rolePermissions.put("admin",new HashSet<String>(){{add("user:add");}});
        this.rolePermissions.put("leader",new HashSet<String>(){{add("user:delete");}});

        //设置当前自定义的realmName
        super.setName("CustomRealm");
    }

    /**
     * 实现用户授权方法
     * @author furao
     * @date 2019/2/17 22:17
     * @param
     * @return
     * @throws
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.从主体传来的信息中获取用户名
        String userName = (String)principalCollection.getPrimaryPrincipal();

        //2.根据用户名从数据库查询当前角色
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByRoles(roles);

        //反回SimpleAuthorizationInfo对象
        //SimpleAuthorizationInfo对象实现了AuthorizationInfo
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 实现用户认证的方法
     * @author furao
     * @date 2019/2/17 21:48
     * @param
     * @return
     * @throws
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1.从主体传来的信息中获取用户名
        String userName = (String) authenticationToken.getPrincipal();

        //2.通过用户名从数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if(password==null){
            throw new UnknownAccountException("找不到用户名");
        }

        //3.返回自定义的验证信息
        //userName：用户输入的用户名  password：从数据库查询到的用户名  super.getName：获取的构造块设置的realm名
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName,password,super.getName());
        return simpleAuthenticationInfo;
    }

    //模拟从数据库中读取数据
    private String getPasswordByUserName(String userName) {
        return this.userName.get(userName);
    }
    //模拟从数据库中读取数据
    private Set<String> getRolesByUserName(String userName) {
        return this.userRoles.get(userName);
    }
    //模拟从数据库中读取数据
    private Set<String> getPermissionsByRoles(Set<String> roles) {
        Set<String> permissions = new HashSet<>();
        roles.forEach( e -> permissions.addAll(this.rolePermissions.get(e)));
        return permissions;
    }


}
