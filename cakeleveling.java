import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }
            long prefixSum = 0;
            long answer = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                prefixSum += a[i];
                long currentAverage = prefixSum / (i + 1);
                answer = Math.min(answer, currentAverage);
                System.out.print(answer + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
