package Market_Model;


public class Laptop extends Product implements LaptopSpecs {

    private String cpu;
    private int ramGB;
    private int SsdGB;
    private double screenSize;
    private String OS;
    private String model;


    public Laptop(int id, String name, String imageUrl, double price, String description,
                  String cpu, int ramGB, int SsdGB, double screenSize, String OS, String model) {
        super(id, name,price,description, imageUrl );
        this.cpu = cpu;
        this.ramGB = ramGB;
        this.SsdGB = SsdGB;
        this.screenSize = screenSize;
        this.OS = OS;
        this.model = model;
    }

    public String getCpu() {
        return cpu;
    }
    public int getRamGB() {
        return ramGB;
    }
    public int getSsdGB() {
        return SsdGB;
    }
    public double getScreenSize() {
        return screenSize;
    }
    public String getOS() {
        return OS;
    }
    public String getModel() {
        return model;
    }



    @Override // Relasition of method
    public String getCategoryName(){
        return "Laptop";
    }


}
