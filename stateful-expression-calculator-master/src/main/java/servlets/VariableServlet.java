package servlets;

import util.Checker;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc/*")
public class VariableServlet extends HttpServlet {
    private final static long serialVersionUID = 1L;

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String variable = uri.substring(uri.lastIndexOf("/") + 1);
        String value = request.getReader().readLine();
        PrintWriter out = response.getWriter();

        try (out) {
                if (Checker.validateVariebleValue(value)) {
                    if (session.getAttribute(variable) == null) {
                        response.setStatus(HttpServletResponse.SC_CREATED);
                        System.out.println("Session variable has been set: " + variable + " " + value);
                    } else {
                        response.setStatus(HttpServletResponse.SC_OK);
                        System.out.println("New value for variable is set: " + variable + " " + value);
                    }
                    session.setAttribute(variable, value);
                } else
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Out of the allowed interval");
        }
        out.println();
    }

    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String variable = uri.substring(uri.lastIndexOf("/") + 1);
        PrintWriter out = response.getWriter();

        try (out) {
            if (Checker.validateVariable(variable)) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                session.removeAttribute(variable);
            }
        }
    }
}