import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * * @author Nikita Kogan.
 * Implementation of Plus.
 */
public class Plus extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param exp1 exp1
     * @param exp2 exp2
     */
    public Plus(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Constructor.
     *
     * @param st1 st1
     * @param st2 st2
     */
    public Plus(String st1, String st2) {
        this(new Var(st1), new Var(st2));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param d  d
     */
    public Plus(String st, double d) {
        this(new Var(st), new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param st st
     */
    public Plus(double d, String st) {
        this(new Num(d), new Var(st));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param ex ex
     */
    public Plus(String st, Expression ex) {
        this(new Var(st), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param st st
     */
    public Plus(Expression ex, String st) {
        this(ex, new Var(st));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param ex ex
     */
    public Plus(double d, Expression ex) {
        this(new Num(d), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param d  d
     */
    public Plus(Expression ex, double d) {
        this(ex, new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d1 d1
     * @param d2 d2
     */
    public Plus(double d1, double d2) {
        this(new Num(d1), new Num(d2));
    }

    // Evaluate the expression using the variable values provided
    // in the assignment, and return the result.  If the expression
    // contains a variable which is not in the assignment, an exception
    // is thrown.

    /**
     * evaluate.
     *
     * @param assignment assigment.
     * @return value
     * @throws Exception exception.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (super.getExp2().evaluate(assignment) + super.getExp1().evaluate(assignment));
    }

    /**
     * evaluate.
     *
     * @return value
     * @throws Exception exception.
     */
    // A convenience method. Like the `evaluate(assignment)` method above,
    // but uses an empty assignment.
    public double evaluate() throws Exception {
        return (super.getExp2().evaluate() + super.getExp1().evaluate());
    }

    /**
     * returns a list of vars.
     *
     * @return list.
     */
    // Returns a list of the variables in the expression.
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
     * @return plusstr.
     */
    // Returns a nice string representation of the expression.
    public String toString() {
        String plusStr = "(" + getExp1().toString() + " + " + getExp2().toString() + ")";
        return plusStr;
    }

    /**
     * assign.
     *
     * @param var        var.
     * @param expression expression.
     * @return ex.
     */
    // Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).
    public Expression assign(String var, Expression expression) {
        Expression ex = new Plus(getExp1().assign(var, expression), getExp2().assign(var, expression));
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
        Expression ex = new Plus(diffexp1, diffexp2);
        return ex;
    }

    /**
     * simplify.
     *
     * @return simplified expression.
     */
    public Expression simplify() {
        if (super.getExp2().simplify().toString().equals("0.0")) {
            return super.getExp1().simplify();
        }
        if (super.getExp1().simplify().toString().equals("0.0")) {
            return super.getExp2().simplify();
        }
        List<String> list = this.getVariables();
        if (list.isEmpty()) {
            try {
                return new Num(new Plus(super.getExp1().simplify().evaluate(), super.getExp2().simplify().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Plus(super.getExp1().simplify(), super.getExp2().simplify());
    }
}
