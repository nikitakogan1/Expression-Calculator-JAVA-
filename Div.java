import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * * @author Nikita Kogan.
 * Implementation of Div.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param exp1 exp1
     * @param exp2 exp2
     */
    public Div(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Constructor.
     *
     * @param st1 st1
     * @param st2 st2
     */
    public Div(String st1, String st2) {
        super(new Var(st1), new Var(st2));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param d  d
     */

    public Div(String st, double d) {
        super(new Var(st), new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param st st
     */
    public Div(double d, String st) {
        super(new Num(d), new Var(st));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param ex ex
     */
    public Div(String st, Expression ex) {
        super(new Var(st), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param st st
     */
    public Div(Expression ex, String st) {
        super(ex, new Var(st));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param ex ex
     */
    public Div(double d, Expression ex) {
        super(new Num(d), ex);
    }

    /**
     * Constructor
     *
     * @param ex ex
     * @param d  d
     */
    public Div(Expression ex, double d) {
        super(ex, new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d1 d1
     * @param d2 d2
     */
    public Div(double d1, double d2) {
        super(new Num(d1), new Num(d2));
    }

    /**
     * evaluate.
     *
     * @param assignment assigment
     * @return value
     * @throws Exception exception.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (getExp2().evaluate(assignment) == 0) {
            throw new Exception("0 divide");
        }
        return (super.getExp1().evaluate(assignment) / super.getExp2().evaluate(assignment));
    }

    /**
     * evaluate.
     *
     * @return value
     * @throws Exception exception.
     */
    public double evaluate() throws Exception {
        if (getExp2().evaluate() == 0) {
            throw new Exception("0 divide");
        }
        return (super.getExp1().evaluate() / super.getExp2().evaluate());
    }

    /**
     * returns a list of vars in expression.
     *
     * @return list of vars
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        List<String> variableList1 = super.getExp1().getVariables();
        List<String> variableList2 = super.getExp2().getVariables();
        if (variableList1 != null) {
            for (int i = 0; i < variableList1.size(); i++) {
                vars.add(variableList1.get(i));
            }
        }
        if (variableList2 != null) {
            for (int i = 0; i < variableList2.size(); i++) {
                vars.add(variableList2.get(i));
            }
        }
        return vars;
    }

    /**
     * make a string from expression.
     *
     * @return string.
     */
    public String toString() {
        String plusStr = "(" + getExp1().toString() + " / " + getExp2().toString() + ")";
        return plusStr;
    }

    /**
     * assign.
     *
     * @param var        var
     * @param expression expression.
     * @return expression with var assigned.
     */
    public Expression assign(String var, Expression expression) {
        Expression ex = new Div(getExp1().assign(var, expression), getExp2().assign(var, expression));
        return ex;
    }

    /**
     * differentiate
     *
     * @param var var
     * @return differentiate the expression.
     */
    public Expression differentiate(String var) {
        Expression diffexp1 = this.getExp1().differentiate(var);
        Expression diffexp2 = this.getExp2().differentiate(var);
        Expression ex = new Div(new Minus(new Mult(diffexp1, super.getExp2()),
                new Mult(super.getExp1(), diffexp2)), new Pow(super.getExp2(), 2));
        return ex;
    }

    /**
     * simplify.
     *
     * @return simplified expression.
     */
    public Expression simplify() {
        if (super.getExp1().simplify().toString().equals("0.0")) {
            return new Num(0.0).simplify();
        }
        if (super.getExp2().simplify().toString().equals(super.getExp1().simplify().toString())) {
            return new Num(1.0).simplify();
        }
        if (super.getExp2().simplify().toString().equals("1.0")) {
            return super.getExp1().simplify();
        }
        List<String> list = this.getVariables();
        if (list.isEmpty()) {
            try {
                return new Num(new Div(super.getExp1().simplify().evaluate(), super.getExp2().simplify().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Div(super.getExp1().simplify(), super.getExp2().simplify());
    }
}
