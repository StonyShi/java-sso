<#--
 * Created by Stony on 2016/4/29.
-->
<div class="page-sidebar nav-collapse collapse">
    <!-- BEGIN SIDEBAR MENU -->
    <ul class="page-sidebar-menu">
        <li>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            <div class="sidebar-toggler hidden-phone"></div>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
        </li>
        <li>
            <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
            <form class="sidebar-search">
                <div class="input-box">
                    <a href="javascript:;" class="remove"></a>
                    <input type="text" placeholder="Search..." />
                    <input type="button" class="submit" value=" " />
                </div>
            </form>
            <!-- END RESPONSIVE QUICK SEARCH FORM -->
        </li>
        <li class="start <@global.active currentUrl=start activeUrl=active /> ">
            <a href="/index">
                <i class="icon-home"></i>
                <span class="title">Dashboard</span>
                <@global.selected currentUrl=start activeUrl=active arrow="0"/>
            </a>
        </li>
        <#if menus?exists>
            <#assign
            index = 0
            subIndex = 0
            />
            <#if (menus?size>0) >
            <li class="">
            </#if>
            <#list menus as item>
                <#if item.url == "">
                    <#if 0 < subIndex >
                    </ul>
                    </#if>
                    <#if  0 < index>
                    </li>
                        <#assign index = 1>
                    </#if>
                    <#assign subIndex = 0>

                    <#if index = 1>
                    <li class="">
                    </#if>
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
                    <#assign index = 2>
                <#else >
                    <#if subIndex = 0>
                    <ul class="sub-menu">
                        <#assign subIndex = 2 >
                    </#if>
                    <li class="<@global.active currentUrl=item.url activeUrl=active />">
                        <#if item.url == "">
                        <a href="${item.url}">${item.name}</a>
                        <#else >
                        <a href="${item.url}"><i class="${item.icon!}"></i> ${item.name}</a>
                        </#if>
                    </li>
                </#if>
            </#list>
            <#if 0 < subIndex >
            </ul>
            </#if>
            <#if (menus?size>0) >
                </li>
            </#if>
        </#if>
    </ul>
    <!-- END SIDEBAR MENU -->
</div>