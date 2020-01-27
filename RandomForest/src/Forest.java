import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

class WeakModel{
    DecisionTree tree;
    ArrayList<Integer> attribs;
    double mse;

    WeakModel() {
        tree = null;
        attribs = new ArrayList<>();
        mse = 0.0;
    }
}

public class Forest {
    private ArrayList<WeakModel> ensemble;
    private ArrayList<Integer> attribs;

    public void trainForest(ArrayList<Sample> training){
        trainForest(training, (int)Math.sqrt(training.size()));
    }

    private void trainForest(ArrayList<Sample> training, int size){
        ensemble = new ArrayList<>();
        attribs = new ArrayList<>();
        for(int x = 0; x < training.get(0).getAttributes().size(); ++x)
            attribs.add(x);
        Collections.shuffle(attribs);

        for(int i = 0; i < size; ++i){
            WeakModel model = new WeakModel();

            Collections.shuffle(attribs);
            for(int j = 0; j < Math.sqrt(attribs.size()); ++j)
                model.attribs.add(attribs.get(j));

            ArrayList<Sample> set = new ArrayList<>();
            for(int j = 0; j < training.size(); ++j){
                ArrayList<Double> attr = new ArrayList<>();
                Sample tmp = training.get(ThreadLocalRandom.current().nextInt(0, training.size()));
                for(Integer atr : model.attribs)
                    attr.add(tmp.getAttributes().get(atr));
                set.add(new Sample(attr,tmp.getResult()));
            }

            model.tree = new DecisionTree(set,6);

            double modelMSE = 0.0;
            for(Sample s : training) {
                ArrayList<Double> attr = new ArrayList<>();
                for(Integer atr : model.attribs)
                    attr.add(s.getAttributes().get(atr));
                Sample prepared = new Sample(attr, s.getResult());
                modelMSE += (prepared.getResult() - model.tree.getResultForSample(prepared))* (prepared.getResult() - model.tree.getResultForSample(prepared));
            }
            modelMSE = modelMSE/training.size();
            model.mse = modelMSE;
            ensemble.add(model);
//            System.out.println("Weak model MSE: " + model.mse);
        }
//        System.out.println("Size: "+ ensemble.size());
    }

    public double predict(Sample toPredict) {
        ArrayList<Double> predictions = new ArrayList<>();
        double weightSum = 0.0;
        double maxMSE = -1.0;
        for(WeakModel m: ensemble){
            if(m.mse>maxMSE)
                maxMSE = m.mse;
        }
        for(WeakModel model : ensemble){
            ArrayList<Double> attr = new ArrayList<>();
            for(Integer atr : model.attribs)
                attr.add(toPredict.getAttributes().get(atr));
            Sample prepared = new Sample(attr, toPredict.getResult());
            predictions.add(model.tree.getResultForSample(prepared) * (maxMSE-model.mse));
            weightSum += maxMSE-model.mse;
        }
        double pred = 0.0;
        for(Double p : predictions){
            pred += p;
        }
        pred = pred/weightSum;
        return pred;
    }

}
