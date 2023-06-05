package Lambda;


import org.junit.Test;

import java.util.function.IntPredicate;

// 函数式编程思想  不关心对象 只关心数据
// 优化部分匿名内部类 不关心类名 不关心对象  只关心参数
// 接口且只有一个方法 抽象方法
// alt + enter
public class Lambda01 {

    public static void main(String[] args) {

        printNum(value->true);
    }


    public static void printNum(IntPredicate predicate){
    int[] arr = {1,2,3,4,5,6,7,8,9,10};
    for (int i :
            arr) {
        if (predicate.test(i)){
            System.out.println("i = " + i);
        }
    }
}
}
