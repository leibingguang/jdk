import java.util.Arrays;
import java.util.List;

public class FirstPrizeOfLottery {
    public static void main(String[] args) {
        int j = 0;
        Integer[] numArray = new Integer[]{0, 0, 0, 0, 0, 0};
        retry:
        while (j < numArray.length) {
            int k = (int) (1 + Math.random() * 32);
            for (int number : numArray) {
                if (number == k) continue retry;
            }
            numArray[j] = k;
            j++;
        }
        List<Integer> list = Arrays.asList(numArray);
        System.out.println("中奖号码：红球==" + list + " 蓝球==[" + (int) (1 + Math.random() * 15) + "]");
    }
}
