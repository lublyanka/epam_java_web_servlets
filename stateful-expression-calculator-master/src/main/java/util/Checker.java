package util;

public class Checker {
    public static boolean validateExpression(String expression) {
        return !expression.matches("\\w{2,}.*");
    }

    public static boolean validateVariable(String variable) {
        return variable.matches("\\w");
    }

    public static boolean validateVariebleValue(String value) {
        try {
            int intValue = Integer.parseInt(value);
            if (intValue < -10000 || intValue > 10000)
                return false;
        } catch (NumberFormatException e) {
            return validateVariable(value);
        }
        return true;
    }
}
