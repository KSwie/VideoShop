package main_class;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args)
    {
        EventQueue.invokeLater((Runnable) () -> {
            Window window = new Window();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
            window.setResizable(false);
        });


    }
}
