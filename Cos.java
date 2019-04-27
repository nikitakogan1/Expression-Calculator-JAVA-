import java.util.List;
import java.util.Map;

/**
 * * @author Nikita Kogan.
 * Implementation of Cos.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * constructor (expression is sent with "super")
     *
     * @param exp expression.
     */
    public Cos(Expression exp) {
        super(exp);
    }

    /**
     * constructor.
     *
     * @param st st
     */
    public Cos(String st) {
        super(new Var(st));
    }

    /**
     * constructor.
     *
     * @param d d
     */
    public Cos(double d) {
        super(new Num(d));
    }

    /**
     * evaluate.
     *
     * @param assignment map-key
     * @return double value
     * @throws Exception not happens
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(super.getExpression().evaluate(assignment)));
    }

    /**
     * evaluate.
     *
     * @return double value
     * @throws Exception not happens
     */
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(super.getExpression().evaluate()));
    }

    /**
     * returns var list.
     *
     * @return vars
     */
    public List<String> getVariables() {
        return super.getExpression().getVariables();
    }

    /**
     * makes it a string.
     *
     * @return string of expression.
     */
    public String toString() {
        String plusStr = "cos(" + super.getExpression().toString() + ")";
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
        Expression ex = new Cos(expression.assign(var, expression));
        return ex;
    }

    /**
     * differentiate.
     *
     * @param var vars.
     * @return ex differentiate expression.
     */
    public Expression differentiate(String var) {
        Expression ex = new Neg(new Mult(new Sin(super.getExpression()), super.getExpression().differentiate(var)));
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
                return new Num(new Cos(super.getExpression().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Cos(super.getExpression().simplify());
    }
}
