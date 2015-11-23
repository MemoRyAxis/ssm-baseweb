package com.memory.base.web.security;

import com.memory.user.po.User;
import com.memory.user.service.UserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author memoryaxis@gmail.com
 * @date 2015/11/22 14:03
 */
public class LoginRealm extends AuthorizingRealm {

    public static final Logger log = LoggerFactory.getLogger(LoginRealm.class);

    @Resource
    UserService userService;

    @Override
    public String getName() {
        return "LoginRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("\t-> authentication...");

        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        User user = new User();
        user.setUsername(username);

        user = userService.getOne(user);
        if (user == null) {
            throw new UnknownAccountException();
        }

        if (user.getValid() == 2) {
            throw new LockedAccountException();
        } else if (user.getErrCount() > 5) {
            throw new ExcessiveAttemptsException();
        } else {
            AuthenticationInfo info =
                    new SimpleAuthenticationInfo(username, password, getName());
            return info;
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("\t-> authorization...");
        return null;
    }
}
