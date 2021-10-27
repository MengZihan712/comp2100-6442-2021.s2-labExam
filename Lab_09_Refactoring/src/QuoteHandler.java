import java.util.ArrayList;
import java.util.List;

/**
 * Author: Person Y
 * Traits:
 * - Unhelpful comments
 * - Unsure of difference between static and non-static
 * - Commented out unfinished code
 * What cannot be changed:
 * - Class name
 * - Any method names
 * - quoteStorage field name
 * Naming requirement:
 * - QuoteFactory class field must be named quoteFactory (can be in either RequestHandler or QuoteHandler)
 */
public class QuoteHandler {

    private List<Quote> quoteStorage;

    private QuoteHandler() {
        quoteStorage = new ArrayList<>();
    }

    private static QuoteHandler quoteHandler = null;

    public static QuoteHandler getInstance() {
        if (quoteHandler == null) {
            quoteHandler = new QuoteHandler();
        }
        return quoteHandler;
    }


    public void add(Quote quote) {
        quoteStorage.add(quote);
    }

    // Get method
    public List<Quote> get() {

        return new ArrayList<>(quoteStorage); //
    }

    public List<Quote> get(Quote.QuoteType type) {
        List<Quote> filtered = new ArrayList<>();
        for (int i = 0; i < quoteStorage.size(); i++) {
            if (quoteStorage.get(i).getType().equals(type)) {
                filtered.add(quoteStorage.get(i));
            }

        }return filtered;
    }
}
