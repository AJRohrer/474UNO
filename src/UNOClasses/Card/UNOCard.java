package UNOClasses.Card;

public class UNOCard {

    CardType _type;
    UNOColor _color;
    boolean _isWild;
    boolean _isWildDraw4;
    boolean _isSkip;
    boolean _isDraw2;
    boolean _isReverse;


    /*UNOCard Values
    Possible colors: Red, Green, Yellow, Blue
    1Y = Yellow number 1 card.


    */
    public UNOCard(CardType value, UNOColor color){
        this._color = color;
        this._type = value;
    }


    public CardType get_type() {
        return _type;
    }

    public UNOColor get_color() {
        return _color;
    }

    public boolean is_isWild() {
        return this._type == CardType.WILD;
    }

    public boolean is_isWildDraw4() {
        return this._type == CardType.WILDDRAW4;
    }

    public boolean is_isSkip() {
        return this._type == CardType.SKIP;
    }

    public boolean is_isDraw2() {
        return this._type == CardType.DRAWTWO;
    }

    public boolean is_isReverse() {
        return this._type == CardType.REVERSE;
    }

    public String toString(){
        return this._color.toString() + " " + this._type.toString();
    }
}
