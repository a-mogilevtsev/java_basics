package ru.skillbox;

/**
 * Created by a.sosnina on 10/18/2021.
 */
public class Computer {
    private  String vendor;
    private  String name;
    private  Processor processor;
    private  DiskDrive disk;
    private  Keyboard keyboard;
    private  Monitor monitor;
    private  Ram ram;

    public Computer(String vendor, String name, Processor processor, DiskDrive disk, Keyboard keyboard, Monitor monitor, Ram ram) {
        this.vendor = vendor;
        this.name = name;
        this.processor = processor;
        this.disk = disk;
        this.keyboard = keyboard;
        this.monitor = monitor;
        this.ram = ram;
    }

    public String getVendor() {
        return vendor;
    }



    public String getName() {
        return name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public DiskDrive getDisk() {
        return disk;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public Ram getRam() {
        return ram;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void setDisk(DiskDrive disk) {
        this.disk = disk;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public int getTotalWeight() {
        return processor.getWeight() + disk.getWeight() + keyboard.getWeight() + monitor.getWeight() + ram.getWeight();
    }


    public String toString() {
        return "Computer: " +
                "vendor='" + vendor + '\'' +
                ", name='" + name + '\'' + "\n" +
                "This computer has components:" + "\n" +
                "1)" + processor + "\n" +
                "2)" + disk + "\n" +
                "3)" + keyboard + "\n" +
                "4)" + monitor + "\n" +
                "5)" + ram +
                '}';
    }
}

