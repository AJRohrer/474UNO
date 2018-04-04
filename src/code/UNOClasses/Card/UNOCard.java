package code.UNOClasses.Card;

public class UNOCard implements Comparable<UNOCard>{

    CardType _type;
    UNOColor _color;

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

    public boolean isWild() {
        return this._type == CardType.WILD;
    }

    public boolean isWildDraw4() {
        return this._type == CardType.WILDDRAW4;
    }

    public boolean isSkip() {
        return this._type == CardType.SKIP;
    }

    public boolean isDraw2() {
        return this._type == CardType.DRAWTWO;
    }

    public boolean isReverse() {
        return this._type == CardType.REVERSE;
    }

    @Override
    public String toString(){
        return this._color.toString() + " " + this._type.toString();
    }

    public boolean isNumberCard() {
        switch (_type){
            case WILD:
            case WILDDRAW4:
            case REVERSE:
            case SKIP:
            case DRAWTWO:
                return false;
            default:
                return true;

        }
    }

    @Override
    public int compareTo(UNOCard unoCard2)
    {
        if(this.get_color().ordinal() > unoCard2.get_color().ordinal())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    public void set_color(UNOColor color){
        this._color = color;
    }
}
