import java.util.List;
import java.util.Map;

/**
 * * @author Nikita Kogan.
 * Implementation of Neg.
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param exp1 exp1
     */
    public Neg(Expression exp1) {
        super(exp1);
    }

    /**
     * Constructor.
     *
     * @param st st.
     */
    public Neg(String st) {
        super(new Var(st));
    }

    /**
     * Constructor.
     *
     * @param d d
     */
    public Neg(double d) {
        super(new Num(d));
    }

    /**
     * evaluate.
     *
     * @param assignment
     * @return
     * @throws Exception
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(super.getExpression())) {
            return (-1) * assignment.get(super.getExpression().evaluate(assignment));
        }
        throw new Exception("error");
    }

    /**
     * evaluate.
     *
     * @return value.
     * @throws Exception exception.
     */
    public double evaluate() throws Exception {
        return (-1) * super.getExpression().evaluate();
    }

    /**
     * returns list of vars.
     *
     * @return
     */
    public List<String> getVariables() {
        return super.getExpression().getVariables();
    }

    /**
     * string.
     *
     * @return string of expression.
     */
    public String toString() {
        String str = "(" + "-" + super.getExpression().toString() + ")";
        return str;
    }

    /**
     * assign.
     *
     * @param var        var.
     * @param expression expression.
     * @return ex.
     */
    public Expression assign(String var, Expression expression) {
        Expression ex = new Neg(expression.assign(var, expression));
        return ex;
    }

    /**
     * differentiate
     *
     * @param var var.
     * @return ex.
     */
    public Expression differentiate(String var) {
        Expression ex = new Mult(-1, this);
        return ex;
    }

    /**
     * simplify.
     *
     * @return simplified expression.
     */
    public Expression simplify() {
        if (super.getExpression().toString().equals("0.0")) {
            return new Num(0.0);
        }
        List<String> list = super.getExpression().getVariables();
        if (list == null) {
            try {
                return new Num(new Neg(super.getExpression().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Neg(super.getExpression().simplify());
    }
}
