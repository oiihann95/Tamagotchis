package org.cuatrovientos.models;

import java.util.Random;

public class Tamagotchi {
    private static final Random rnd = new Random();

    private int id;
    private String nombre;
    private int suciedad;
    private long tiempoVida;
    private int velocidadComida;
    private boolean vivo;
    private boolean ocupado;

    public Tamagotchi() {
    }

    public Tamagotchi(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.suciedad = 0;
        this.tiempoVida = 300000;
        this.velocidadComida = rnd.nextInt(10000, 80000);
        this.vivo = true;
        this.ocupado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSuciedad() {
        return suciedad;
    }

    public void setSuciedad(int suciedad) {
        this.suciedad = suciedad;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(int tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public int getVelocidadComida() {
        return velocidadComida;
    }

    public void setVelocidadComida(int velocidadComida) {
        this.velocidadComida = velocidadComida;
    }

}
