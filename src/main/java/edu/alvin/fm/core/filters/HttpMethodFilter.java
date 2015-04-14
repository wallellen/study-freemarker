package edu.alvin.fm.core.filters;

import edu.alvin.fm.core.utils.Strings.Strings;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class HttpMethodFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String method = request.getParameter("_method");
        if (!Strings.isNullOrEmpty(method)) {
            request = new HttpMethodServletRequest((HttpServletRequest) request, method);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    static class HttpMethodServletRequest extends HttpServletRequestWrapper {

        private String method;

        public HttpMethodServletRequest(HttpServletRequest request, String method) {
            super(request);
            if (method != null) {
                this.method = method.toUpperCase();
            }
        }

        @Override
        public String getMethod() {
            if (Strings.isNullOrEmpty(method)) {
                return super.getMethod();
            }
            return method;
        }
    }
}
