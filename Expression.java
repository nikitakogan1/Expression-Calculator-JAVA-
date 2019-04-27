import java.util.List;
import java.util.Map;

/**
 * @author Nikita Kogan.
 * Implementation of Expression Interface.
 */
public interface Expression {
    // Evaluate the expression using the variable values provided
    // in the assignment, and return the result.  If the expression
    // contains a variable which is not in the assignment, an exception
    // is thrown.

    /**
     * ecaluate.
     *
     * @param assignment assignment.
     * @return value.
     * @throws Exception Exceptaion.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    // A convenience method. Like the `evaluate(assignment)` method above,
    // but uses an empty assignment.

    /**
     * evaluate.
     *
     * @return value.
     * @throws Exception Exception
     */
    double evaluate() throws Exception;

    // Returns a list of the variables in the expression.

    /**
     * returns a list of vars.
     *
     * @return vars.
     */
    List<String> getVariables();

    // Returns a nice string representation of the expression.

    /**
     * String.
     *
     * @return string.
     */
    String toString();

    // Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).

    /**
     * assign.
     *
     * @param var        var.
     * @param expression expression.
     * @return expression.
     */
    Expression assign(String var, Expression expression);
    //differ

    /**
     * differentiate.
     *
     * @param var var.
     * @return Expression.
     */
    Expression differentiate(String var);
    //simplify

    /**
     * simplify.
     *
     * @return expression.
     */
    Expression simplify();
}