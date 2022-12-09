package pwr.sadowski;

public class Assistant extends MyThread{

    private int value;
    private String direction;
    private int position = 0;


    public int getValue() {
        return value;
    }

    public Assistant(char name, Labolatorium l, int position){
        super(name, l);
        this.position = position;
        value = 50;
    }

    private void checkEmpty(){
        if(value <= 0)
        {
            if(l.emptyBag(currentThread().getName())){
                value = 50;
            }
        }
    }

    public void run(){
        direction = "down";
        while (!end){
            while(direction.equals("down")){
                if(l.movingDown(position, currentThread().getName(), value)){
                    sleepA();
                    position++;
                    checkEmpty();
                    int valueInTank = 10 - Main.frame.foodyList.get(position).getFoodAsInt();
                    if(Main.frame.foodyList.get(position).dajJedzenie(value)){
                        value -= valueInTank;
                        l.refresh(position, currentThread().getName(), value, direction);
                        if(value < 0){
                            value = 0;
                            checkEmpty();
                        }
                    }
                }
                else {
                    direction = "up";
                }
            }

            while (direction.equals("up")){
                checkEmpty();

                if(l.movingUp(position, currentThread().getName(), value)){
                    sleepA();
                    position--;;
                    checkEmpty();
                    int valueInTank = 10 - Main.frame.foodyList.get(position).getFoodAsInt();
                    if(Main.frame.foodyList.get(position).dajJedzenie(value)){
                        value = value - valueInTank;
                        l.refresh(position, currentThread().getName(), value, direction);
                        if(value < 0){
                            value = 0;
                            checkEmpty();
                        }
                    }
                }
                else{
                    direction = "down";
                }
            }
        }
    }

    public static void sleepA() {
        try {
            Thread.sleep((int) (Math.random() * 400) + (3200 - MyFrame.sliderForAssistant.getValue()));
        } catch (InterruptedException e) {
            System.err.println("Thread stopped");
        }
    }
}

