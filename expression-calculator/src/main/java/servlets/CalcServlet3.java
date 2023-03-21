package servlets;

/*import com.expression.parser.*;
import com.expression.parser.util.Point;
import com.expression.parser.util.ParserResult;*/


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/calc3")
public class CalcServlet3 extends HttpServlet {

/*
    private final static String EXPRESSION = "expression";
    private final static long serialVersionUID = 1L;

    */
/**
     * Using always parametersMap.get(<name>)[0] because arguments names are never duplicating
     *//*

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> parametersMap = request.getParameterMap();

        String expressionToCalculate = parametersMap.get(EXPRESSION)[0];
        List<Point> argumentList = getArgumentList(parametersMap);
        Point[] p = argumentList.toArray(argumentList.toArray(new Point[0]));
        ParserResult result = Parser.eval(expressionToCalculate, new Point[10]);//Parser.eval(expressionToCalculate, p);
        response.getWriter().println(result.getValue());

    }

    private static List<Point> getArgumentList(Map<String, String[]> parametersMap) {
        List<Point> argumentList = new ArrayList<>();
        Map<String, String[]> paramMap = new HashMap<>(parametersMap);
        paramMap.remove(EXPRESSION);
        paramMap.forEach((argName, value) -> {
                String argValue = value[0];
                try {
                    Integer.parseInt(argValue);
                } catch (NumberFormatException exception) {
                    argValue = paramMap.get(argValue)[0];
                }

                argumentList.add(new Point(argName,argValue));
        });
        return argumentList;
    }
*/

}
