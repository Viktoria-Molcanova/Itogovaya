package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class PetRegistry {
    private final List<Animal> animals = new ArrayList<>();
    private int nextId;

    public void addAnimal(Animal animal) {
        animals.add(animal);
        saveToFile();
    }

    public void showAnimals() {
        try (BufferedReader reader = new BufferedReader(new FileReader("animals.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("animals.txt", false))) {
            for (Animal animal : animals) {
                writer.write("ID: " + animal.getId() + ", Тип: " + animal.getType() + ", Животное: " + animal.getName() + ", Команды: " + animal.getCommands());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PetRegistry registry = new PetRegistry();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Введите команду (" +
                    "1 - добавить животное, " +
                    "2 - показать список, " +
                    "3 - обучить животное, " +
                    "4 - выход): ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.println("Введите имя животного: ");
                    String name = scanner.nextLine();
                    System.out.println("Выберите тип (" +
                            "1 - собака, " +
                            "2 - кошка, " +
                            "3 - хомяк, " +
                            "4 - лошадь, " +
                            "5 - верблюд, " +
                            "6 - осел): ");
                    String type = scanner.nextLine();

                    Animal animal = null;
                    switch (type) {
                        case "1":
                            animal = new Dog(registry.nextId++, name, type);
                            break;
                        case "2":
                            animal = new Cat(registry.nextId++, name, type);
                            break;
                        case "3":
                            animal = new Hamster(registry.nextId++, name, type);
                            break;
                        case "4":
                            animal = new Horse(registry.nextId++, name, type);
                            break;
                        case "5":
                            animal = new Camel(registry.nextId++, name, type);
                            break;
                        case "6":
                            animal = new Donkey(registry.nextId++, name, type);
                            break;
                        default:
                            System.out.println("Неизвестный тип животного.");
                            continue;
                    }

                    registry.addAnimal(animal);
                    System.out.println("Животное добавлено: " + name);
                    break;

                case "2":
                    registry.showAnimals();
                    break;

                case "3":
                    System.out.println("Введите имя животного, которого хотите обучить: ");
                    String nameToTrain = scanner.nextLine();
                    Animal animalToTrain = null;

                    for (Animal animalItem : registry.animals) {
                        if (animalItem.getName().equalsIgnoreCase(nameToTrain)) {
                            animalToTrain = animalItem;
                            break;
                        }
                    }

                    if (animalToTrain != null) {
                        System.out.println("Введите новую команду для " + nameToTrain + ": ");
                        String newCommand = scanner.nextLine();
                        animalToTrain.addCommand(newCommand);
                        System.out.println(nameToTrain + " обучен новой команде: " + newCommand);
                        registry.saveToFile();
                    } else {
                        System.out.println("Животное с таким именем не найдено.");
                    }
                    break;

                case "4":
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неизвестная команда.");
            }
        }
    }
}