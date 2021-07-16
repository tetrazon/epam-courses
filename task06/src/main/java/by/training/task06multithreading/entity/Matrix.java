package by.training.task06multithreading.entity;

import by.training.task06multithreading.entity.exception.MatrixException;

import java.util.Arrays;

public class Matrix {
    private static final String BLANK = " ";

    private int[][] a;
    public Matrix(int[][] a) {
        this.a = a;
    }
    public Matrix(int n, int m) throws MatrixException {
        if (n < 1 || m < 1) {// check input
            throw new MatrixException();
        }
        a = new int[n][m];
    }
    public int getVerticalSize() {
        return a.length;
    }
    public int getHorizontalSize() {
        return a[0].length;
    }
    public int getElement(int i, int j) throws MatrixException {
        if (checkRange(i, j)) { // check i & j
            return a[i][j];
        } else {
            throw new MatrixException("wrong index");
        }
    }
    public void setElement(int i, int j, int value) throws MatrixException {
        if (checkRange(i, j)) {
            a[i][j] = value;
        } else {
            throw new MatrixException("wrong index");
        }
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nMatrix : " + a.length + "x"
                + a[0].length + "\n");
        for (int [ ] row : a) {
            for (int value : row) {
                s.append(value).append(BLANK);
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        for (int i = 0; i < ((Matrix) o).getHorizontalSize(); i++) {
            if(!Arrays.equals(a[i], matrix.a[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a);
    }

    private boolean checkRange(int i, int j) {
        // check matrix range
        return (i >= 0 && i < a.length && j >= 0 && j < a[0].length);
    }
}
