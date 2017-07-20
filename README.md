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
    * 5.1 服务登陆:
![服务登陆](https://git.oschina.net/uploads/images/2017/0720/134455_e087666f_97991.png "login.png")
	* 5.2 服务主页:
![服务主页](https://git.oschina.net/uploads/images/2017/0720/134431_480f1f89_97991.png "home.png")
	* 5.3 应用管理:
![应用管理](https://git.oschina.net/uploads/images/2017/0720/134414_c745674b_97991.png "app.png")
	* 5.4 角色管理:
![角色管理](https://git.oschina.net/uploads/images/2017/0720/134220_e4025a31_97991.png "role.png")
	* 5.5 资源管理:
![资源管理](https://git.oschina.net/uploads/images/2017/0720/134248_bbc9f4a6_97991.png "res.png")
	* 5.6 角色资源:
![角色资源](https://git.oschina.net/uploads/images/2017/0720/134333_a64fe3a2_97991.png "role-res.png")
	* 5.7 授权管理:
![授权管理](https://git.oschina.net/uploads/images/2017/0720/134359_68452e0f_97991.png "auth.png")
    * 5.8 用户管理:
![用户管理](https://git.oschina.net/uploads/images/2017/0720/134043_c5f13eb3_97991.png "user.png")

## 6. 应用截图
    * 6.1 应用登陆:
![应用登陆](https://git.oschina.net/uploads/images/2017/0720/134036_6beb5fa2_97991.png "m-login.png")
	* 6.1 应用主页:
![应用主页](https://git.oschina.net/uploads/images/2017/0720/134020_864ca0a0_97991.png "m-home.png")
	
	
