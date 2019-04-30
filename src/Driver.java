// Daniel Rosenfeld
// Text Based Calculator
//Write Equation to log.txt on the last line ending in $ then run program

import java.io.*;

public class Driver {
    static strStack opStk;
    static doubleStack valStk;
    static char currentString;
    static boolean endIsBool=false;
    static boolean afterOpBool;
    public static void main(String[] args){

        String someString = "(107+92)*2$";

        Boolean endofText = false;
        BufferedReader br= null;
        PrintWriter PW = null;
        File log = new File("log.txt");


        opStk = new strStack();
        valStk = new doubleStack();






        try{

            br = new BufferedReader(new FileReader(log));
            while(!endofText){
                String readerString = br.readLine();

                if(!(readerString == null)){
                    someString = readerString;
                }

                if(readerString == null){
                    endofText = true;
                }
            }
            br.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(someString);





        boolean negative = false;
        for (int i = 0; i < someString.length(); i++) {
            currentString = someString.charAt(i);
            String nextChar = "";
            String prevChar = "";

            if(i<someString.length()-1)
                nextChar = ""+someString.charAt(i+1);
            if(i>0)
                prevChar = ""+someString.charAt(i-1);


            double ascii = (double)currentString-48;

            if(ascii >=0 && ascii< 10 ){ // if a character is negative and has factorial

                String extraDigit = ""+someString.charAt(i);
                Boolean nextIsValue = false;
                int counter = 0;

                System.out.println((double)someString.charAt(i+counter));
                while((double)someString.charAt(i+counter+1) -48>= 0 && (double)someString.charAt(i+counter+1) -48< 10 ){
                    System.out.println("innnnnn");
                    extraDigit += someString.charAt(i+counter+1);
                    System.out.println(extraDigit);
                    counter++;
                    nextIsValue = true;
                }
                System.out.println("counter is: " + counter);


                if(nextIsValue) {
                    ascii = Double.parseDouble(extraDigit);
                    i += counter;
                }


                if(nextChar.equals("!")){
                    if(negative ){
                        ascii = factorial(ascii);
                        valStk.push(-1*ascii);
                        negative = false;
                    }
                    ascii = factorial(ascii); // if only factorial
                    valStk.push(ascii);
                }
                else if(negative ){
                    valStk.push(-1*ascii); // if only negative
                    negative = false;
                }
                else{
                valStk.push(ascii);
                }
                System.out.println("this char went into val and is " + valStk.getTop());
            }
            else{
                if(nextChar.equals("=") || nextChar.equals("!")){
                    repeatOP();
                    opStk.push(currentString + nextChar);  //if there are two characters meant to  be one operation
                    i++;
                }
                else{
                    repeatOP();
                    opStk.push(""+currentString);
                }
                if(nextChar.equals("-")){
                    negative = true;
                    i++;
                }
                System.out.println("this char went into op and is " + opStk.getTop());

            }

        }
        repeatOP();
        System.out.println(valStk.getTop());
        System.out.println("val size is: " + valStk.getSize());
        System.out.println("op size is: " + opStk.getSize());


        try{
            PW = new PrintWriter(new FileWriter(log, true));
            //PW.println();
            if(endIsBool){
                PW.println("The solution is: " + afterOpBool);
            }else{
                PW.println("The solution is: " + valStk.getTop());
            }

        }catch(IOException e){
            System.exit(0);
        }finally {   //writers are closed in the finally  block, so notepads only are written to at the end of the loop, if the program is ended early, nothing will write to the notepads.
            PW.close();
        }

    }

    private static boolean prec(String x, String y){
        int precX = setPrec(x);
        int precY = setPrec(y);

        System.out.println("x is: " + x + " prec x is: " + precX + " y is: " + y + " precY is: " + precY);

        return (precX <= precY);

    }
    private static int setPrec(String op){
        int prec = 0;
        switch (op){
            case "(": prec = 10;
                break;
            case ")": prec = 1;
                break;
            /*case "!": prec = 2;
                break;*/
            case "^": prec = 4;
                break;
            case "*": prec = 5;
                break;
            case "/": prec = 5;
                break;
            case "+": prec = 6;
                break;
            case "-": prec = 6;
                break;
            case ">": prec = 7;
                break;
            case "<": prec = 7;
                break;
            case "<=": prec = 7;
                break;
            case ">=": prec = 7;
                break;
            case "==": prec = 8;
                break;
            case "!=": prec = 8;
                break;
            case "$": prec = 9;
                break;

        }
        return prec;
    }

    public static void repeatOP(){
        while((valStk.getSize()>=1 && prec(opStk.getTop(),""+currentString) && setPrec(opStk.getTop()) != 0) || setPrec(opStk.getTop()) == 1){
            System.out.println("got in");
            doOP();
        }

    }
    public static void doOP(){
        double x = valStk.pop();
        double y = valStk.pop();
        String op = opStk.pop();
        double afterOp = 0;


        System.out.println("val 1: " +x + " val 2:  " + y + " op is: " + op);

        switch (op){

            case "(": return;
            case ")":
                while(!(opStk.getTop().equals("("))){
                    valStk.push(y);
                    valStk.push(x);
                    System.out.println("recursion");
                    doOP();

                }
                opStk.pop();
                return;



                /*case "!": afterOp = factorial(y);
                          valStk.push(x);
                          break;*/
            case "^": afterOp = Math.pow(y,x);
                break;
            case "*": afterOp = x*y;
                break;
            case "/": afterOp = y/x;
                break;
            case "+": afterOp = x+y;
                break;
            case "-":afterOp = y-x;
                break;
            case ">": afterOpBool = (y>x);
                break;
            case "<": afterOpBool = (y<x);
                break;
            case "<=": afterOpBool = (y<=x);
                break;
            case ">=": afterOpBool = (y>=x);
                break;
            case "==": afterOpBool = (y==x);
                break;
            case "!=": afterOpBool = (y!=x);
                break;

        }
        System.out.println(afterOp + " op is: " + op);
        if(afterOp!=0){
            valStk.push(afterOp);
            System.out.println("pushing: " + afterOp);
        }
        else{
            endIsBool = true;
            System.out.println(afterOpBool);
            valStk.push(-1);
        }
    }

    public static double factorial(double y){
        double current= y;
        for (int i = 1; i < y; i++) {
            current *= (y-i);
        }
        if (y == 0){
            current = 1;
        }
        return current;
    }


}
