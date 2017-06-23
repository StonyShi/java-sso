<#--
 * @author: Stony  Date: 2016/3/30 Time: 14:19
-->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>
<h1>Welcome <@shiro.principal/> to Index.</h1>
<#if tree??>
    <@global.treeMenus menus=tree activeUrl=active/>
</#if>
</body>
</html>