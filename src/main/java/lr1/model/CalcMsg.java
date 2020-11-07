package lr1.model;

import java.util.List;

public class CalcMsg {

    private double x;
    private double delta;

    private List<Double> results;

    public CalcMsg(List<Double> results) {
        this.results = results;
    }

    public CalcMsg(double x, double delta) {
        this.x = x;
        this.delta = delta;
    }

    public CalcMsg() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public List<Double> getResults() {
        return results;
    }

    public void setResults(List<Double> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "CalcMsg{" +
                "x=" + x +
                ", delta=" + delta +
                ", results=" + results +
                '}';
    }
}
