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
<h1><@shiro.principal />, how are you today?</h1>
<ul>
    <li><@shiro.hasPermission name="monitor:statistics:order:*">monitor:statistics:driver:*</@shiro.hasPermission></li>
    <li><@shiro.hasPermission name="monitor:statistics:order:*">monitor:statistics:order:*</@shiro.hasPermission></li>
    <li><@shiro.hasPermission name="admin:resource:*">admin:resource:*</@shiro.hasPermission></li>
</ul>
</body>
</html>