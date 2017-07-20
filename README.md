## 1. 授权系统采用shiro,单点登陆采用oauth2.0
## 2. 授权模式 app 下 user 的 role 拥有的 资源（菜单、按钮等）
## 3. 模块介绍：
    * 3.1 sso-commons-security 公共安全模块抽象
    * 3.2 sso-commons-client 客户端安全模块抽象
    * 3.3 sso-commons-server 服务端端安全模块抽象
    * 3.4 sso-facade 接口和接口对象
    * 3.5 sso-service 接口实现，数据操作层
    * 3.6 sso-statics 静态资源
    * 3.7 sso-server-manger SSO服务端
    * 3.8 sso-admin-monitor，sso-admin-boss  SSO应用
    * 3.9 sso-config 公共配置文件

## 4. 启动
    * 4.1 sso-statics  8080端口
    * 4.2 sso-server-manger 8099端口
    * 4.3 sso-admin-monitor  8090端口

## 5. sso服务截图

    ![登陆](https://git.oschina.net/stoney/java-sso/blob/master/dist/login.png)
    ![主页](https://git.oschina.net/stoney/java-sso/blob/master/dist/home.png)
    ![应用管理](https://git.oschina.net/stoney/java-sso/blob/master/dist/app.png)
    ![资源管理](https://git.oschina.net/stoney/java-sso/blob/master/dist/res.png)
    ![角色管理](https://git.oschina.net/stoney/java-sso/blob/master/dist/role.png)
    ![角色资源](https://git.oschina.net/stoney/java-sso/blob/master/dist/role-res.png)
    ![授权管理](https://git.oschina.net/stoney/java-sso/blob/master/dist/auth.png)
    ![用户管理](https://git.oschina.net/stoney/java-sso/blob/master/dist/user.png)

## 6. 应用截图

    ![应用登陆](https://git.oschina.net/stoney/java-sso/blob/master/dist/m-login.png)
    ![应用首页](https://git.oschina.net/stoney/java-sso/blob/master/dist/m-home.png)
