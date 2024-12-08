package org.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String action = request.getServletPath();

        if(action.equals("/login") || action.equals("/signup")){
            filterChain.doFilter(request,response);
            return;
        }

        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect("/login");
            return;
        }

        filterChain.doFilter(request,response);
    }
}
