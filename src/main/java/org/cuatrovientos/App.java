package org.cuatrovientos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.cuatrovientos.models.HiloTamagotchi;
import org.cuatrovientos.models.Interacciones;
import org.cuatrovientos.models.Tamagotchi;

public class App {
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        List<Tamagotchi> listaTamagotchis = new ArrayList<>();

        List<Thread> listaDeHilos = new ArrayList<>();

        int contador = 1;
        int respuesta = -1;
        do {
            do {
                System.out.println("\n===== CUIDADOR TAMAGOTCHI =====");
                System.out.println("1. Crear Tamagotchi");
                System.out.println("2. Dar de comer");
                System.out.println("3. Jugar");
                System.out.println("4. Bañar");
                System.out.println("5. Consultar estado");
                System.out.println("6. Matar Tamagotchi");
                System.out.println("0. Salir");
                System.out.print("Selecciona una opcion: ");

                try {
                    respuesta = scan.nextInt();

                } catch (java.util.InputMismatchException e) {
                    System.out.println("Error: ¡Debes introducir un número entero!");
                    scan.next();
                }
            } while (respuesta < 0 || respuesta > 6);
            Tamagotchi tamagotchi;
            switch (respuesta) {
                case 1:
                    scan.nextLine();

                    System.out.print("Introduce nombre del tamagotchi: ");
                    String nombre = scan.nextLine();

                    tamagotchi = new Tamagotchi(contador, nombre);

                    listaTamagotchis.add(tamagotchi);

                    HiloTamagotchi hiloTamagotchi = new HiloTamagotchi(tamagotchi);
                    Thread hilo = new Thread(hiloTamagotchi);

                    listaDeHilos.add(hilo);
                    hilo.start();

                    contador++;
                    break;
                case 2:
                    tamagotchi = seleccionarTamagotchi(listaTamagotchis);

                    if (tamagotchi != null && tamagotchi.isVivo()) {
                        if (!tamagotchi.isOcupado()
                                && tamagotchi.getInteraccion() == Interacciones.NINGUNA) {
                            tamagotchi.setInteraccion(Interacciones.COMER);
                        } else {
                            System.out.println("El Tamagotchi está ocupado o tiene una orden pendiente.");
                        }
                    }
                    break;
                case 3:
                    tamagotchi = seleccionarTamagotchi(listaTamagotchis);

                    if (tamagotchi != null && tamagotchi.isVivo()) {
                        if (!tamagotchi.isOcupado()
                                && tamagotchi.getInteraccion() == Interacciones.NINGUNA) {
                            tamagotchi.setInteraccion(Interacciones.JUGAR);
                        } else {
                            System.out.println("El Tamagotchi está ocupado o tiene una orden pendiente.");
                        }
                    }
                    break;
                case 4:
                    tamagotchi = seleccionarTamagotchi(listaTamagotchis);

                    if (tamagotchi != null && tamagotchi.isVivo()) {
                        if (!tamagotchi.isOcupado()
                                && tamagotchi.getInteraccion() == Interacciones.NINGUNA) {
                            tamagotchi.setInteraccion(Interacciones.LIMPIARSE);
                        } else {
                            System.out.println("El Tamagotchi está ocupado o tiene una orden pendiente.");
                        }
                    }
                    break;
                case 5:
                    consultarEstado(listaTamagotchis);
                    break;
                case 6:
                    tamagotchi = seleccionarTamagotchi(listaTamagotchis);

                    if (tamagotchi != null && tamagotchi.isVivo()) {
                        if (!tamagotchi.isOcupado()
                                && tamagotchi.getInteraccion() == Interacciones.NINGUNA) {
                            tamagotchi.setInteraccion(Interacciones.MATAR);
                        } else {
                            System.out.println("El Tamagotchi está ocupado o tiene una orden pendiente.");
                        }
                    }
                    break;
                default:
                    break;
            }
            if (respuesta != 0) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        } while (respuesta != 0);

    }

    public static Tamagotchi seleccionarTamagotchi(List<Tamagotchi> tamagotchis) {
        if (tamagotchis.isEmpty()) {
            System.out.println("No hay ningún Tamagotchi creado.");
            return null;
        }
        System.out.println("===== SELECCIONAR TAMAGOTCHI =====");
        for (Tamagotchi tamagotchi : tamagotchis) {
            System.out.println(tamagotchi.toString());
        }
        System.out.println("Introduce la ID del Tamagotchi: ");
        int idSeleccionada = scan.nextInt();

        for (Tamagotchi tamagotchi : tamagotchis) {
            if (tamagotchi.getId() == idSeleccionada) {
                return tamagotchi;
            }
        }
        System.out.println("No existe ningún Tamagotchi con esa ID.");
        return null;
    }

    public static void consultarEstado(List<Tamagotchi> tamagotchis) {
        if (tamagotchis.isEmpty()) {
            System.out.println("No hay ningún Tamagotchi creado.");
            return;
        }
        Tamagotchi tamagotchi = seleccionarTamagotchi(tamagotchis);

        if (tamagotchi == null) {
            return;
        }

        System.out.println("\n===== ESTADO DEL TAMAGOTCHI =====");
        System.out.println("ID: " + tamagotchi.getId());
        System.out.println("Nombre: " + tamagotchi.getNombre());
        System.out.println("Suciedad: " + tamagotchi.getSuciedad() + "/10");
        System.out.println("Tiempo de vida: " + tamagotchi.getTiempoVida() + " ms");

        if (tamagotchi.isVivo()) {
            System.out.println("Vivo: Sí");
        } else {
            System.out.println("Vivo: No");
        }
        if (tamagotchi.isOcupado()) {
            System.out.println("Ocupado: Sí");
        } else {
            System.out.println("Ocupado: No");
        }
        System.out.println("Interacción pendiente: " + tamagotchi.getInteraccion());
    }
}
