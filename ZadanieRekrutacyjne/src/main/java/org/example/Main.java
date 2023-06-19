package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Block> blocks = new ArrayList<>();
        Wall wall = new Wall(blocks);
        System.out.println(wall.count());




    }
}