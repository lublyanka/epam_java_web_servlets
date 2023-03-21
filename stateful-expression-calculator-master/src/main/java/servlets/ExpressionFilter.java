package servlets;

import util.Checker;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/calc/exp")
public class ExpressionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *Won't work. Because getReader() may be called only once in the whole process
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String expression = req.getReader().readLine();
        if (!Checker.validateExpression(expression)) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal format of expression");
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
