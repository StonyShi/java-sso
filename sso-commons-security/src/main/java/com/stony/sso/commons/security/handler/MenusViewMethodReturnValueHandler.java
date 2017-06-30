package com.stony.sso.commons.security.handler;

import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.service.MenusManager;
import com.stony.sso.facade.util.TreeUtil;
import com.stony.sso.facade.view.MenusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/7 </p>
 * <p>Time: 18:40 </p>
 * <p>Version: 1.0 </p>
 * <p>server端绑定菜单 view</p>
 * <pre class="code">
 *  &lt;bean id="menusManager" class="MenusManagerImpl"/&gt;
 *  &lt;mvc:annotation-driven &gt;
         &lt;mvc:return-value-handlers&gt;
         &lt;bean id="menusViewNameMethodReturnValueHandler" class="com.stony.sso.commons.security.handler.MenusViewMethodReturnValueHandler"&gt;
         &lt;property name="menusManager" ref="menusManager"/&gt;
         &lt;/bean&gt;
         &lt;/mvc:return-value-handlers&gt;
     &lt;/mvc:annotation-driven&gt;
 * </pre>
 * @see com.stony.sso.facade.view.MenusView
 */
public class MenusViewMethodReturnValueHandler implements HandlerMethodReturnValueHandler {

    private static final Logger logger = LoggerFactory.getLogger(MenusViewMethodReturnValueHandler.class);

    MenusManager menusManager;


    public boolean supportsReturnType(MethodParameter returnType) {
        Class<?> paramType = returnType.getParameterType();
        return (MenusView.class.equals(paramType) || MenusView.class.isAssignableFrom(returnType.getParameterType()));
    }

    public void handleReturnValue(
            Object returnValue, MethodParameter returnType,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
            throws Exception {
        logger.debug("Enter");
        if (returnValue instanceof MenusView) {
            List<Resource> menus = menusManager.getMenus();
            mavContainer.addAttribute("menus", menus);
            mavContainer.addAttribute("tree", TreeUtil.treeMenu(menus));
            String viewName = ((MenusView) returnValue).getViewName();
            if (webRequest.getNativeRequest() instanceof HttpServletRequest) {
                String uri = ((HttpServletRequest) webRequest.getNativeRequest()).getRequestURI();
                logger.debug("uri[{}] mapper view[{}]", uri, viewName);
                mavContainer.addAttribute("active", uri);
                if (uri.equals("/index")) {
                    mavContainer.addAttribute("start", uri);
                } else {
                    mavContainer.addAttribute("start", "/");
                }
            } else {
                mavContainer.addAttribute("active", viewName);
                mavContainer.addAttribute("start", "/");
            }
            logger.debug("view = {}", viewName);
            mavContainer.setViewName(viewName);
            if (isRedirectViewName(viewName)) {
                mavContainer.setRedirectModelScenario(true);
            }
        } else {
            // should not happen
            throw new UnsupportedOperationException("Unexpected return type: " +
                    returnType.getParameterType().getName() + " in method: " + returnType.getMethod());
        }
    }

    /**
     * Whether the given view name is a redirect view reference.
     *
     * @param viewName the view name to check, never {@code null}
     * @return "true" if the given view name is recognized as a redirect view
     * reference; "false" otherwise.
     */
    protected boolean isRedirectViewName(String viewName) {
        return viewName.startsWith("redirect:");
    }

    public void setMenusManager(MenusManager menusManager) {
        this.menusManager = menusManager;
    }
}