/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 *
 * @author huy.pham
 */
public class Tokeniser {
    private String _buffer;

    public Tokeniser(String buffer) {
        this._buffer = buffer;
    }

    /**
     * Return the next token without changing the buffer
     * @return the next token OR null when there is no more token or the next token is invalid.
     */
    public Token next() {
        if (_buffer.isEmpty())
            return null;

        // TODO: Complete this method
        // START YOUR CODE
        // 把这个string的第一个char判断是什么token，返回其类型和substring
        Token currentToken=null;

        char first = _buffer.charAt(0);
        if (first=='('){
            currentToken = new Token(Token.Type.LEFT_BRACKET,_buffer.substring(0,1));
        }else if (first==')'){
            currentToken = new Token(Token.Type.RIGHT_BRACKET,_buffer.substring(0,1));
        }else return null;
        // END YOUR CODE

        return currentToken;
    }

    /**
     * @return the 2nd next token
     * 拿到第二个token
     */
    public Token nextNext() {
        Tokeniser t = new Tokeniser(_buffer);
        t.takeNext();
        return t.takeNext();
    }

    /**
     * Return the next token and remove it from the buffer
     * 从buffer中把已经拿走的token删掉
     * @return the next token OR null when there is no more token or the next token is invalid.
     */
    public Token takeNext() {
        Token nextToken = next();
        if (nextToken == null)
            return null;

        if (nextToken.originalTokenStr.length() < _buffer.length()) {
            _buffer = _buffer.substring(nextToken.originalTokenStr.length());
        } else {
            _buffer = "";
        }

        return nextToken;
    }

    /**
     * @return whether there is another token to parse in the buffer
     */
    public boolean hasNext() {
        return next() != null;
    }

    /**
     * @return True only if the next token is valid
     */
    public boolean isNextValid() {
        return next() != null || _buffer.isEmpty();
    }
}