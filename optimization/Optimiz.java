package optimization;

import java.lang.invoke.StringConcatFactory;

public class Optimiz {
    public static void main(String[] args) {
        String[] strs = {"a", "b", "c"};
        String str = "";
        for (int i = 0; i < strs.length; ++i) { //for 문 사용
            str += strs[i];
        }
        System.out.println(str);

        String str2 = strs[0] + strs[1] + strs[2]; // 일반 연산
        System.out.println(str2);

        String str3 = "S" + "t" + "r"; // String str3 = "Str";
        System.out.println(str3);
//        int result = 10 * 5;  // int result = 50으로 판단
//        System.out.println(result);
//        boolean x = true;
//        if (x) {
//            System.out.println("x is greater than 5");
//        } else {
//            System.out.println("x is not greater than 5");
//        }
//        int[] arr = new int[3];
//        for (int i = 0; i < 3; i++) {
//            arr[i] = i;
//        }
//        System.out.println(arr);
    }

}
