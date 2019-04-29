public class doubleStack {

    private int arraySize = 10;
    private double[] doubleStack;
    private int size = -1;

    public doubleStack(){
        doubleStack = new double[arraySize];
    }

    public void push(double current){
        if (arraySize == size){
            arraySize *= 2;
            double[] newArray = new double[arraySize];
            for (int i = 0; i < doubleStack.length; i++) {
                newArray[i] = doubleStack[i];
                doubleStack = newArray;
            }
        }

        size++;
        doubleStack[size] = current;

    }

    public double pop(){
        double current = doubleStack[size];
        doubleStack[size] = 0;
        size--;
        return current;
    }

    public int getSize() {
        return size;
    }
    public double getTop(){
        if(size >=0){
            double current = doubleStack[size];
            return current;
        }
        return -1;
    }
}
