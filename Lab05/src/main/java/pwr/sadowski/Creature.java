package pwr.sadowski;

public class Creature extends MyThread{

    private int food;
    protected int stamina;
    private boolean isAlive;

    Creature(char name, Labolatorium l){
        super(name, l);
    }


    public void run(){
        isAlive = true;
        stamina = 5;
        while (isAlive){
            sleepC();
            Main.frame.foodyList.get(Integer.parseInt(getName())).setFood(10);
            while (!end){
                food = Main.frame.foodyList.get(Integer.parseInt(getName())).jedz();
                stamina = (l.durabilityStamina(currentThread().getName(), stamina, food));
                sleepC();

                if(food == 0 && stamina == 0){
                    isAlive = false;
                    stop();
                }
            }
        }
    }

    public static void sleepC() {
        try {
            Thread.sleep((int) (Math.random() * 300) + (3200 - MyFrame.sliderForCreature.getValue()));
        } catch (InterruptedException e) {
            System.err.println("Thread stopped");
        }
    }
}

