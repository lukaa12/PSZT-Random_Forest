import java.util.ArrayList;

public class DividerAttribute {

    private int attributeIndex;
    private double value;

    public DividerAttribute(ArrayList<Sample> samples) {
        int bestAttribute = -1;
        double divideValue = 0.0;
        double minError = 100000.0;

        for(int i = 0; i < samples.get(0).getAttributes().size(); i++) {
            for (int j = 0; j < samples.size(); j ++) {
                double divisionValue = samples.get(j).getAttributes().get(i);
                ArrayList<Sample> lowerValues = new ArrayList<>();
                ArrayList<Sample> biggerValues = new ArrayList<>();
                for(Sample sample : samples) {
                    if(sample.getAttributes().get(i) < divisionValue)
                        lowerValues.add(sample);
                    else
                        biggerValues.add(sample);
                }

                double error = calcMSE(lowerValues)/lowerValues.size() + calcMSE(biggerValues)/biggerValues.size();
                if(error < minError) {
                    minError = error;
                    bestAttribute = i;
                    divideValue = divisionValue;
                }
            }
        }
        this.setAttributeIndex(bestAttribute);
        this.setValue(divideValue);
    }

    private double calcMSE(ArrayList<Sample> samples) {
        double sum = 0.0;
        for(Sample sample : samples) {
            sum += sample.getResult();
        }
        double resultFromSamples = sum/samples.size();

        double mse = 0.0;

        for(Sample sample : samples) {
            double error = sample.getResult() - resultFromSamples;
            mse += error*error;
        }
        return mse;
    }

    public int getAttributeIndex() {
        return attributeIndex;
    }

    public void setAttributeIndex(int attributeIndex) {
        this.attributeIndex = attributeIndex;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
