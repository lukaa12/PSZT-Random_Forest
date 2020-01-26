
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        DataImporter importer = new DataImporter();
        ArrayList<Sample> samplesFromFile = importer.samplesFromFile("bostonHousingDataset.txt");

        double error = 0.0;
        DecisionTree tree = new DecisionTree(samplesFromFile);
        for(Sample sample : samplesFromFile) {
            error += (sample.getResult() - tree.getResultForSample(sample))*(sample.getResult() - tree.getResultForSample(sample));
        }
        error = error/samplesFromFile.size();
        System.out.println(error);//checking MSE

    }

}
