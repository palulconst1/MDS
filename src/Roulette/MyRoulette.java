package Roulette;

public class MyRoulette {
    private Integer[] rouletteNumebers = {0,32,15,19,4,21,2,25,17,34,6,27,13,36,11,30,8,23,10,5,24,16,33,1,20,14,31,9,22,18,29,7,28,12,35,3,26};

    public RouletteNumber spin()
    {
        Integer index = (int)(Math.random() * rouletteNumebers.length);
        return new RouletteNumber(rouletteNumebers[index]);
    }

}

class RouletteNumber{
    private Integer number;
    private String color;
    private String position;

    public RouletteNumber(Integer number) {
        this.number = number;

        if (number == 0)
            this.color = "GREEN";
        else if (number % 2 == 1)
            this.color = "RED";
        else
            this.color = "BLACK";

        if(number > 16)
            this.position = "HIGH";
        else
            this.position = "LOW";
    }

    public Integer getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public String getPosition() {
        return position;
    }
}