import java.util.ArrayList;
import java.util.List;

/**
 * Author: Person X
 * Trails:
 * - No comments
 * - Unnecessary complication of code
 * - Difficult to understand, unconventional code
 * What cannot be changed:
 * - Class name
 * - Any method names, input and return type
 * - quoteHandler field name and type
 * Naming requirement:
 * - QuoteFactory class field must be named quoteFactory (can be in either RequestHandler or QuoteHandler)
 */
public class RequestHandler {
    //    String[] valids = { "PHILOSOPHICAL", "MOTIVATIONAL", "DISCOURAGEMENT", "HAPPY", "SAD" };
    QuoteFactory quoteFactory ;
    private QuoteHandler quoteHandler;

    private RequestHandler() {
//        quoteHandler = new QuoteHandler();
        quoteFactory= new QuoteFactory();
        quoteHandler =QuoteHandler.getInstance();

    };//------to private

    private static RequestHandler requestHandlerInstance = null;
    public static RequestHandler getInstance(){
        if (requestHandlerInstance==null){
            requestHandlerInstance=new RequestHandler();
        }
        return requestHandlerInstance;
    }

    public String[] getQuotes() {
        List<Quote> qList = quoteHandler.get();
        List<String> qStringList= new ArrayList<>();
        for (Quote q: qList){
            qStringList.add(q.getQuote());
        }

//        String[] res=  Stream.of(qStringList.toArray(new String[0])).toArray(String[]::new);
        String[] res=qStringList.toArray(new String[qStringList.size()]);
        if (res.length==0){
            return new String[0];
        }
        return res;
    }

    public String[] getQuotes(String type) {
        boolean con = false;
        Quote.QuoteType givenType=null;
        for (Quote.QuoteType qt : Quote.QuoteType.values()){
            if (qt.name().equalsIgnoreCase(type)){
                con=true;
                givenType=qt;
                break;
            }
        }

        if (con){
            List<Quote> qList = quoteHandler.get(givenType);
            if (qList.size()==0) return new String[0];
            List<String> qStringList = new ArrayList<>();
            for (Quote q: qList){
                qStringList.add(q.getQuote());
            }
//            String[] res =Stream.of(qStringList.toArray(new String[0])).toArray(String[]::new);
            String[] res=qStringList.toArray(new String[qStringList.size()]);
            return res;
        } else {
            return new String[0];
        }

//        return Arrays.asList(valids).contains(type.toUpperCase()) ? Stream.of(QuoteHandler.get(type).toArray(new String[0])).toArray(String[]::new) : new String[0];
    }

    public boolean addQuote(String quote, String type) {
        boolean con = false;
        for (Quote.QuoteType qt : Quote.QuoteType.values()){
            if (qt.name().equalsIgnoreCase(type)){
                con=true;
                break;
            }
        }

        if (!con)
            return false;

        Quote givenQ = quoteFactory.createQuote(quote,type);
        quoteHandler.add(givenQ);
        return true;
    }
}