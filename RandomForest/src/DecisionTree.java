import java.util.ArrayList;

public class DecisionTree {


    Node root;
    int maxDepth = 12;

    public DecisionTree(ArrayList<Sample> samples) {
        root = ID3(samples,maxDepth);
    }

    private Node ID3(ArrayList<Sample> samples, int depth) {
        //samples set is small or maxDepth reached - return leaf
        if(samples.size() <= 1 || depth == 0) {
            Node leaf = new Node();
            leaf.setSamples(samples);
            return leaf;
        }
        //find divider - its index in attribute array
        DividerAttribute divisionAttribute = new DividerAttribute(samples);
        ArrayList<Sample> lowerValues = new ArrayList<>();
        ArrayList<Sample> biggerValues = new ArrayList<>();

        for(Sample sample : samples) {
            if(sample.getAttributes().get(divisionAttribute.getAttributeIndex()) < divisionAttribute.getValue())
                lowerValues.add(sample);
            else
                biggerValues.add(sample);
        }
        Node node = new Node();
        node.setDivider(divisionAttribute);
        node.setSamples(samples);
        node.setLowerValuesSon(ID3(lowerValues,depth-1));
        node.setBiggerValuesSon(ID3(biggerValues,depth-1));
        return node;
    }

    public double getResultForSample(Sample sample) {
        return getEvaluation(sample,root);
    }

    private double getEvaluation(Sample sample, Node node) {
        if(node.getLowerValuesSon() == null && node.getBiggerValuesSon() == null) { //leaf
            return node.evalaute();
        }
        int dividerAttribute = node.getDivider().getAttributeIndex();
        double divisionValue = node.getDivider().getValue();
        if(sample.getAttributes().get(dividerAttribute) < divisionValue)
            return getEvaluation(sample,node.getLowerValuesSon());
        else
            return getEvaluation(sample,node.getBiggerValuesSon());
    }

}
