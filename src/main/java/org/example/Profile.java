package org.example;

public class Profile {
    private final Recipe recipe;
    private final int cups;

    public Profile(Recipe recipe, int cups) {
        this.recipe = recipe;
        this.cups = cups;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getCups() {
        return cups;
    }
}
