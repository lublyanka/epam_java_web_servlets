package servlets;

import util.ExpressionParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@WebServlet("/calc/result")
public class CalcServlet extends HttpServlet {

    private final static String EXPRESSION = "expression";
    private final static long serialVersionUID = 1L;

    /**
     * Using always parametersMap.get(<name>)[0] because arguments names are never duplicating
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String expressionToCalculate = (String) session.getAttribute("expression");
        if (expressionToCalculate.isBlank())
            response.sendError(HttpServletResponse.SC_CONFLICT, "No expression for calculation");
        else {
            Map<String, String> parametersMap = getParametersMap(session);
            ExpressionParser parser = new ExpressionParser();
            setArguments(parametersMap, parser);
            try {
                int result = parser.parse(expressionToCalculate);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().print(result);
            } catch (ArithmeticException | IllegalArgumentException arithmeticException) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.getWriter().println(arithmeticException.getMessage());
            }
        }
    }

    private static Map<String, String> getParametersMap(HttpSession session) {
        Map<String, String> parametersMap = new HashMap<>();
        for (Iterator<String> it = session.getAttributeNames().asIterator(); it.hasNext(); ) {
            String attrName = it.next();
            if (!(attrName.equals("sessionId") || attrName.equals("expression"))) {
                parametersMap.put(attrName, session.getAttribute(attrName).toString());
            }
        }
        return parametersMap;
    }

    /**
     * Using argName.charAt(0) because arguments names are 1 symbol long
     */
    private static void setArguments(Map<String, String> parametersMap, ExpressionParser parser) {
        Map<String, String> paramMap = new HashMap<>(parametersMap);
        paramMap.remove(EXPRESSION);
        paramMap.forEach((argName, value) -> {
            int arg;
            try {
                arg = Integer.parseInt(value);
            } catch (NumberFormatException exception) {
                arg = Integer.parseInt(paramMap.get(value));
            }
            parser.setVariable(argName.charAt(0), arg);
        });
    }
}
