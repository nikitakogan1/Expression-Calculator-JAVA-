import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Pow.
 */
public class Pow extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param exp1 expression 1.
     * @param exp2 expression 2.
     */
    public Pow(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Constructor.
     *
     * @param st1 st1
     * @param st2 st2
     */
    public Pow(String st1, String st2) {
        super(new Var(st1), new Var(st2));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param d  d
     */
    public Pow(String st, double d) {
        super(new Var(st), new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param st st
     */
    public Pow(double d, String st) {
        super(new Num(d), new Var(st));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param ex ex
     */
    public Pow(String st, Expression ex) {
        super(new Var(st), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param st st
     */
    public Pow(Expression ex, String st) {
        super(ex, new Var(st));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param ex ex
     */
    public Pow(double d, Expression ex) {
        super(new Num(d), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param d  d
     */
    public Pow(Expression ex, double d) {
        super(ex, new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d1 d1
     * @param d2 d2
     */
    public Pow(double d1, double d2) {
        super(new Num(d1), new Num(d2));
    }

    /**
     * evaluate. tries to calculate if there are only numbers.
     *
     * @param assignment assigment.
     * @return value.
     * @throws Exception exception.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            Double value = Math.pow(super.getExp1().evaluate(assignment), super.getExp2().evaluate(assignment));
            if (value.equals(Double.NaN)) {
                throw new Exception("Invalid Input");
            }
            return value.doubleValue();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * evaluate.
     *
     * @return value.
     * @throws Exception exception.
     */
    public double evaluate() throws Exception {
        try {
            Double value = Math.pow(super.getExp1().evaluate(), super.getExp2().evaluate());
            if (value.equals(Double.NaN)) {
                throw new Exception("Invalid Input");
            }
            return value.doubleValue();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * gets a list of vars.
     *
     * @return list of vars.
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
     * String expression.
     *
     * @return plusStr.
     */
    public String toString() {
        String plusStr = "(" + getExp1().toString() + "^" + getExp2().toString() + ")";
        return plusStr;
    }

    /**
     * assign.
     *
     * @param var        var.
     * @param expression expression.
     * @return expression.
     */
    public Expression assign(String var, Expression expression) {
        Expression ex = new Pow(getExp1().assign(var, expression), getExp2().assign(var, expression));
        return ex;
    }

    /**
     * differentiate.
     *
     * @param var var.
     * @return differentiation of Pow expression.
     */
    public Expression differentiate(String var) {
        Expression f = super.getExp1();
        Expression fD = super.getExp1().differentiate(var);
        Expression g = super.getExp2();
        Expression gD = super.getExp2().differentiate(var);
        return new Mult(new Pow(f, g), (new Plus(new Mult(fD, new Div(g, f)), new Mult(gD, new Log(new Var("e"), f)))));
    }

    /**
     * simplify.
     *
     * @return expression.
     */
    public Expression simplify() {
        //check for special cases.
        if (super.getExp2().simplify().toString().equals("0.0")) {
            return new Num(1);
        }
        if (super.getExp2().simplify().toString().equals("1.0")) {
            return super.getExp1().simplify();
        }
        if (super.getExp1().simplify().toString().equals("1.0")) {
            return super.getExp2().simplify();
        }
        List<String> list = this.getVariables();
        if (list.isEmpty()) {
            try {
                return new Num(new Pow(super.getExp1().simplify().evaluate(), super.getExp2().simplify().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Pow(super.getExp1().simplify(), super.getExp2().simplify());
    }
}
