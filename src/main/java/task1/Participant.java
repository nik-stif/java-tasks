package task1;

public class Participant {
    private String name;
    private double payment;

    public Participant(String name, double payment) {
        this.name = name;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
