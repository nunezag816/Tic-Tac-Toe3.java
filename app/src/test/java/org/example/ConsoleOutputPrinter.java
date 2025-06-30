package org.example;

public class ConsoleOutputPrinter implements OutputPrinter {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
