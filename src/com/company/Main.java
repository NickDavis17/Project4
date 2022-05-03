package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import com.google.gson.Gson;

public class Main {
    static Scanner input = new Scanner(System.in);
    static int priority;
    static Gson gson = new Gson();

    static File saveData = new File("C:\\Users\\063815\\IdeaProjects\\Project4\\data.json");
    static FileReader fileReader;
    static{
        try{
            fileReader = new FileReader(saveData);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        menu();

        ArrayList<Task> list = new ArrayList<Task>();


        int choice = input.nextInt();
        //System.out.println(choice);
        while ((choice == 1) || (choice == 2) || (choice == 3) || (choice == 4) || (choice == 5) || (choice == 6)) {
            // If the user chooses 1
            if (choice == 1) {
                add(list);
                System.out.println();
                // User chooses 2
            } else if (choice == 2) {
                removeTask(list);
                // 3
            } else if (choice == 3) {
                updateTask(list);
                // 4
            } else if (choice == 4) {

                displayList(list);
            } else if (choice == 5) {
                displayListPriority(list);
            }
            else if (choice == 6) {
                serializeSimple(list);
            }
            menu();

            choice = input.nextInt();
        }
        // 0
        if (choice == 0) {
            System.out.println("Goodbye");
            System.exit(0);
        }
        //anything else
        else {
            menu();

            choice = input.nextInt();
            System.out.println(choice);
        }
        menu();

        choice = input.nextInt();
        System.out.println(choice);
    }

    static void serializeSimple(ArrayList<Task> a){
//        Task list = new Task(title, add, priority);

        // System.out.println(person);
        String json = gson.toJson(a);

        try {
            FileWriter writer = new FileWriter("data.json");
            gson.toJson(a, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static void deserializeSimple(){
        String list1Json = " { \"description\": \"Walk the dog\", \"priority\": 2, \"title\": \"dog\"}";
        String list2Json = " {\"body\": \"Pay the bills\", \"done\": false, \"id\": 1, \"priority\": 1, \"title\": \"bills\"}";
        Gson gson = new Gson();
        Task list1 = gson.fromJson(list1Json,Task.class);

//        a.add(list1);
//        a.add(list2);
//        System.out.println(a);
        System.out.println(list1);
        System.out.println(list1.getClass().getSimpleName());


    }

    static void menu() {
        System.out.println("Please choose an option:");
        System.out.println("(1) Add a task.");
        System.out.println("(2) Remove a task.");
        System.out.println("(3) Update a task.");
        System.out.println("(4) List all tasks.");
        System.out.println("(5) List all tasks of a certain priority.");
        System.out.println("(6) Serialize");
        System.out.println("(0) Exit.");
    }

    //Adds an item to the list
    static void add(ArrayList<Task> a) {
        input.nextLine();
        String title = promptString("What is the name of the task you would like to add?");
        String add = promptString("Please give of a description of the task you would like to add");
        priority = promptInt("What Priority does this task have (1 = least, 5 = most)");
        Task task1 = new Task(title, add, priority);
        a.add(task1);


    }

    //removes an item
    static void removeTask(ArrayList<Task> a) {
        System.out.println(a);
        System.out.println("Which would you like to remove? (1, 2, 3, 4, Etc...");
        int remove = input.nextInt() - 1;
        while (remove > a.size()) {
            if (remove > a.size()) {
                System.out.println("Please enter a valid number");
                remove = input.nextInt() - 1;
            }
        }

        a.remove(remove);
    }


    //
    static void updateTask(ArrayList<Task> a) {
        System.out.println(a);
        System.out.println("Which would you like to update? (1, 2, 3, 4, Etc...");
        int remove = input.nextInt() - 1;
        while (remove > a.size()) {
            if (remove > a.size()) {
                System.out.println("Please enter a valid number");
                remove = input.nextInt() - 1;
            }
        }
        String add = promptString("What would you like to change it to?");
        String description = promptString("Please give it a description");
        priority = promptInt("What Priority does this have?");
        a.remove(remove);
        Task newTask = new Task(add, description, priority);
        a.add(newTask);

    }

    static void displayList(ArrayList<Task> a) {
        Collections.sort(a);
        System.out.println("Listing all tasks...");
        System.out.println(a);
    }

    static void displayListPriority(ArrayList<Task> a){
        input.nextLine();
        priority = promptInt("Which Priority would you like to look at?");
        System.out.println("Listing all tasks with the priority " + priority);
        for (Task task: a) {
            task.getPriority();
            if(task.getPriority() == priority){
                System.out.println(task);
            }
        }

    }

    static String promptString(String message) {
        System.out.println(message);
        String userInput = input.nextLine();

        String userString = "";
        boolean isString = false;
        while (!isString) {
            try {

                double b = Double.parseDouble(userInput);
                System.out.println(userInput + " is not a valid string. " + message);
                userInput = input.nextLine();

            }
            catch (Exception e){
                isString = true;
            }


        }

        return userInput;
    }
    static int promptInt(String message) {
        System.out.println(message);
        String userInput = input.nextLine();

        int userInt = 0;
        boolean isInt = false;
        while (!isInt) {
            try {
                userInt = Integer.parseInt(userInput);
                isInt = true;
            }
            catch (NumberFormatException e) {
                System.out.println(userInput + " is not a valid integer. " + message);
                userInput = input.nextLine();
            }
        }

        return userInt;
    }

}
