/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 * @author huy.pham
 */
public class Tokenizer {
    private String _buffer;
    private final char COMMAND_SEPARATOR = ';';

    public Tokenizer(String buffer) {
        this._buffer = buffer;
    }

    /**
     * @return the next token
     */
    private Token next() {
        _buffer = _buffer.trim();
        if (_buffer.isEmpty())
            return null;

        if (_buffer.startsWith(Token.Type.LEFT.toString()))
            return new Token(Token.Type.LEFT);

        if (_buffer.startsWith(Token.Type.RIGHT.toString()))
            return new Token(Token.Type.RIGHT);

        // TODO: Implement "FORWARD" and "BACK" tokenization.
        // hints:
        // - Character.isDigit() may be useful in extracting the forward or back value from the buffer.
        // - Use new Token(<type>, <original token str>, <value>) to return these tokens

        if (_buffer.startsWith(Token.Type.FORWARD.toString())){
            int numIndex =8;
            String num="";
            while (Character.isDigit(_buffer.charAt(numIndex))){
                num=num+_buffer.charAt(numIndex);
                numIndex++;
            }

            Integer value = Integer.parseInt(num);
            String originTokenStr = "FORWARD(" + num + ")";
            return new Token(Token.Type.FORWARD,originTokenStr,value);
        }

        if (_buffer.startsWith(Token.Type.BACK.toString())){
            int numIndex =5;
            String num="";
            while (Character.isDigit(_buffer.charAt(numIndex))){
                num=num+_buffer.charAt(numIndex);
                numIndex++;
            }

            Integer value = Integer.parseInt(num);
            String originTokenStr = "BACK(" + num + ")";
            return new Token(Token.Type.BACK,originTokenStr,value);
        }



        // TODO: Implement "PENUP" and "PENDOWN" tokenization.
        if (_buffer.startsWith(Token.Type.PENUP.toString()))
            return new Token(Token.Type.PENUP);

        if (_buffer.startsWith(Token.Type.PENDOWN.toString()))
            return new Token(Token.Type.PENDOWN);


        return null;
    }

    /**
     * Return the next token and remove it from the buffer
     * @return the next token
     */
    public Token takeNext() {
        // TODO: Add your implementation here.
        Token res = this.next();
        int index = _buffer.indexOf(COMMAND_SEPARATOR);
        _buffer=_buffer.substring(index+1);

        return res;
    }

    /**
     * @return whether there is another token to parse in the buffer
     */
    public boolean hasNext() {
        // TODO: Add your implementation here.
        return _buffer.length()!=0;
    }

}