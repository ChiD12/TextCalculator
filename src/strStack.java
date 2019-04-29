public class strStack {

    private int arraySize = 10;
    private String[] strStack;
    private int size = -1;

    public strStack(){
        strStack = new String[arraySize];
    }

    public void push(String current){
        if (arraySize == size){
            arraySize *= 2;
            String[] newArray = new String[arraySize];
            for (int i = 0; i < strStack.length; i++) {
                newArray[i] = strStack[i];
                strStack = newArray;
            }
        }

        size++;
        strStack[size] = current;

    }

    public String pop(){
        String current = strStack[size];
        strStack[size]=null;
        size--;
        return current;
    }

    public int getSize() {
        return size;
    }
    public String getTop(){
        if(size >=0){
            String current = strStack[size];
            return current;
        }
        return "";
    }
}

