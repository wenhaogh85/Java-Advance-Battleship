package battleship;

public class LifeSaver extends Potion {
    
    private int lifeValue;

    public LifeSaver() {
        super("Life Saver", '$');
        setLifeValue(1);
    }

    public void setLifeValue(int lifeValue) {
        this.lifeValue = lifeValue;
    }

    public int getLifeValue() {
        return lifeValue;
    }
}