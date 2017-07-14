<#--
 * Created by Stony on 2016/6/3.
-->

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>司机列表</title>
</head>
<body>
<h1><@shiro.principal property="username"/>, how are you today?</h1>
<h4>权限列表</h4>
<ul>
    <li><@shiro.hasPermission name="monitor:statistics:driver:menu">monitor:statistics:driver:menu</@shiro.hasPermission></li>
    <li><@shiro.hasPermission name="monitor:statistics:order:menu">monitor:statistics:order:menu</@shiro.hasPermission></li>
    <li><@shiro.hasPermission name="admin:resource:menu">admin:resource:menu</@shiro.hasPermission></li>
</ul>
</body>
</html>