package utils;

public class Interval implements Comparable<Interval> {
    private int min;
    private int max;

    public Interval(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public boolean contains(int value){
        return value >= min && value < max;
    }

    @Override
    public String toString() {
        return "[" + min +
                ", " + max +
                ']';
    }

    @Override
    public int compareTo(Interval o) {
        return Integer.compare(min, o.min);
    }
}
