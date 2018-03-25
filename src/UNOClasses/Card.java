package UNOClasses;

public class Card {

    String _type;
    String _color;
    boolean _isWild;
    boolean _isSkip;
    boolean _isDraw2;
    boolean _isDraw3;
    boolean _isReverse;
    


    //value for wild, skip, draw 2 etc. can be W for wild, S for skip and D2/D3 for draw cards.
    public Card (String value, String color){
        this._color = color;
        this._type = type;
    }


    public String get_type() {
        return _type;
    }

    public String get_color() {
        return _color;
    }


}
