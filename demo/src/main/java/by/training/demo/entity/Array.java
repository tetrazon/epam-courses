package by.training.demo.entity;

import java.util.Arrays;

public class Array<N extends Number> {

    private N[] values;

    public Array(N[] values) {
        this.values = values;
    }

    public N getValue(int index) {
        return values[index];
    }

    public void setValue(N value, int index) {
        values[index] = value;
    }

    public int getLength(){
        return values.length;
    }
    public void swap(int i, int j){
        N tmp;
        tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    /**
     Compares the two specified values. The sign
     * of the integer value returned is the same as that of the
     * integer that would be returned by the call:
     * <pre>
     *    new Double(d1).compareTo(new Double(d2))
     * </pre>
     * @param o1 first number
     * @param o2 second number
     * @param <N> N extends Number
     * @return the value {@code 0} if o1 is
     *          numerically equal to o2; a value less than
     *          {@code 0} if o1 is numerically less than
     *          o2; and a value greater than {@code 0}
     *          if o1 is numerically greater than
     *          o2.
     */
    public static<N extends Number> int compare(N o1, N o2) {
        return Double.compare(o1.doubleValue(), o2.doubleValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array array = (Array) o;
        int length = array.getLength();
        for (int i = 0; i < length; i++) {
            if (!array.getValue(i).equals(values[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
        return "Array{" + "values=" + Arrays.toString(values) + '}';
    }

}
