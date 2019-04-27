import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * * @author Nikita Kogan.
 * implementation of Minus.
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param exp1 exp1
     * @param exp2 exp2
     */
    public Minus(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * @param st1
     * @param st2
     */
    public Minus(String st1, String st2) {
        super(new Var(st1), new Var(st2));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param d  d
     */
    public Minus(String st, double d) {
        super(new Var(st), new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param st st
     */
    public Minus(double d, String st) {
        super(new Num(d), new Var(st));
    }

    /**
     * Consturctor.
     *
     * @param st st
     * @param ex ex
     */
    public Minus(String st, Expression ex) {
        super(new Var(st), ex);
    }

    /**
     * Consturcotr.
     *
     * @param ex ex
     * @param st st
     */
    public Minus(Expression ex, String st) {
        super(ex, new Var(st));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param ex ex
     */
    public Minus(double d, Expression ex) {
        super(new Num(d), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param d  d
     */
    public Minus(Expression ex, double d) {
        super(ex, new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d1 d1
     * @param d2 d2
     */
    public Minus(double d1, double d2) {
        super(new Num(d1), new Num(d2));
    }

    /**
     * evaluate.
     *
     * @param assignment assigment
     * @return value
     * @throws Exception Exception
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (super.getExp1().evaluate(assignment) - super.getExp2().evaluate(assignment));
    }

    /**
     * evaluate.
     *
     * @return value
     * @throws Exception exception.
     */
    public double evaluate() throws Exception {
        return (super.getExp1().evaluate() - super.getExp2().evaluate());
    }

    /**
     * returns a list of vars.
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
     * string form.
     *
     * @return plussr.
     */
    public String toString() {
        String plusStr = "(" + getExp1().toString() + " - " + getExp2().toString() + ")";
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
        Expression ex = new Minus(getExp1().assign(var, expression), getExp2().assign(var, expression));
        return ex;
    }

    /**
     * differentiate.
     *
     * @param var var.
     * @return ex.
     */
    public Expression differentiate(String var) {
        Expression diffexp1 = this.getExp1().differentiate(var);
        Expression diffexp2 = this.getExp2().differentiate(var);
        Expression ex = new Minus(diffexp1, diffexp2);
        return ex;
    }

    /**
     * simplify.
     *
     * @return simplified expression.
     */
    public Expression simplify() {
        if (super.getExp1().simplify().toString().equals("0.0")) {
            return new Neg(super.getExp2().simplify());
        }
        if (super.getExp2().simplify().toString().equals("0.0")) {
            return super.getExp1().simplify();
        }
        //if equal
        if (super.getExp2().simplify().toString().equals(super.getExp1().simplify().toString())) {
            return new Num(0).simplify();
        }
        List<String> list = super.getExp2().getVariables();
        if (list.isEmpty()) {
            try {
                return new Num(new Minus(super.getExp1().simplify().evaluate(), super.getExp2().simplify().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Minus(this.getExp1().simplify(), this.getExp2().simplify());
    }
}
