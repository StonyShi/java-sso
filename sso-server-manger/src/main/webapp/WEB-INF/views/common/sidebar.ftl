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
        <li class="start <@global.active currentUrl=start!"/" activeUrl=active!"/" /> ">
            <a href="/index">
                <i class="icon-home"></i>
                <span class="title">Dashboard</span>
                <@global.selected currentUrl=start!"/" activeUrl=active!"/" arrow="0"/>
            </a>
        </li>
        <#if tree??>
            <@global.treeMenus menus=tree activeUrl=active!"/"/>
        </#if>
    </ul>
    <!-- END SIDEBAR MENU -->
</div>