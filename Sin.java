import java.util.List;
import java.util.Map;

/**
 * Implementation of Sin.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param exp exp.
     */
    public Sin(Expression exp) {
        super(exp);
    }

    /**
     * Constructor.
     *
     * @param st st
     */
    public Sin(String st) {
        super(new Var(st));
    }

    /**
     * constructor.
     *
     * @param d d
     */
    public Sin(double d) {
        super(new Num(d));
    }

    /**
     * evaluate using assigment (map-key)
     *
     * @param assignment map-key
     * @return value
     * @throws Exception not happens
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(super.getExpression().evaluate(assignment)));
    }

    /**
     * evaluate value.
     *
     * @return value.
     * @throws Exception if there is no key.
     */
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(super.getExpression().evaluate()));
    }

    /**
     * returns a list of vars.
     *
     * @return list of var.
     */
    public List<String> getVariables() {
        return this.getExpression().getVariables();
    }

    /**
     * makes it a string.
     *
     * @return string of expression.
     */
    public String toString() {
        String plusStr = "sin(" + getExpression().toString() + ")";
        return plusStr;
    }

    /**
     * assign.
     *
     * @param var        var.
     * @param expression expression
     * @return ex expression
     */
    public Expression assign(String var, Expression expression) {
        Expression ex = new Sin(super.getExpression().assign(var, expression));
        return ex;
    }

    /**
     * differentiate.
     *
     * @param var vars.
     * @return ex differentiate expression.
     */
    public Expression differentiate(String var) {
        Expression ex = new Mult(new Cos(super.getExpression()), super.getExpression().differentiate(var));
        return ex;
    }

    /**
     * simplifies expression.
     *
     * @return new simplified expression
     */
    public Expression simplify() {
        List<String> list = this.getVariables();
        if (list.isEmpty()) {
            try {
                return new Num(new Sin(super.getExpression().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Sin(super.getExpression().simplify());
    }
}
