import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * * @author Nikita Kogan.
 * Implementation of Mult.
 */
public class Mult extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param exp1 exp1
     * @param exp2 exp2
     */
    public Mult(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Constructor.
     *
     * @param st1 st1
     * @param st2 st2
     */
    public Mult(String st1, String st2) {
        super(new Var(st1), new Var(st2));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param d  d
     */
    public Mult(String st, double d) {
        super(new Var(st), new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param st st
     */
    public Mult(double d, String st) {
        super(new Num(d), new Var(st));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param ex ex
     */
    public Mult(String st, Expression ex) {
        super(new Var(st), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param st st
     */
    public Mult(Expression ex, String st) {
        super(ex, new Var(st));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param ex ex
     */
    public Mult(double d, Expression ex) {
        super(new Num(d), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param d  d
     */
    public Mult(Expression ex, double d) {
        super(ex, new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d1
     * @param d2
     */
    public Mult(double d1, double d2) {
        super(new Num(d1), new Num(d2));
    }

    /**
     * evaluate
     *
     * @param assignment key-map.
     * @return value
     * @throws Exception exception.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (this.getExp2().evaluate(assignment) * this.getExp1().evaluate(assignment));
    }

    /**
     * evaluate.
     *
     * @return value
     * @throws Exception Exception
     */
    public double evaluate() throws Exception {
        return (this.getExp2().evaluate() * this.getExp1().evaluate());
    }

    /**
     * returns a list of vars.
     *
     * @return list of vars for expression.
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
     * string form.
     *
     * @return string form of expression.
     */
    public String toString() {
        String plusStr = "(" + getExp1().toString() + " * " + getExp2().toString() + ")";
        return plusStr;
    }

    /**
     * assign.
     *
     * @param var        var.
     * @param expression expression.
     * @return ex.
     */
    public Expression assign(String var, Expression expression) {
        Expression ex = new Mult(getExp1().assign(var, expression), getExp2().assign(var, expression));
        return ex;
    }

    /**
     * differentiate Mult expression.
     *
     * @param var var
     * @return diff
     */
    public Expression differentiate(String var) {
        Expression diffexp1 = this.getExp1().differentiate(var);
        Expression diffexp2 = this.getExp2().differentiate(var);
        Expression ex = new Plus(new Mult(diffexp1, this.getExp2()), (new Mult(diffexp2, this.getExp1())));
        return ex;
    }

    /**
     * simplify.
     *
     * @return simplifies the expression.
     */
    public Expression simplify() {
        if (super.getExp2().simplify().toString().equals("0.0") || super.getExp1().simplify().toString().equals("0.0")) {
            return new Num(0).simplify();
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
                return new Num(new Mult(super.getExp1().evaluate(), super.getExp2().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Mult(super.getExp1().simplify(), super.getExp2().simplify());
    }
}
