package org.cuatrovientos.models;

import java.util.Random;
import java.util.Scanner;

public class HiloTamagotchi implements Runnable {
    private Tamagotchi tamagotchi;
    private static final Random rnd = new Random();
    private static final Scanner scan = new Scanner(System.in);

    public HiloTamagotchi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    @Override
    public void run() {
        long nacimiento = System.currentTimeMillis();
        long ultimoAumentoSuciedad = 0;

        try {
            System.out.println(tamagotchi.getNombre() + " se ha creado con exito.");

            while (tamagotchi.isVivo()) {
                long tiempoActual = System.currentTimeMillis();
                long tiempoTranscurrido = tiempoActual - nacimiento;

                Interacciones interaccion = tamagotchi.getInteraccion();
                if (interaccion != Interacciones.NINGUNA) {
                    gestionarInteracciones(interaccion);
                }

                long tiempoSuciedad = tiempoTranscurrido - ultimoAumentoSuciedad;
                if (tiempoSuciedad >= 20000) {
                    tamagotchi.aumentarSuciedad();
                    ultimoAumentoSuciedad = tiempoTranscurrido;

                    if (tamagotchi.getSuciedad() == 5) {
                        System.out.println(tamagotchi.getNombre() + ": Estoy muy sucio dame un baño.");
                    }

                    if (tamagotchi.getSuciedad() >= 10) {
                        morir();
                    }
                }

                if (tiempoTranscurrido >= tamagotchi.getTiempoVida()) {
                    morir();
                } else {
                    Thread.sleep(100);
                }
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    public void morir() {
        System.out.println("El tamagotchi " + tamagotchi.getNombre() + " ha muerto. :(");
        tamagotchi.setVivo(false);
    }

    public void gestionarInteracciones(Interacciones interaccion) throws InterruptedException {
        if (interaccion == Interacciones.COMER) {
            comer();
        }
        if (interaccion == Interacciones.JUGAR) {
            jugar();
        }
        if (interaccion == Interacciones.MATAR) {
            morir();
        }
        if (interaccion == Interacciones.LIMPIARSE) {
            limpiarse();
        }
    }

    public void comer() throws InterruptedException {
        tamagotchi.setOcupado(true);
        System.out.println(tamagotchi.getNombre() + ": se esta comiendo una manzana.");
        Thread.sleep(tamagotchi.getVelocidadComida());
        System.out.println(tamagotchi.getNombre() + ": mmm que rica manzana");
        tamagotchi.setInteraccion(Interacciones.NINGUNA);
        tamagotchi.setOcupado(false);
    }

    public void jugar() {
        tamagotchi.setOcupado(true);
        System.out.println("Has empezado a jugar con " + tamagotchi.getNombre());

        int respuesta = 0;
        boolean esCorrecto = true;

        do {

            int num1 = rnd.nextInt(5) + 1;
            int num2 = rnd.nextInt(5) + 1;

            System.out.print(num1 + " + " + num2 + " = ");

            try {
                respuesta = scan.nextInt();

                if (respuesta == (num1 + num2)) {
                    System.out.println("¡Correcto!");
                    esCorrecto = true;
                } else {
                    System.out.println("Incorrecto... ¡Has fallado!");
                    esCorrecto = false;
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: ¡Debes introducir un número entero!");
                scan.next();
                esCorrecto = false;
            }

        } while (!esCorrecto);

        System.out.println("Fin del juego.");
        tamagotchi.setInteraccion(Interacciones.NINGUNA);
        tamagotchi.setOcupado(false);
    }

    public void limpiarse() throws InterruptedException {
        tamagotchi.setOcupado(true);
        System.out.println(tamagotchi.getNombre() + ": Se esta dando un baño.");
        Thread.sleep(5000);
        tamagotchi.setSuciedad(0);
        ultimoAumentoSuciedad = 0;
        System.out.println(tamagotchi.getNombre() + ": Esta limpio.");
        tamagotchi.setInteraccion(Interacciones.NINGUNA);
        tamagotchi.setOcupado(false);
    }
}