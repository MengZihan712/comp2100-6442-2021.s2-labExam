import java.util.Scanner;
//Ke Ning
/**
 * Note: You will need to have completed task 1 to complete this task.
 * <p>
 * Welcome to task 2. In this task your job is to implement a simple parser.
 * It should be able to parser the following grammar rule:
 * <exp>    ::=  <term> | <term> + <exp> | <term> - <exp>
 * <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
 * <factor> ::=  <unsigned integer> | ( <exp> )
 * <p>
 * Here are some rules you must abide by for this task:
 * 1. You may NOT modify any other classes in this task 2 package.
 * 2. You may create additional fields or methods to finish you implementation within this class.
 * <p>
 * Parsing, within the context of this lab, is the process of taking a bunch of tokens and
 * evaluating them. You will not need to 'evaluate' them within this class, instead, just
 * return an expression which can be evaluated.
 */
public class Parser {
    /**
     * The following exception should be thrown if the parse is faced with series of tokens that do not
     * correlate with any possible production rule.
     */
    public static class IllegalProductionException extends IllegalArgumentException {
        public IllegalProductionException(String errorMessage) {
            super(errorMessage);
        }
    }

    // The tokenizer (class field) this parser will use.
    Tokenizer tokenizer;
    private int RBNum = 0;

    /**
     * Parser class constructor
     * Simply sets the tokenizer field.
     * **** please do not modify this part ****
     */
    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * To help you both test and understand what this parser is doing, we have included a main method
     * which you can run. Once running, provide a mathematical string to the terminal and you will
     * receive back the result of your parsing.
     */
    public static void main(String[] args) {
        // Create a scanner to get the user's input.
        Scanner scanner = new Scanner(System.in);

        /*
         Continue to get the user's input until they exit.
         To exit press: Control + D or providing the string 'q'
         Example input you can try: ((1 + 2) * 5)/2
         Note that evaluations will round down to negative infinity (because they are integers).
         */
        System.out.println("Provide a mathematical string to be parsed:");
        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            // Check if 'quit' is provided.
            if (input.equals("q"))
                break;

            // Create an instance of the tokenizer.
            Tokenizer tokenizer = new Tokenizer(input);

            // Print out the expression from the parser.
            Parser parser = new Parser(tokenizer);
            Exp expression = parser.parseExp();
            System.out.println("Parsing: " + expression.show());
            System.out.println("Evaluation: " + expression.evaluate());

        }

    }

    //(((10 - 2) * (10 / 2)) + 1)

    /**
     * Adheres to the grammar rule:
     * <exp>    ::= <term> | <term> + <exp> | <term> - <exp>
     *
     * @return type: Exp.
     */
    public Exp parseExp() {
        /*
         TODO: Implement parse function for <exp>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint 1: you know that the first item will always be a term (according to the grammar).
         Hint 2: the possible grammar return '<term> + <exp>' correlates with the class (AddExp(term, exp)).
         */
        // ########## YOUR CODE STARTS HERE ##########
        Exp term = this.parseTerm();


        if (this.tokenizer.hasNext()){
            if (this.tokenizer.current().getType() == Token.Type.ADD){
                this.tokenizer.next();
                Exp exp = this.parseTerm();

                if (this.tokenizer.current()!=null && this.tokenizer.getBuffer().length()==0 &&this.tokenizer.current().getType() == Token.Type.RBRA){
                    throw new IllegalProductionException("lack of (");
                }
                return new AddExp(term, exp);
            } else if (this.tokenizer.current().getType() == Token.Type.SUB){
                this.tokenizer.next();
                Exp exp = this.parseTerm();
                if (this.tokenizer.current()!=null && this.tokenizer.getBuffer().length()==0&&this.tokenizer.current().getType() == Token.Type.RBRA){
                    throw new IllegalProductionException("lack of (");
                }
                return new SubExp(term, exp);
            } else if (this.tokenizer.current().getType() == Token.Type.RBRA){

                RBNum--;
                if (RBNum < 0) {
                    throw new IllegalProductionException("lack of (");
                }

            } else {

                throw new IllegalProductionException("Tokens provided does not conform to the grammar in parsing exp!");
            }
        }
        return term;

        /*
        if (this.tokenizer.hasNext() && this.tokenizer.current().getType() == Token.Type.ADD) {
            this.tokenizer.next();
            Exp exp = this.parseTerm();
            return new AddExp(term, exp);
        } else if (this.tokenizer.hasNext() && this.tokenizer.current().getType() == Token.Type.SUB) {
            this.tokenizer.next();
            Exp exp = this.parseTerm();
            return new SubExp(term, exp);
        } else {
            return term;
        }
         */

        // ########## YOUR CODE ENDS HERE ##########

    }

    /**
     * Adheres to the grammar rule:
     * <term>   ::=  <factor> | <factor> * <term> | <factor> / <term>
     *
     * @return type: Exp.
     */
    public Exp parseTerm() {
        /*
         TODO: Implement parse function for <term>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint: you know that the first item will always be a factor (according to the grammar).
         */
        // ########## YOUR CODE STARTS HERE ##########
        Exp factor = this.parseFactor();

        if (this.tokenizer.hasNext() && this.tokenizer.current().getType() == Token.Type.MUL) {
            this.tokenizer.next();
            Exp term = this.parseTerm();
            return new MultExp(factor, term);
        } else if (this.tokenizer.hasNext() && this.tokenizer.current().getType() == Token.Type.DIV) {
            this.tokenizer.next();
            Exp term = this.parseTerm();
            return new DivExp(factor, term);
        } else {
            return factor;
        }

        // ########## YOUR CODE ENDS HERE ##########
    }

    /**
     * Adheres to the grammar rule:
     * <factor> ::= <unsigned integer> | ( <exp> )
     *
     * @return type: Exp.
     */
    public Exp parseFactor() {
        /*
         TODO: Implement parse function for <factor>.
         TODO: Throw an IllegalProductionException if provided with tokens not conforming to the grammar.
         Hint: you can use Integer.parseInt() to convert a string into an integer.
         Fun fact: Integer.parseInt() is using a parser!
         */
        // ########## YOUR CODE STARTS HERE ##########

        if (this.tokenizer.hasNext()){
            if (this.tokenizer.current().getType() == Token.Type.INT) {
                Exp inte = new IntExp(Integer.parseInt(this.tokenizer.current().getToken()));
                this.tokenizer.next();
                return inte;

            } else if (this.tokenizer.current().getType() == Token.Type.LBRA) {
                RBNum++;
                this.tokenizer.next();
                Exp exp = this.parseExp();

                if (this.tokenizer.current() == null || this.tokenizer.current().getType() != Token.Type.RBRA) {
                    throw new IllegalProductionException("no ')'");
                }
                this.tokenizer.next();

                return exp;

            }
        }


        throw new IllegalProductionException("Tokens provided does not conform to the grammar in parsing factor!");


        //          (((10 - 2) * (10 / 2)) + 1)


        // ########## YOUR CODE ENDS HERE ##########
    }
}
