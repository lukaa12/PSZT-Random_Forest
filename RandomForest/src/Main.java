
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        DataImporter importer = new DataImporter();
        ArrayList<Sample> samplesFromFile = importer.samplesFromFile("bostonHousingDataset.txt");
        ArrayList<Sample> train = null, test = null;
        train = importer.getTrain(0.75);
        test = importer.getTest();
        System.out.println(train.size() + "  " + test.size());
        double error = 0.0;

//        DecisionTree tree = new DecisionTree(train,6);
//        for(Sample sample : train) {
//            error += (sample.getResult() - tree.getResultForSample(sample))*(sample.getResult() - tree.getResultForSample(sample));
//        }
//        error = error/train.size();
//        System.out.println(error);//checking MSE
//        double testError = 0.0;
//        for(Sample sample : test) {
//            testError += (sample.getResult() - tree.getResultForSample(sample))*(sample.getResult() - tree.getResultForSample(sample));
//        }
//        testError = testError/test.size();
//        System.out.println(testError);

        Forest decision = new Forest();
        decision.trainForest(train);
        for(Sample sample: train) {
            error += (sample.getResult() - decision.predict(sample))*(sample.getResult() - decision.predict(sample));
        }
        error = error/train.size();
        System.out.println("MSE on training set: " + error);

        error = 0.0;
        for(Sample sample: test) {
            error += (sample.getResult() - decision.predict(sample))*(sample.getResult() - decision.predict(sample));
        }
        error = error/test.size();
        System.out.println("MSE on testing set: " + error);


    }

}
