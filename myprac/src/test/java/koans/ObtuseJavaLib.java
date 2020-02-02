package koans;

import scala.Option;

import java.util.List;
import java.util.ArrayList;


public class ObtuseJavaLib {

    public String getComplement(String word) {
        if (word.equals("Salt")) return "Pepper";
        if (word.equals("Fish")) return "Chips";
        if (word.equals("Horse")) return "Cart";
        return null;
    }

    public boolean isNull(Object ref) {
        if (ref == null) return true;
       // if (ref == None) return
        else return false;
    }

    public List<Integer> oneToTen() {
        ArrayList<Integer> oneToTen = new ArrayList(10);
        for (int i = 1; i < 10; i++ ){
            oneToTen.add(i);
        }
        return oneToTen;
    }


    public interface MathFunc {
        public int apply(int in);
    }

    public List<Integer> doMathFuncOnList(List<Integer> list, MathFunc func) {
        ArrayList<Integer> newList = new ArrayList(list.size());
        for (int i: list) {
            newList.add(func.apply(i));
        }
        return newList;
    }

    public boolean isNotDefined(String it) {
        Option<String> something;
        if (it.isEmpty()) something = Option.empty();
        else something = Option.apply(it);

        TryOption tryOption = new TryOption();
        return tryOption.notDefined(something);
    }

    public static class JavaMathObj implements MathNum {
        private final int num;
        public JavaMathObj(int n) {num = n;}

        public int add(int i){ return num + i;}

        public int sub(int i){ return num - i;}

        public int mul(int i){ return num * i;}

        public int div(int i){ return num / i;}
    }

    public JavaMathObj makeMathObj(int i){
        return new JavaMathObj(i);
    }
}
