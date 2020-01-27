import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Forest {
    private ArrayList<DecisionTree> ensemble;
    private ArrayList<Integer> attribs;

    public void trainForest(ArrayList<Sample> training){
        trainForest(training, 13);
    }

    private void trainForest(ArrayList<Sample> training, int size){
        ensemble = new ArrayList<>();
        attribs = new ArrayList<>();
        for(int x = 0; x < training.get(0).getAttributes().size(); ++x)
            attribs.add(x);
        Collections.shuffle(attribs);
        for(int i = 0; i < size; ++i){
            Collections.shuffle(training);
            ArrayList<Sample> set = new ArrayList<>();
            for(int j = 0; j < training.size()/size; ++j){
                ArrayList<Double> attr = new ArrayList<>();
                attr.add(training.get(j).getAttributes().get(i));
                Sample tmp = new Sample(attr,training.get(j).getResult());
                set.add(tmp);
            }
            DecisionTree tree = new DecisionTree(set,6);
            ensemble.add(tree);
        }
    }

    public double predict(Sample toPredict) {
        ArrayList<Double> predictions = new ArrayList<>();
        for(int i = 0; i < ensemble.size(); ++i){
            Sample prepared = new Sample(new ArrayList<>(Arrays.asList(toPredict.getAttributes().get(i))),toPredict.getResult());
            predictions.add(ensemble.get(i).getResultForSample(prepared));
        }
        double pred = 0.0;
        for(Double p : predictions){
            pred +=p;
        }
        pred = pred/predictions.size();
        return pred;
    }

}
