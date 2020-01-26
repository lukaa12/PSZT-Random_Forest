
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        DataImporter importer = new DataImporter();

        ArrayList<Sample> samplesFromFile = importer.samplesFromFile("bostonHousingDataset.txt");

//        double sum = 0.0;
//        for (int i = 0; i < samplesFromFile.size(); i++)
//            sum += samplesFromFile.get(i).getResult();

        //double avg = sum / samplesFromFile.size();
        //System.out.println(avg);
        double error = 0.0;
        DecisionTree tree = new DecisionTree(samplesFromFile);
        for(Sample sample : samplesFromFile) {
            error += (sample.getResult() - tree.getResultForSample(sample))*(sample.getResult() - tree.getResultForSample(sample));
        }
        error = error/samplesFromFile.size();
        System.out.println(error);

        //  System.out.println(sample.getResult());

//        System.out.println("Nodes total: "+Node.nodes.size());
//        for (Node n : Node.nodes) {
//            System.out.println("----------node--------------");
//            if(n.divider!=null)
//                System.out.println(n.divider.getAttributeIndex() + ": " + n.divider.getValue());
//            else
//                System.out.println("leaf");
//            System.out.println(n.samples.size());
//        }
    }


}
