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
        tom.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        jerry = vytvorMys();
        jerry.setBrain(new KeyboardBrain());

        vytvorVeci(4);
        chytMys();
    }

    public void chytMys() {
        while (jerry.isAlive()){
            jdiZaJerrymAVyhniSeStromu();
        }

    }


    private void jdiZaJerrymAVyhniSeStromu(){
        int horizontalniRozdil = (jerry.getX() - tom.getX());
        if (horizontalniRozdil < 0) {
            OtocSeVlevo();
            while (jerry.getX() < tom.getX()){
                vyhniSeStromu();
                tom.moveForward();}
        } else if (horizontalniRozdil > 0) {
            OtocSeVpravo();
            while (jerry.getX()> tom.getX());{
                vyhniSeStromu();
                tom.moveForward();}
        }
        int vertikalniRozdil = (jerry.getY() - tom.getY());
        if (vertikalniRozdil < 0) {
            OtocSeNahoru();
            while (jerry.getY()< tom.getY()){
                vyhniSeStromu();
                tom.moveForward();}
        } else if (vertikalniRozdil > 0) {
            OtocSeDolu();
            while (jerry.getY() > tom.getY()){
                vyhniSeStromu();
                tom.moveForward();
            }
        }

    }
    private void vyhniSeStromu(){
        if (tom.isPossibleToMoveForward()) {
            return;
        }
        tom.turnRight();
        tom.moveForward();
        tom.turnLeft();
    }

    private void jdiZaJerrym(){
        int horizontalniRozdil = (jerry.getX() - tom.getX());
        if (horizontalniRozdil < 0) {
            OtocSeVlevo();
            while (jerry.getX() < tom.getX()){
                tom.moveForward();}
        } else if (horizontalniRozdil > 0) {
            OtocSeVpravo();
            while (jerry.getX()> tom.getX());{
                tom.moveForward();}
        }
        int vertikalniRozdil = (jerry.getY() - tom.getY());
        if (vertikalniRozdil < 0) {
            OtocSeNahoru();
            while (jerry.getY()< tom.getY()){
                tom.moveForward();}
        } else if (vertikalniRozdil > 0) {
            OtocSeDolu();
            while (jerry.getY() > tom.getY()){
                tom.moveForward();
            }
        }

    }

    private void jdiNaSouradnice(int x, int y) {
        int horizontalniRozdil = (x - tom.getX());
        if (horizontalniRozdil < 0) {
            OtocSeVlevo();
            tom.moveForward(Math.abs(horizontalniRozdil));
        }
        else if (horizontalniRozdil > 0) {
            OtocSeVpravo();
            tom.moveForward(Math.abs(horizontalniRozdil));
        }

        int vertikalniRozdil = (y - tom.getY());
        if (vertikalniRozdil < 0) {
            OtocSeNahoru();
            tom.moveForward(Math.abs(vertikalniRozdil));
        }
        else if (vertikalniRozdil > 0) {
            OtocSeDolu();
            tom.moveForward(Math.abs(vertikalniRozdil));
        }
    }
    private void OtocSeDolu() {
        if (tom.getOrientation() == PlayerOrientation.DOWN) {
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnLeft();
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnRight();
            return;
        }
        tom.turnLeft();
        tom.turnLeft();
    }

    private void OtocSeVlevo() {
        if (tom.getOrientation() == PlayerOrientation.LEFT) {
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnLeft();
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnLeft();
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnLeft();
            tom.turnLeft();
            return;
        }
    }

    private void OtocSeNahoru() {
        if (tom.getOrientation() == PlayerOrientation.UP) {
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnRight();
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnLeft();
            return;
        }

        if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnLeft();
            tom.turnLeft();
            return;
        }
    }

    private void OtocSeVpravo() {
        if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnLeft();
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnRight();
            return;
        }
        if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnRight();
            tom.turnRight();
            return;
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