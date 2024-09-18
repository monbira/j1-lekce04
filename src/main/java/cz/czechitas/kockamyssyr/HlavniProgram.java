package cz.czechitas.kockamyssyr;

import cz.czechitas.kockamyssyr.api.*;

import java.awt.*;
import java.util.Random;

/**
 * Hlaví třída pro hru Kočka–myš–sýr.
 */
public class HlavniProgram {
    private final Random random = new Random();

    private final int VELIKOST_PRVKU = 50;
    private final int SIRKA_OKNA = 1000 - VELIKOST_PRVKU;
    private final int VYSKA_OKNA = 600 - VELIKOST_PRVKU;

    private Cat tom;
    private Mouse jerry;

    /**
     * Spouštěcí metoda celé aplikace.
     *
     * @param args
     */
    public static void main(String[] args) {
        new HlavniProgram().run();
    }

    /**
     * Hlavní metoda obsahující výkonný kód.
     */
    public void run() {
        tom = vytvorKocku();
        //tom.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        jerry = vytvorMys();
        jerry.setBrain(new KeyboardBrain());

        vytvorVeci(4);
        chytMys();
    }

    public void chytMys() {

        while (jerry.isAlive()) {

            tomGetJerryOnX();
            tomGetJerryOnY();

        }



    }

    private void tomGetJerryOnX() {
        if (tom.getX() < jerry.getX()) {
            tomTurnRight();
            tom.moveForward();
            dodgeTree();
        } else if (tom.getX() > jerry.getX()) {
            tomTurnLeft();
            tom.moveForward();
            dodgeTree();
        } else if (tom.getX() == jerry.getX()) {
            tomGetJerryOnY();
        }
    }

    private void tomGetJerryOnY() {
        if (tom.getY() < jerry.getY()) {
            tomTurnDown();
            tom.moveForward();
            dodgeTree();
        } else if (tom.getY() > jerry.getY()) {
            tomTurnUp();
            tom.moveForward();
            dodgeTree();
        } else if (tom.getY() == jerry.getY()) {
            tomGetJerryOnX();
        }
    }

    private void tomTurnUp() {
        if (tom.getOrientation() == PlayerOrientation.UP) {

        } else if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnRight();
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnLeft();
        }
    }

    private void tomTurnDown() {
        if (tom.getOrientation() == PlayerOrientation.DOWN) {

        } else if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnRight();
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnRight();
        }
    }

    private void tomTurnLeft() {
        if (tom.getOrientation() == PlayerOrientation.LEFT) {

        } else if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnRight();
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnRight();
        }
    }

    private void tomTurnRight() {
        if (tom.getOrientation() == PlayerOrientation.RIGHT) {

        } else if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnRight();
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnLeft();
        }
    }

    private void dodgeTree() {
        if (!tom.isPossibleToMoveForward()) {
            tom.turnRight();
            tom.moveForward(50);
        }
    }


    public void vytvorVeci(int pocetStromu) {
        for (int i = 0; i < pocetStromu; i++) {
            vytvorStrom();
        }
        vytvorSyr();
        vytvorJitrnici();
    }
    public Tree vytvorStrom() {
        return new Tree(vytvorNahodnyBod());
    }

    public Cat vytvorKocku() {
        return new Cat(vytvorNahodnyBod());
    }

    public Mouse vytvorMys() {
        return new Mouse(vytvorNahodnyBod());
    }

    public Cheese vytvorSyr() {
        return new Cheese(vytvorNahodnyBod());
    }

    public Meat vytvorJitrnici() {
        return new Meat(vytvorNahodnyBod());
    }

    private Point vytvorNahodnyBod() {
        return new Point(random.nextInt(SIRKA_OKNA), random.nextInt(VYSKA_OKNA));
    }

}