package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        Processor processor = new Processor((short)4600, (byte)2,  "Intel", (short)25);
        Ram ram = new Ram(RAM_TYPE.DDR4, (short)4024, (short)259);
        DiskDrive disk = new DiskDrive(DISK_TYPE.HDD, (short)1000, (short)823);
        Monitor monitor = new Monitor((byte)29, PANEL_TYPE.IPS, (short)1830);
        Keyboard keyboard = new Keyboard("Logitech", true, (short)220);
        Computer computer = new Computer("Some vendor", "Random computer", processor, disk, keyboard, monitor, ram);
        System.out.println(computer);
    }
}
