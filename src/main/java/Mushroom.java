public class Mushroom implements Killable{
    boolean deadly = false;
    int poisonLevel;
    int mushroomX;
    int mushroomY;

    public Mushroom(int poisonLevel, int mushroomX, int mushroomY){
        this.poisonLevel = poisonLevel;
        this.mushroomX = mushroomX;
        this.mushroomY = mushroomY;
    
    }

    @Override
    public boolean deadly() {
        if(poisonLevel >= 10){
            deadly = true;
        }
        return deadly;
    }

    @Override
    public boolean harmful() {
        if(poisonLevel < 10){
            deadly = false;
        }
        return deadly;
    }

}
