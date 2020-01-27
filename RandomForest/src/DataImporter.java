import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class DataImporter {

    ArrayList<Sample> samples = new ArrayList<>();
    ArrayList<Sample> train;
    ArrayList<Sample> test;

    public ArrayList<Sample> samplesFromFile(String dataPath) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader(dataPath));

        String line = reader.readLine();
        List<String> data;
        while (line != null) {
            ArrayList<Double> sampleData = new ArrayList<>();
            data = Arrays.asList(line.split(";"));
            for(int i = 0; i < 13; i ++) {
                sampleData.add(Double.valueOf(data.get(i)));
            }
            Sample sample = new Sample(sampleData,Double.valueOf(data.get(13)));
            samples.add(sample);

            line = reader.readLine();
        }
        return samples;
    }

    public ArrayList<Sample> getTrain(double proportions) {
        if (train==null){
            Collections.shuffle(samples);
            train = new ArrayList<>();
            test = new ArrayList<>();
            for(int i =0; i < samples.size(); ++i){
                if(i < samples.size()*proportions)
                    train.add(samples.get(i));
                else
                    test.add(samples.get(i));
            }
        }
        return train;
    }

    public  ArrayList<Sample> getTest() {
        if(test==null)
            getTrain(0.8);
        return test;
    }

}
