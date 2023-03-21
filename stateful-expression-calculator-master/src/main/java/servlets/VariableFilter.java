package servlets;

import util.Checker;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/calc/*")
public class VariableFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        String exitPoint = uri.substring(uri.lastIndexOf("/") + 1);
        if (!exitPoint.equals("result") && !exitPoint.equals("expression")) {
            if (!Checker.validateVariable(exitPoint)) {
                HttpServletResponse resp = (HttpServletResponse) response;
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal format of expression");
            }
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
