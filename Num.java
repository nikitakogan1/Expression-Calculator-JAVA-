import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of num.
 * * @author Nikita Kogan.
 */
public class Num implements Expression {
    /**
     * Member.
     */
    private double num;

    /**
     * constructor.
     *
     * @param number num value.
     */
    public Num(double number) {
        this.num = number;
    }

    /**
     * evaluates the expression.
     *
     * @param assignment map-key
     * @return value
     * @throws Exception not happens.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.num;
    }

    /**
     * evaluate.
     *
     * @return this.num.
     * @throws Exception if there is no key.
     */
    public double evaluate() throws Exception {
        return this.num;
    }

    /**
     * returns empty list of vars.
     *
     * @return new list.
     */
    public List<String> getVariables() {
        return new ArrayList<String>();
    }

    /**
     * makes a string from number.
     *
     * @return string.
     */
    public String toString() {
        return Double.toString(this.num);
    }

    /**
     * assign.irrelevant for num.
     *
     * @param var        var
     * @param expression expression
     * @return this
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * differentiate according to var.
     *
     * @param var var do differentiate.
     * @return 0 diff of num
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * simplify of num.
     *
     * @return this
     */
    public Expression simplify() {
        return this;
    }
}
