package servlets;

import util.ExpressionParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/calc")
public class CalcServlet extends HttpServlet {

    private final static String EXPRESSION = "expression";
    private final static long serialVersionUID = 1L;

    /**
     * Using always parametersMap.get(<name>)[0] because arguments names are never duplicating
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, String[]> parametersMap = request.getParameterMap();

        String expressionToCalculate = parametersMap.get(EXPRESSION)[0];
        ExpressionParser parser = new ExpressionParser();
        setArguments(parametersMap, parser);
        int result = parser.parse(expressionToCalculate);
        //System.out.println("Result: " + result);
        response.getWriter().println(result);
    }

    /**
     * Using argName.charAt(0) because arguments names are 1 symbol long
     */
    private static void setArguments(Map<String, String[]> parametersMap, ExpressionParser parser) {
        Map<String, String[]> paramMap = new HashMap<>(parametersMap);
        paramMap.remove(EXPRESSION);
        paramMap.forEach((argName, value) -> {
            String argValue = value[0];
            int arg;
            try {
                arg = Integer.parseInt(argValue);
            } catch (NumberFormatException exception) {
                arg = Integer.parseInt(paramMap.get(argValue)[0]);
            }

            parser.setVariable(argName.charAt(0), arg);
        });
    }

}
