package servlets;

//import org.mariuszgromada.math.mxparser.Argument;
//import org.mariuszgromada.math.mxparser.Expression;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/calcMx")
public class CalcServletMxparser extends HttpServlet {

    private final static String EXPRESSION = "expression";
    private final static long serialVersionUID = 1L;

    /**
     * Using always parametersMap.get(<name>)[0] because arguments names are never duplicating
     */
    /*protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Map<String, String[]> parametersMap = request.getParameterMap();

        String expressionToCalculate = parametersMap.get(EXPRESSION)[0];
        List<Argument> argumentList = getArgumentList(parametersMap);

        Expression e = new Expression(expressionToCalculate);
        argumentList.forEach(e::addArguments);
        double result = e.calculate();
        System.out.println(e);

        response.getWriter().println(result);

    }

    private static List<Argument> getArgumentList(Map<String, String[]> parametersMap) {
        List<Argument> argumentList = new ArrayList<>();
        Map<String, String[]> paramMap = new HashMap<>(parametersMap);
        paramMap.remove(EXPRESSION);
        paramMap.forEach((argName, value) -> {
                String argValue = value[0];
                String arg;
                try {
                    Integer.parseInt(argValue);
                    arg = argName + " = " + argValue;
                } catch (NumberFormatException exception) {
                    arg = argName + " = " + paramMap.get(argValue)[0];
                }

                argumentList.add(new Argument(arg));
        });
        return argumentList;
    }*/

}
