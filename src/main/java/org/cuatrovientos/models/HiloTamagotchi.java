package org.cuatrovientos.models;

public class HiloTamagotchi implements Runnable {
    private Tamagotchi tamagotchi;

    public HiloTamagotchi(Tamagotchi tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    @Override
    public void run() {
        long nacimiento = System.currentTimeMillis();
        try {

            System.out.println(tamagotchi.getNombre() + " se ha creado con exito.");

            while (tamagotchi.isVivo()) {
                long tiempoActual = System.currentTimeMillis();
                long tiempoTranscurrido = tiempoActual - nacimiento;
            
                if (tiempoTranscurrido >= tamagotchi.getTiempoVida()) {
                    System.out.println("El tamagotchi " + tamagotchi.getNombre() + " ha muerto. :(");
                    tamagotchi.setVivo(false);
                } else {
                    Thread.sleep(100);
                }
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            return;
        }
    }
}
