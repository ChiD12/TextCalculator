public class op {

    private int prec = 0;
    private String oper = "";

    public op(String op){
        setPrec(op);
        oper = op;

    }

    private void setPrec(String op){
        switch (op){
            case "(": prec = 10;
                break;
            case ")": prec = 1;
                break;
            case "^": prec = 4;
                break;
            case "*": prec = 5;
                break;
            case "/": prec = 5;
                break;
            case "+": prec = 6;
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

        }
    }

    public int getPrec() {
        return prec;
    }

    public String getOper() {
        return oper;
    }
}
