import java.util.ArrayList;
import java.util.List;

/**
 * * @author Nikita Kogan.
 * Implementation of Binary Expression class.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression expression1;
    private Expression expression2;

    /**
     * constructor.
     *
     * @param exp1 expression 1
     * @param exp2 expression 2
     */
    public BinaryExpression(Expression exp1, Expression exp2) {
        super(exp1);
        this.expression1 = exp1;
        this.expression2 = exp2;
    }

    /**
     * returns expression
     *
     * @return expression 1
     */
    public Expression getExp1() {
        return this.expression1;
    }

    /**
     * returns expression
     *
     * @return expression 2
     */
    public Expression getExp2() {
        return this.expression2;
    }

    /**
     * returns a list of vars in the expression.
     *
     * @return vars list of vars
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        List<String> variableList1 = this.getExp1().getVariables();
        List<String> variableList2 = this.getExp2().getVariables();
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
}
