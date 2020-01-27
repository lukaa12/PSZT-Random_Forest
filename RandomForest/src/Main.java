
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        DataImporter importer = new DataImporter();
        ArrayList<Sample> samplesFromFile = importer.samplesFromFile("bostonHousingDataset.txt");
        ArrayList<Sample> train = null, test = null;
        train = importer.getTrain(0.8);
        test = importer.getTest();
        System.out.println(train.size() + "  " + test.size());


        for(int d = 6; d < 7; ++d) {
            double error = 0.0;
            Forest decision = new Forest();
            decision.trainForest(train, test, d);
//        for(Sample sample: train) {
//            error += (sample.getResult() - decision.predict(sample))*(sample.getResult() - decision.predict(sample));
//        }
//        error = error/train.size();
//        System.out.println("Model performance on training set\nMSE: " + error);

            error = 0.0;
            for (Sample sample : test) {
                error += (sample.getResult() - decision.predict(sample)) * (sample.getResult() - decision.predict(sample));
            }
            error = error / test.size();
//            System.out.println(error);
        }


    }

}
