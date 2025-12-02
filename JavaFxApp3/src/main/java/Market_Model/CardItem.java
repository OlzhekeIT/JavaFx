package Market_Model;
// корзинадағы орны postion of korzina


public class CardItem {

    private Product product;// Товар келеді
    private int quantity; // количество товар


    public CardItem(Product product, int quantity){
        if (quantity == 0){
            throw new IllegalArgumentException("Товардың количестовасы 1 болу керек");
        }
        this.product = product;
        this.quantity = quantity;
    }


    public Product getProduct() {
        return product;
    }

    public int getQuantity(int i) {
        return quantity;
    }




    // Method плюс товар каждый
    public void addQuantity(int amount){
        quantity = quantity + amount;
    }

    // Method для цена товар
    public double getTotalPrice(){
        return product.getPrice() * quantity;
    }






}
