package com.furao.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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
        //this.userName.put("zhangsan","123456");明文时可以存123456，加密后需要存md5加密加盐后的值
        this.userName.put("zhangsan","61a310016ddcf3312aa004dc714f976f");
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

        //在返回AuthenticationInfo之前，如果加密了而且加盐了，那么需要在这里面设置盐值
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("MARK"));
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

    /**
     * 通过md5 及加盐计算出zhangsan密码md5之后的数值，放入数据库
     * @param args
     */
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","MARK");
        System.out.println(md5Hash.toString());
    }
}
