package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachina machine = new CoffeeMachina();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Меню кофемашины ---");
            System.out.println("1. Включить кофемашину");
            System.out.println("2. Выключить кофемашину");
            System.out.println("3. Добавить воду");
            System.out.println("4. Добавить молоко");
            System.out.println("5. Добавить кофе");
            System.out.println("6. Проверить уровень воды");
            System.out.println("7. Проверить уровень молока");
            System.out.println("8. Проверить уровень кофе");
            System.out.println("9. Проверить необходимость очистки");
            System.out.println("10. Очистить кофемашину");
            System.out.println("11. Приготовить кофе");
            System.out.println("12. Добавить профиль напитка");
            System.out.println("13. Приготовить напиток по профилю");
            System.out.println("14. Показать лог");
            System.out.println("15. Выход");

            System.out.print("Выберите опцию: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    machine.powerOn();
                    System.out.println("Кофемашина включена.");
                    break;
                case 2:
                    machine.powerOff();
                    System.out.println("Кофемашина выключена.");
                    break;
                case 3:
                    System.out.print("Введите количество воды (мл): ");
                    int water = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    machine.addWater(water);
                    break;
                case 4:
                    System.out.print("Введите количество молока (мл): ");
                    int milk = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    machine.addMilk(milk);
                    break;
                case 5:
                    System.out.print("Введите количество кофе (г): ");
                    int coffee = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    machine.addCoffee(coffee);
                    break;
                case 6:
                    System.out.println("Уровень воды: " + machine.getWaterLevel() + " мл");
                    break;
                case 7:
                    System.out.println("Уровень молока: " + machine.getMilkLevel() + " мл");
                    break;
                case 8:
                    System.out.println("Уровень кофе: " + machine.getCoffeeLevel() + " г");
                    break;
                case 9:
                    machine.cleanInfo();
                    break;
                case 10:
                    machine.clean();
                    break;
                case 11:
                    System.out.println("Выберите напиток:");
                    System.out.println("1. Эспрессо");
                    System.out.println("2. Капучино");
                    int drinkOption = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Введите количество чашек: ");
                    int cups = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (drinkOption == 1) {
                        machine.makeCoffee(Recipe.ESPRESSO, cups);
                    } else if (drinkOption == 2) {
                        machine.makeCoffee(Recipe.CAPPUCCINO, cups);
                    } else {
                        System.out.println("Неверный выбор напитка.");
                    }
                    break;
                case 12:
                    System.out.print("Введите имя профиля: ");
                    String profileName = scanner.nextLine();
                    List<Profile> items = new ArrayList<>();
                    boolean addingItems = true;
                    while (addingItems) {
                        System.out.println("Выберите напиток для профиля:");
                        System.out.println("1. Эспрессо");
                        System.out.println("2. Капучино");
                        int profileDrinkOption = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Введите количество чашек: ");
                        int profileCups = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        Recipe profileRecipe = (profileDrinkOption == 1) ? Recipe.ESPRESSO : Recipe.CAPPUCCINO;
                        items.add(new Profile(profileRecipe, profileCups));
                        System.out.print("Добавить еще напиток в профиль? (y/n): ");
                        String continueAdding = scanner.nextLine();
                        if (continueAdding.equalsIgnoreCase("n")) {
                            addingItems = false;
                        }
                    }
                    machine.addProfile(profileName, items);
                    break;
                case 13:
                    System.out.print("Введите имя профиля для приготовления: ");
                    String profileToMake = scanner.nextLine();
                    machine.makeProfileCoffee(profileToMake);
                    break;
                case 14:
                    machine.printLog();
                    break;
                case 15:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверная опция. Попробуйте снова.");
            }
        }
        scanner.close();
    }
}
