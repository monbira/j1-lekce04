package cz.czechitas.kockamyssyr;

import cz.czechitas.kockamyssyr.api.*;

import java.awt.*;

/**
 * Hlaví třída pro hru Kočka–myš–sýr.
 */
public class HlavniProgram {

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
        new Tree(new Point(400, 200));
        new Tree(new Point(400, 200));
        new Tree(new Point(400, 200));
        new Tree(new Point(400, 200));
        // TODO: Sem vepište svůj program

        Cat tom = new Cat(new Point(100, 100));
        tom.setBrain(new KeyboardBrain());

        new Cheese(new Point(200, 200));

        Mouse jerry = new Mouse(new Point(600, 200));
        jerry.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        new Meat(new Point(200, 500));
    }

}
