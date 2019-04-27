import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * * @author Nikita Kogan.
 * Implementation of Var.
 */
public class Var implements Expression {
    private String var;

    /**
     * Constructor.
     *
     * @param s string.
     */
    public Var(String s) {
        this.var = s;
    }

    /**
     * evaluate.
     *
     * @param assignment assigment
     * @return value.
     * @throws Exception exception.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.var)) {
            return assignment.get(this.var).doubleValue();
        }
        throw new Exception("Error");
    }

    /**
     * evaluate.
     *
     * @return exception.
     * @throws Exception exception.
     */
    public double evaluate() throws Exception {
        throw new Exception("cant calculate variable");
    }

    /**
     * returns list of vars.
     *
     * @return list
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(this.var);
        return list;
    }

    /**
     * makes a string from var.
     *
     * @return string.
     */
    public String toString() {
        return var;
    }
    // Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).

    /**
     * assign if var is in the expression.
     *
     * @param var        var
     * @param expression expression
     * @return this or expression.
     */
    public Expression assign(String var, Expression expression) {
        if (this.var.equals(var)) {
            return expression;
        }
        return this;
    }

    /**
     * differentiate var.
     *
     * @param var var.
     * @return 1(differentiation of var).
     */
    public Expression differentiate(String var) {
        if (this.var.equals(var)) {
            return new Num(1); //check it.
        }
        return new Num(0);
    }

    /**
     * Simplify.
     *
     * @return this.
     */
    public Expression simplify() {
        List<String> list = this.getVariables();
        return this;
    }
}
