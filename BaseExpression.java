/**
 * Implementation for Base Expression.
 */
public class BaseExpression {
    private Expression expression;

    /**
     * constructor.
     *
     * @param exp expression.
     */
    public BaseExpression(Expression exp) {
        this.expression = exp;
    }

    /**
     * returns the expression
     *
     * @return this.expression.
     */
    public Expression getExpression() {
        return this.expression;
    }
}
