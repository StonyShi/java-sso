package com.stony.sso.web.fliter;

import javax.servlet.*;
import java.io.IOException;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/27 </p>
 * <p>Time: 19:46 </p>
 * <p>Version: 1.0 </p>
 * <h3>心跳检测,在web.xml 配置</h3>
 * <pre class="code">
 *   &lt;filter&gt;
 *        &lt;filter-name&gt;liveFilter&lt;/filter-name&gt;
 *       &lt;filter-class&gt;LiveFilter&lt;/filter-class&gt;
 *       &lt;async-supported&gt;true&lt;/async-supported&gt;
 *   &lt;/filter&gt;
 *   &lt;filter-mapping&gt;
 *       &lt;filter-name&gt;liveFilter&lt;/filter-name&gt;
 *       &lt;url-pattern&gt;/live.do&lt;/url-pattern&gt;
 *   &lt;/filter-mapping&gt;
 * </pre>
 */
public class LiveFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/plain;charset=utf-8");
        servletResponse.getWriter().print("ok");
    }

    @Override
    public void destroy() {

    }
}
