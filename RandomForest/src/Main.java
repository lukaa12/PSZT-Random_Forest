
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        DataImporter importer = new DataImporter();

        ArrayList<Sample> samplesFromFile = importer.samplesFromFile("bostonHousingDataset.txt");

        double sum = 0.0;
        for(int i = 0; i < samplesFromFile.size(); i++)
            sum += samplesFromFile.get(i).getResult();

        double avg = sum/samplesFromFile.size();
        System.out.println(avg);

       DecisionTree tree = new DecisionTree(samplesFromFile);
      // for(Sample sample : samplesFromFile)
       //System.out.println(tree.getResultForSample(sample));
    }


}
