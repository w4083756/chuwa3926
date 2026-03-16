package HW3;

class Computer {
    private CPU cpu;
    private RAM ram;
    private HardDrive hd;

    public Computer(String cpuBrand, double cpuSpeed, double ramSize, double hdSize, String hdType) {
        this.cpu = new CPU(cpuBrand, cpuSpeed);
        this.ram = new RAM(ramSize);
        this.hd = new HardDrive(hdSize, hdType);
    }

    public String getSpecs() {
        return cpu + "\n" + ram + "\n" + hd;
    }

    public static void main(String[] args) {
        Computer c = new Computer("AMD", 32, 16, 512, "SSD");
        System.out.println(c.getSpecs());
    }
}

class CPU {
    private String brand;
    private double speed;

    public CPU(String brand, double speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public String toString() {
        return "CPU: " + brand + " " + "speed: " + speed;
    }
}

class RAM {
    private double size;

    public RAM(double size) {
        this.size = size;
    }

    public String toString() {
        return "RAM size: " + size + "GB";
    }
}

class HardDrive {
    private double size;
    private String type;

    public HardDrive(double size, String type) {
        this.size = size;
        this.type = type;
    }

    public String toString() {
        return "HardDrive size: " + size + "GB" + " " + "HardDrive type: " + type;
    }
}


