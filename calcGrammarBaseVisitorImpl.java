import generatedGrammar.calcGrammarBaseVisitor;
import generatedGrammar.calcGrammarParser;

public class calcGrammarBaseVisitorImpl extends calcGrammarBaseVisitor<Double> {
    @Override
    public Double visitAdd(calcGrammarParser.AddContext ctx) {
        /**@param two data types matched with + on parsing
         * @returns two values added together*/
        return visit(ctx.addSub()) + visit(ctx.multDiv());
    }

    @Override
    public Double visitSubtract(calcGrammarParser.SubtractContext ctx) {
        /**param two data types matched with - on parsing
         * @returns two values subtracted together*/
        return visit(ctx.addSub()) - visit(ctx.multDiv());
    }

    @Override
    public Double visitDivide(calcGrammarParser.DivideContext ctx) {
        /**@param two data types matched with / on parsing
         * @return two values divided*/
        return visit(ctx.multDiv()) / visit(ctx.type());
    }

    @Override
    public Double visitMultiply(calcGrammarParser.MultiplyContext ctx) {
        /**@param two data types matched with * on parsing
         * @return two values multiplied*/
        return visit(ctx.multDiv()) * visit(ctx.type());
    }

    @Override
    public Double visitInteger(calcGrammarParser.IntegerContext ctx) {
        /**@param data matched to an integer data type
         * @return data parsed as an integer, wrapped as a double for simplicity*/
        return Double.parseDouble(String.valueOf(ctx.INT()));
    }

    @Override
    public Double visitDouble(calcGrammarParser.DoubleContext ctx) {
        /**@param data matched as a double data type
         * @return data parsed as a double*/
        return Double.parseDouble(ctx.DOUBLE().toString());
    }
    @Override
    public Double visitParenthesis(calcGrammarParser.ParenthesisContext ctx) {
        /**@param expression inside of parenthesis
         * @returns parsed expression*/
        return visit(ctx.addSub());
    }

    @Override
    public Double visitRaisetoPower(calcGrammarParser.RaisetoPowerContext ctx) {
        /**@param expression matched with power sign
         * @returns expression raised to a power
         */
        if(ctx.pow() != null)
            return Math.pow(visit(ctx.negative()), visit(ctx.pow()));
        return visit(ctx.negative());
    }

    @Override
    public Double visitTimesNegativeOne(calcGrammarParser.TimesNegativeOneContext ctx) {
        /**@param expression matched with unary minus
         * @returns negative expression
         */
        return -1 * visit(ctx.negative());
    }
    @Override
    public Double visitCalculate(calcGrammarParser.CalculateContext ctx) {
        /**@param expression at top of 'matching tree'
         * @returns evaluated expression
         */
        return visit(ctx.addSub());
    }

}