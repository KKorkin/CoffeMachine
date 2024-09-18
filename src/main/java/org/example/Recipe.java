package org.example;

public enum Recipe {
    CAPPUCCINO(120, 6, 60),
    ESPRESSO(0, 12, 60);

    private final int milk;
    private final int coffee;
    private final int water;

    Recipe(int milk, int coffee, int water) {
        this.milk = milk;
        this.coffee = coffee;
        this.water = water;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public String getRecipe() {
        return String.format("Рецепт: %d грамм кофе, %d мл молока, %d мл воды", coffee, milk, water);
    }
}