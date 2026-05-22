package model;

import interfaces.Laptop;

public class MacBook implements Laptop {
    private int volume;
    private boolean is_power_on;

    public MacBook() {
        this.volume = 50;
    }

    @Override
    public void powerOn() {
        is_power_on = true;
        System.out.println("Laptop is On");
        System.out.println("MacBook Pro");
    }

    @Override
    public void powerOff() {
        is_power_on = false;
        System.out.println("Shutdown in process ...");
    }

    @Override
    public void volumeUp() {
        if (is_power_on) {
            if (this.volume == MAX_VOL) {
                System.out.println("Volume is Full");
            } else {
                this.volume += 10;
                System.out.println("Volume is : " + this.getVolume());
            }
        } else {
            System.out.println("Nyalakan laptop terlebih dahulu!");
        }
    }

    @Override
    public void volumeDown() {
        if (is_power_on) {
            if (this.volume == MIN_VOL) {
                System.out.println("Volume is 0%");
            } else {
                this.volume -= 10;
                System.out.println("Volume is : " + this.getVolume());
            }
        } else {
            System.out.println("Nyalakan laptop terlebih dahulu!");
        }
    }

    public int getVolume() {
        return this.volume;
    }
}