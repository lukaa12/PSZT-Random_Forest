import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataImporter {

    ArrayList<Sample> samples = new ArrayList<>();

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
}
