package by.epam.courierexchange.controller.filter;

import by.epam.courierexchange.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(filterName = "LocalFilter", urlPatterns = {"*.jsp"})
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String contextPath = httpServletRequest.getContextPath();
        String url = httpServletRequest.getHeader("referer");
        httpServletRequest.getSession().setAttribute(SessionAttribute.PREVIOUS_REQUEST, url);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}