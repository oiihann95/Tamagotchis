package org.cuatrovientos.models;

public class HiloTamagotchi implements Runnable {
    private Tamagotchi tamagotchi;

    public HiloTamagotchi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    @Override
    public void run() {
        long nacimiento = System.currentTimeMillis();
        long ultimoAumentoSuciedad = 0;
        String interaccion;
        try {
            System.out.println(tamagotchi.getNombre() + " se ha creado con exito.");

            while (tamagotchi.isVivo()) {
                long tiempoActual = System.currentTimeMillis();
                long tiempoTranscurrido = tiempoActual - nacimiento;

                long tiempoSuciedad = tiempoTranscurrido - ultimoAumentoSuciedad;
                if (tamagotchi.isOcupado() == false) {
                    gestionarInteracciones(interaccion);
                }
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

    public void gestionarInteracciones(String interaccion) {
        if (interaccion.equals("comer")) {
            comer();
        }
    }

    public void comer() {
        System.out.println(tamagotchi.getNombre() + ": se esta comiendo una manzana.");
        Thread.sleep(tamagotchi.getVelocidadComida());
        System.out.println(tamagotchi.getNombre() + ": mmm que rica manzana");
    }

}