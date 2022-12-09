package pwr.sadowski;

import static java.lang.Thread.sleep;

public class Labolatorium {
    public Labolatorium(){
    }

    public void refresh(int i, String name, int value, String direct){
        if(value < 0){
            value = 0;
        }
        if(direct == "down"){
            Main.frame.assistantsList.get(i).setText("|" + name + "__" + value + "|");
        }
        else if (direct == "up") {
            Main.frame.assistantsList.get(i).setText("|" + name + "__" + value + "|");
        }
    }

    public synchronized boolean movingDown(int i, String name, int value){
        if(i == 0){
            Main.frame.assistantsList.get(i).setText("|" + name + "__" + value + "|");
            try {
                sleep(400);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(i == 8){
            return false;
        }
        else if (Main.frame.assistantsList.get(i+1).getText().equals("|____|")) {
            Main.frame.assistantsList.get(i+1).setText("|" + name + "__" + value + "|");
            Main.frame.assistantsList.get(i).setText("|____|");
            return true;
        }
        return false;
    }

    public synchronized boolean movingUp(int i, String name, int value){
        if(i == 0){
            return false;
        }
        else if(Main.frame.assistantsList.get(i-1).getText().equals("|____|")){
            Main.frame.assistantsList.get(i-1).setText("|" + name + "__" + value + "|");
            Main.frame.assistantsList.get(i).setText("|____|");
            return true;
        }

        return false;
    }

    public synchronized boolean emptyBag(String name){
        if(Main.frame.distributor.getText().equals("|___|")){
            Main.frame.distributor.setText("| " + name + " |");
            Creature.sleepC();
            Main.frame.distributor.setText("|___|");
            return true;
        }
        return false;
    }


    public int durabilityStamina(String name, int stamina, int food){               //dodać że sobie umiera i nie bedzie jadł :C
        if(stamina == 0 && food == 0){
            Main.frame.staminaList.get(Integer.parseInt(name)).setText("|_" + stamina + "_|");
            return stamina;
        }
        if(food == 0){
            stamina --;
        }
        Main.frame.staminaList.get(Integer.parseInt(name)).setText("|_" + stamina + "_|");
        return stamina;
    }
}