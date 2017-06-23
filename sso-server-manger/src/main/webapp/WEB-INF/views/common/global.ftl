
<#macro seoKeywords>首汽约车,出租车，网络叫车，app打车，手机打车</#macro>
<#macro seoDescription>首汽约车</#macro>
<#macro seoCopyright>首汽约车</#macro>
<#macro seoAuthor>首汽约车</#macro>



<#macro date2string dateStr>
${dateStr?if_exists?string("yyyy-MM-dd")}
</#macro>

<#macro datetime2string dateStr>
${dateStr?if_exists?string("yyyy-MM-dd HH:mm:ss")}
</#macro>

<#macro active currentUrl activeUrl>
    <#if currentUrl == activeUrl>
    active
    </#if>
</#macro>


<#macro selected currentUrl activeUrl arrow="0">
    <#if currentUrl == activeUrl>
    <span class="selected"></span>
    </#if>
    <#if arrow == "1">
    <span class="arrow open"></span>
    </#if>
</#macro>

<#macro typeSelected type>
    <#if type == "selected">
    active
    </#if>
</#macro>

<#macro treeMenus menus activeUrl>
    <#list menus as item>
    <li class="">
        <#if item.url == "">
            <a href="javascript:;">
                <i class="${item.icon!}"></i>
                <span class="title">${item.name}</span>
                <#if item.type == "selected">
                    <span class="selected"></span>
                    <span class="arrow open"></span>
                <#else >
                    <span class="arrow "></span>
                </#if>
            </a>
            <#if item.children??>
                <@treeSubMenus menus=item.children activeUrl=activeUrl/>
            </#if>
        <#else >
            <a href="${item.url}">
                <i class="${item.icon!}"></i>
                <span class="title">${item.name!}</span>
            </a>
        </#if>
    </li>
    </#list>
</#macro>
<#macro treeSubMenus menus activeUrl>
    <ul class="sub-menu">
        <#list menus as item>
            <li class="<@active currentUrl=item.url activeUrl=activeUrl />">
                <#if item.url == "">
                    <a href="${item.url}">${item.name}</a>
                <#else >
                    <a href="${item.url}"><i class="${item.icon!}"></i> ${item.name}</a>
                </#if>
            </li>
        </#list>
    </ul>
</#macro>
<#macro treeOrgan list>
    <#assign
    index = 1
    />
    <#list list as item>
        <#if item.children??>
        <li>
            <a href="#" data-role="branch" class="tree-toggle" data-toggle="branch" data-value="Bootstrap_Tree">
                ${item.name}
            </a>
            <ul class="branch in">
                <@treeOrgan list=item.children />
            </ul>
        </li>
        <#else >
        <li><a href="#" data-role="leaf"><i class="${item.logo!}"></i> ${item.name}</a></li>
        </#if>
    </#list>
</#macro>