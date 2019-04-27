import java.util.Map;
import java.util.TreeMap;

public class ExpressionsTest {
    /**
     * * @author Nikita Kogan.
     * main class running ExpressionsTest.
     *
     * @param args *array of String arguments passed to main from command prompt*
     */
    public static void main(String[] args) {
        Expression ex1 = new Mult(2, "x");
        Expression ex2 = new Sin(new Mult(4, "y"));
        Expression ex3 = new Pow("e", "x");
        Expression ex4 = new Plus(ex1, ex2);
        Expression ex = new Plus(ex4, ex3);
        System.out.println(ex);
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);  //As instructed 'e' was assigned 2.71, but even if not, it will automatically be
        assignment.put("e", 2.71);  //assigned to Math.E (the most accurate value possible with Java).
        try {
            System.out.println(ex.evaluate(assignment));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Expression exDeriv = ex.differentiate("x");
        System.out.println(exDeriv);
        try {
            System.out.println(exDeriv.evaluate(assignment));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(exDeriv.simplify());
    }
}