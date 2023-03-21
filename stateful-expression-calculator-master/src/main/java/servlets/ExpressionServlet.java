package servlets;

import util.Checker;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/calc/expression")
public class ExpressionServlet extends HttpServlet {

    private final static long serialVersionUID = 1L;

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        String expression = request.getReader().readLine();

        PrintWriter out = response.getWriter();
        try (out) {
            if (Checker.validateExpression(expression)) {
                if (session.getAttribute("expression") == null) {
                    response.setStatus(HttpServletResponse.SC_CREATED);
                    System.out.println("Session expression has been set: " + expression);
                } else {
                    response.setStatus(HttpServletResponse.SC_OK);
                    System.out.println("New expression is reset: " + expression);
                }
                session.setAttribute("expression", expression);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Illegal format of expression");
            }
        }
        out.println();
    }
}