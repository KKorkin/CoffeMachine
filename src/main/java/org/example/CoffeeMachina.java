package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachina {
    private static final int MAX_WATER = 1000;
    private static final int MAX_MILK = 1000;
    private static final int MAX_COFFEE = 1000;
    private static final int CLEANING = 20;

    private int cleaning;
    private boolean isOn;
    private int waterLevel;
    private int milkLevel;
    private int coffeeLevel;
    private final List<String> log = new ArrayList<>(); // для логов
    private final Map<String, List<Profile>> profiles = new HashMap<>(); // Профили с напитками

    public CoffeeMachina() {
        this.isOn = false;
        this.waterLevel = 500;
        this.milkLevel = 500;
        this.coffeeLevel = 500;
        this.cleaning = 0;
    }

    public int getMilkLevel() {
        return this.milkLevel;
    }

    public int getCoffeeLevel() {
        return this.coffeeLevel;
    }

    public int getWaterLevel() {
        return this.waterLevel;
    }

    public void addWater(int waterLevel) {
        if (waterLevel >= 0) {
            if (this.waterLevel + waterLevel <= MAX_WATER) {
                this.waterLevel += waterLevel;
                System.out.println("Вода добавлена.");
            } else {
                System.out.println("Вы пытаетесь добавить больше воды, чем допустимо. Уровень воды установлен на максимум.");
                this.waterLevel = MAX_WATER;
            }
        } else {
            System.out.println("Количество воды должно быть неотрицательным.");
        }
    }

    public void addMilk(int milkLevel) {
        if (milkLevel >= 0) {
            if (this.milkLevel + milkLevel <= MAX_MILK) {
                this.milkLevel += milkLevel;
                System.out.println("Молоко добавлено.");
            } else {
                System.out.println("Вы пытаетесь добавить больше молока, чем допустимо. Уровень молока установлен на максимум.");
                this.milkLevel = MAX_MILK;
            }
        } else {
            System.out.println("Количество молока должно быть неотрицательным.");
        }
    }

    public void addCoffee(int coffeeLevel) {
        if (coffeeLevel >= 0) {
            if (this.coffeeLevel + coffeeLevel <= MAX_COFFEE) {
                this.coffeeLevel += coffeeLevel;
                System.out.println("Кофе добавлено.");
            } else {
                System.out.println("Вы пытаетесь добавить больше кофе, чем допустимо. Уровень кофе установлен на максимум.");
                this.coffeeLevel = MAX_COFFEE;
            }
        } else {
            System.out.println("Количество кофе должно быть неотрицательным.");
        }
    }

    public void powerOn() {
        this.isOn = true;
        System.out.println("Кофемашина включена.");
    }

    public void powerOff() {
        this.isOn = false;
        System.out.println("Кофемашина выключена.");
    }

    public void cleanInfo() {
        if (cleaning > 0) {
            System.out.println("Требуется очистка.");
        } else {
            System.out.println("Очистка не требуется.");
        }
    }

    public void clean() {
        if (cleaning > 0) {
            this.cleaning = 0;
            System.out.println("Очистка завершена.");
        } else {
            System.out.println("Очистка не требуется.");
        }
    }

    public void makeThreeCups(Recipe recipe) {
        if (!this.isOn) {
            System.out.println("Кофемашина выключена.");
            return;
        }
        if (cleaning >= CLEANING || cleaning + 3 >= CLEANING) {
            System.out.println("ТРЕБУЕТСЯ ОЧИСТКА!");
            return;
        }
        if (waterLevel >= 3 * recipe.getWater() &&
                milkLevel >= 3 * recipe.getMilk() &&
                coffeeLevel >= 3 * recipe.getCoffee()) {
            waterLevel -= 3 * recipe.getWater();
            milkLevel -= 3 * recipe.getMilk();
            coffeeLevel -= 3 * recipe.getCoffee();
            log.add("Приготовлено 3 чашки " + recipe);
            System.out.println("Приготовлено 3 чашки " + recipe);
        } else {
            System.out.println("Недостаточно ингредиентов для приготовления 3 чашек " + recipe);
        }
    }

    public void makeCoffee(Recipe recipe, int cups) {
        if (!this.isOn) {
            System.out.println("Кофемашина выключена.");
            return;
        }
        if (cleaning >= CLEANING || cleaning + cups >= CLEANING) {
            System.out.println("ТРЕБУЕТСЯ ОЧИСТКА!");
            return;
        }
        if (waterLevel >= cups * recipe.getWater() &&
                milkLevel >= cups * recipe.getMilk() &&
                coffeeLevel >= cups * recipe.getCoffee()) {
            waterLevel -= cups * recipe.getWater();
            milkLevel -= cups * recipe.getMilk();
            coffeeLevel -= cups * recipe.getCoffee();
            log.add("Приготовлено чашек " + recipe + ": " + cups);
            System.out.println("Приготовлено " + cups + " чашек " + recipe + ".");
        } else {
            System.out.println("Недостаточно ингредиентов.");
        }
    }

    public void addProfile(String profileName, List<Profile> items) {
        profiles.put(profileName, items);
        System.out.println("Профиль '" + profileName + "' добавлен.");
    }

    public void makeProfileCoffee(String profileName) {
        List<Profile> items = profiles.get(profileName);
        if (items == null) {
            System.out.println("Профиль не найден.");
            return;
        }
        if (!isOn) {
            System.out.println("Кофемашина выключена.");
            return;
        }
        for (Profile item : items) {
            if (cleaning >= CLEANING || cleaning + item.getCups() >= CLEANING) {
                System.out.println("ТРЕБУЕТСЯ ОЧИСТКА!");
                return;
            }
            Recipe recipe = item.getRecipe();
            int cups = item.getCups();
            if (waterLevel >= cups * recipe.getWater() &&
                    milkLevel >= cups * recipe.getMilk() &&
                    coffeeLevel >= cups * recipe.getCoffee()) {
                waterLevel -= cups * recipe.getWater();
                milkLevel -= cups * recipe.getMilk();
                coffeeLevel -= cups * recipe.getCoffee();
                log.add("Приготовлено чашек " + recipe + ": " + cups);
                System.out.println("Приготовлено " + cups + " чашек " + recipe + ".");
            } else {
                System.out.println("Недостаточно ингредиентов для приготовления " + recipe + ".");
                return;
            }
        }
        cleaning += items.size(); // Допустим, что каждая порция увеличивает необходимость очистки
    }

    public void printLog() {
        if (log.isEmpty()) {
            System.out.println("Лог пуст.");
        } else {
            System.out.println("Лог операций:");
            for (String entry : log) {
                System.out.println(entry);
            }
        }
    }
}