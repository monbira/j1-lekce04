package cz.czechitas.kockamyssyr;

import cz.czechitas.kockamyssyr.api.*;

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
        new Tree(400, 200);
        new Tree(400, 200);
        new Tree(400, 200);
        new Tree(400, 200);
        // TODO: Sem vepište svůj program

        new Cat(50, 100);
        Cat tom = new Cat(100, 100);
        tom.setBrain(new KeyboardBrain());

        new Cheese(200, 200);

        Mouse jerry = new Mouse(600, 200);
        jerry.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        new Meat(200, 500);
    }

}
