<#--
 * Created by Stony on 2016/6/3.
-->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单列表</title>
</head>
<body>
<h1><@shiro.principal property="username"/>, how are you today?</h1>
<h4>角色列表</h4>
<ul>
    <li><@shiro.hasRole name="moniter-admin">moniter-admin</@shiro.hasRole></li>
    <li><@shiro.hasRole name="admin">admin</@shiro.hasRole></li>
</ul>
</body>
</html>