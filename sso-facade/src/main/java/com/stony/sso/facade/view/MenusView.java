package com.stony.sso.facade.view;


/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/7 </p>
 * <p>Time: 19:05 </p>
 * <p>Version: 1.0 </p>
 * 菜单自动绑定视图
 *
 */
public class MenusView {
    private String viewName;

    public String getViewName() {
        return viewName;
    }

    private MenusView() {}
    public MenusView(String viewName) {
        this.viewName = viewName;
    }
}
