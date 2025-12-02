package Market_Model;

public class Smartphone extends Product implements SmartphoneSpecs {

    private double screenSize;
    private int ramGB;
    private int storageGB;
    private int batteryMAh;
    private String camera;
    private String model;

    public Smartphone(int id, String name, double price, String description, String imageURL,
                      double screenSize, int ramGB, int storageGB, int batteryMAh, String camera, String model) {
        super(id, name, price, description, imageURL);
        this.screenSize = screenSize;
        this.ramGB = ramGB;
        this.storageGB = storageGB;
        this.batteryMAh = batteryMAh;
        this.camera = camera;
        this.model = model;
    }

    public double getScreenSize() {
        return screenSize;
    }
    public int getRamGB() {
        return ramGB;
    }
    public int getStorageGB() {
        return storageGB;
    }
    public int getBatteryMAh() {
        return batteryMAh;
    }
    public String getCamera() {
        return camera;
    }
    public String getModel() {
        return model;
    }



    @Override // Relasition of method
    public String getCategoryName(){
        return "Smartphones";
    }
}
