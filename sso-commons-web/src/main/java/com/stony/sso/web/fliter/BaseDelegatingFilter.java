package com.stony.sso.web.fliter;

import com.stony.sso.cache.ticket.TicketCacheManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stony Created Date : 2016/4/20  17:23
 */
public abstract class BaseDelegatingFilter implements InitializingBean {

    TicketCacheManager ticketCacheManager;

    protected List<String> excludes;
    protected List<String> includes;
    protected final static String DEFAULT_DELIMITER = ";";
    protected String delimiter;

    /**
     * doFilter once filter
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    protected void doOnceFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean clearContext = request.getAttribute(getFilterApplied()) == null;
        if(clearContext) {
            try {
                request.setAttribute(getFilterApplied(), Boolean.TRUE);
                doFilterInternal(request, response, chain);
            } finally {
                request.removeAttribute(getFilterApplied());
            }
        } else {
            doFilterInternal(request, response, chain);
        }
    }

    protected abstract void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException ;

    public abstract String getFilterApplied();

    /**
     * 排除不过滤URI
     * @param uri
     * @return
     */
    public boolean excludesUrl(String uri){
        return checkExp(uri, getExcludes());
    }
    public boolean includesUrl(String uri){
        return checkExp(uri, getIncludes());
    }

    /**
     *
     * @param uri
     * @param exps
     * @return
     */
    public boolean checkExp(String uri,List<String> exps){
        for(String exp : exps){
            if(exp.endsWith("/*")){
                String ex = exp.substring(0, exp.length()-1);
                if(uri.startsWith(ex)){
                    return true;
                }
            }
            if(uri.equals(exp)){
                return true;
            }
        }
        return false;
    }

    public TicketCacheManager getTicketCacheManager() {
        return ticketCacheManager;
    }

    public void setTicketCacheManager(TicketCacheManager ticketCacheManager) {
        this.ticketCacheManager = ticketCacheManager;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public List<String> getExcludes() {
        if(excludes == null){
            this.excludes = new ArrayList<String>();
        }
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        this.excludes = excludes;
    }

    public List<String> getIncludes() {
        if(includes == null){
            this.includes = new ArrayList<String>();
        }
        return includes;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.ticketCacheManager, "property ticketCacheManager is required");
        if(StringUtils.isEmpty(delimiter)){
            delimiter = DEFAULT_DELIMITER;
        }
        if(includes == null){
            this.includes = new ArrayList<String>();
            this.includes.add("/*");
        }
        if(excludes == null){
            this.excludes = new ArrayList<String>();
            this.excludes.add("/login");
        }
    }
    public void clear(){
        if(includes != null){
            includes.clear();
        }
        if(excludes != null){
            excludes.clear();
        }
    }
}
