package src.main.com.algorithms;

class Frogjmp {

    public static void main(String[] args) {
        {
            int result = solution(10, 85, 30);
            System.out.println(result);
        }

        {
            int result = solution(47, 1_000_000_000, 23);
            System.out.println(result);
        }
    }

    /** X, Y, D [1..1,000,000,000]; */
    public static int solution(int X, int Y, int D) {
        double dD = (double) D;
        return (int) Math.ceil((Y - X) / dD);
    }

}