package org.example;

public class CoffeeMachina {
    private static final int MAX_WATER = 1000;
    private static final int MAX_MILK = 500;
    private static final int MAX_COFFE = 1000;
    private static final int CLEANING = 10;

    private int cleaning;
    private boolean isOn;
    private int waterLevel;
    private int milkLevel;
    private int coffeeLevel;

    public CoffeeMachina() {
        this.isOn = false;
        this.waterLevel = 0;
        this.milkLevel = 0;
        this.coffeeLevel = 0;
        this.cleaning = 0;
    }

    public int getMilkLevel(){
        return this.milkLevel;
    }
    public int getCoffeeLevel(){
        return this.coffeeLevel;
    }
    public int getWaterLevel(){
        return this.waterLevel;
    }

    public void addWater(int waterLevel){
        while (true){
        if ((this.waterLevel < MAX_WATER) && (waterLevel + this.waterLevel <= MAX_WATER) && waterLevel >= 0 ){
            this.waterLevel += waterLevel;
            break;
        }
        else if (this.waterLevel == MAX_WATER){
            System.out.println("Вода полная");
            break;
        }
        else System.out.println("Вы пытаетесь добавь воды больше допустимого, уменьшите количество");
        }
    }
    public void addMilk(int milkLevel){
        while (true){
            if (this.milkLevel < MAX_MILK && (milkLevel + this.milkLevel <= MAX_MILK) && milkLevel >= 0 ){
                this.milkLevel += milkLevel;
                break;
            }
            else if (this.milkLevel == MAX_MILK){
                System.out.println("Уровень молока максимальный");
                break;
            }
            else System.out.println("Вы пытаетесь добавить слишком много молока");
        }
    }
    public void addCoffee(int coffeeLevel){
        while (true){
            if (this.coffeeLevel < MAX_COFFE && (coffeeLevel + this.coffeeLevel <= MAX_COFFE) &&  coffeeLevel >= 0 ){
                this.coffeeLevel += coffeeLevel;
                break;
            }
            else if (this.coffeeLevel == MAX_COFFE){
                System.out.println("Уровень кофе максимальный");
                break;
            }
            else System.out.println("Вы пытаетесь добавить слишком много кофе");
        }
    }

    public void powerOn(){
        this.isOn = true;
        System.out.println("Кофемашина включена");
    }
    public void powerOff(){
        this.isOn = false;
    }

    public void cleanInfo(){
        if (cleaning > 0) {
            System.out.println("Требуется очистка");
        }
        else System.out.println("Очистка не требуется");
    }
    public void clean(){
        this.cleaning = 0;
        System.out.println("Очистка завершена");
    }


}
