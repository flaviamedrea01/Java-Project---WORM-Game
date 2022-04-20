public class Superfood implements Bonusable {
    int bonus;
    int bonusX;
    int bonusY;

    public Superfood(int bonus, int bonusX, int bonusY){
        this.bonus = bonus;
        this.bonusX = bonusX;
        this.bonusY = bonusY;

    }

    @Override
    public boolean friend() {
        if(bonus >=20)
           return true;
           else return false;
    }

    @Override
    public boolean leaves() {
        if(bonus < 20)
        return true;
        else return false;
    }
}
