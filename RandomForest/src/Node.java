import java.util.ArrayList;

public class Node {

    Node lowerValuesSon, biggerValuesSon;
    ArrayList<Sample> samples;
    DividerAttribute divider;

    public void setLowerValuesSon(Node lowerValuesSon) {
        this.lowerValuesSon = lowerValuesSon;
    }

    public void setBiggerValuesSon(Node biggerValuesSon) {
        this.biggerValuesSon = biggerValuesSon;
    }

    public Node getLowerValuesSon() {
        return lowerValuesSon;
    }

    public Node getBiggerValuesSon() {
        return biggerValuesSon;
    }

    public ArrayList<Sample> getSamples() {
        return samples;
    }

    public DividerAttribute getDivider() {
        return divider;
    }

    public void setDivider(DividerAttribute divider) {
        this.divider = divider;
    }

    public void setSamples(ArrayList<Sample> samples) {
        this.samples = samples;
    }



    //returns avg median cost of house in set
    double evalaute() {
        double sum = 0.0;
        for(Sample sample : samples) {
            sum += sample.getResult();
        }
        return sum/samples.size();
    }
}
