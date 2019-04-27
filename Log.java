import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * * @author Nikita Kogan.
 * Implementation of Log.
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * Constructor.
     *
     * @param exp1 exp1
     * @param exp2 exp2
     */
    public Log(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Constructor.
     *
     * @param st1 st1
     * @param st2 st2
     */
    public Log(String st1, String st2) {
        super(new Var(st1), new Var(st2));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param d  d
     */
    public Log(String st, double d) {
        super(new Var(st), new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param st st
     */
    public Log(double d, String st) {
        super(new Num(d), new Var(st));
    }

    /**
     * Constructor.
     *
     * @param st st
     * @param ex ex
     */
    public Log(String st, Expression ex) {
        super(new Var(st), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param st st
     */
    public Log(Expression ex, String st) {
        super(ex, new Var(st));
    }

    /**
     * Constructor.
     *
     * @param d  d
     * @param ex ex
     */
    public Log(double d, Expression ex) {
        super(new Num(d), ex);
    }

    /**
     * Constructor.
     *
     * @param ex ex
     * @param d  d
     */
    public Log(Expression ex, double d) {
        super(ex, new Num(d));
    }

    /**
     * Constructor.
     *
     * @param d1 d1
     * @param d2 d2
     */
    public Log(double d1, double d2) {
        super(new Num(d1), new Num(d2));
    }

    /**
     * evaluate.
     *
     * @param assignment assigment
     * @return value
     * @throws Exception Exception.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (super.getExp1().evaluate(assignment) < 0 || super.getExp2().evaluate(assignment) < 0) {
            throw new Exception("negative log");
        }
        return (Math.log(super.getExp2().evaluate(assignment)) / Math.log(super.getExp1().evaluate(assignment)));
    }

    /**
     * evaluate.
     *
     * @return value
     * @throws Exception Exception
     */
    public double evaluate() throws Exception {
        if (super.getExp1().evaluate() == 1.0) {
            throw new Exception("1 base");
        }
        if (super.getExp1().evaluate() < 0 || super.getExp2().evaluate() < 0) {
            throw new Exception("negative log");
        }
        return (Math.log(super.getExp2().evaluate()) / (Math.log(super.getExp1().evaluate())));
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
     * makes a string from expression.
     *
     * @return string form.
     */
    public String toString() {
        String plusStr = "log(" + super.getExp1().toString() + ", " + super.getExp2().toString() + ")";
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
        Expression ex = new Log(getExp1().assign(var, expression), getExp2().assign(var, expression));
        return ex;
    }

    /**
     * differentiate.
     *
     * @param var var.
     * @return differentiate the expression.
     */
    public Expression differentiate(String var) {
        Expression exp1 = super.getExp1();
        Expression exp2 = super.getExp2();
        Expression exp2Differ = super.getExp2().differentiate(var);
        return new Div(exp2Differ, new Mult(exp2, new Log(new Var("e"), exp1)));
    }

    /**
     * simplify.
     *
     * @return simplifies the expression.
     */
    public Expression simplify() {
        if (super.getExp2().simplify().toString().equals(super.getExp1().simplify().toString())) {
            return new Num(1).simplify();
        }
        List<String> list = this.getVariables();
        if (list.isEmpty()) {
            try {
                return new Num(new Log(super.getExp1().evaluate(), super.getExp2().evaluate()).evaluate());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Log(super.getExp1().simplify(), super.getExp2().simplify());
    }
}