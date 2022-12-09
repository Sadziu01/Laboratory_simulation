package pwr.sadowski;

public class Tank{
    private int food;
    private int position;
    public Tank(int food, int position){
        this.food = food;
        this.position = position;
    }

    public void setFood(int food) {
        this.food = food;
        Main.frame.foodList.get(position).setText("|_"+food+"_|");
    }
    public int getFoodAsInt() {
        return food;
    }
    public String getFood() {
        return "|_"+food+"_|";
    }

    public synchronized boolean dajJedzenie(int value){
        if(value < 5 && food <= 5 && Integer.parseInt(String.valueOf(Main.frame.staminaList.get(position).getText().charAt(2))) > 0){
            food += value;
            Main.frame.foodList.get(position).setText("|_"+food+"_|");
            return true;
        }
        else if(food <= 5 && Integer.parseInt(String.valueOf(Main.frame.staminaList.get(position).getText().charAt(2))) > 0){
            food = 10;
            Main.frame.foodList.get(position).setText("|_"+food+"_|");
            return true;
        }
        return false;
    }

    public synchronized int jedz(){
        if(food <= 0){
            food = 0;
        }
        else {
            food--;
        }

        Main.frame.foodList.get(position).setText("|_"+food+"_|");
        return food;
    }
}
