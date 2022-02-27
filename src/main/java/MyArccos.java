public class MyArccos {

    public static long factorial(int n){
        if (n < 0) return -1;
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static double calc(double x){
        if (Double.isNaN(x) || x < -1 || x > 1) return Double.NaN;

        double res = Math.PI / 2;
        for (int i = 0; i < 10; i++){
            res -= factorial(2 * i) / Math.pow(4, i) /
                    Math.pow(factorial(i), 2) / (2 * i + 1) * Math.pow(x, 2 * i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        double x = -1;
        System.out.println(Math.acos(x) - calc(x));
    }
}
