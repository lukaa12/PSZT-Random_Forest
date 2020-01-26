
import java.util.ArrayList;

public class Sample {

    private ArrayList<Double> attributes = new ArrayList<>();
    private double result;

    public Sample(ArrayList<Double> attr,
                  Double argResult) {
        for(int i = 0; i < 13; i++) {
            attributes.add(attr.get(i));
        }
        result = argResult;
    }

    public ArrayList<Double> getAttributes() {
        return attributes;
    }

    public double getResult() {
        return result;
    }



}
