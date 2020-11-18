package sup.mr.geary.dataClass;

public class Sales {
    private final double q1;
    private final double q2;
    private final double q3;
    private final double q4;

    public Sales(double q1, double q2, double q3, double q4) {
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
    }

    public double totalSales(){
        return q1 + q2 + q3 + q4;
    }

    public double getQ(int q){
        return switch (q) {
            case 1 -> q1;
            case 2 -> q2;
            case 3 -> q3;
            default -> q4;
        };
    }
}
