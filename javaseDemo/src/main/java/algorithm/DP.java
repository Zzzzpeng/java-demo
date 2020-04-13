package algorithm;

public class DP {

    //备忘录模式
    public static int dp(int n){
        int[] p = new int[]{0,1,5,8,9,10,17,17,20,24,30};
        int[] r = new int[n + 1];
        return getDp(p, n, r);
    }
    public static int getDp(int[] p,int n,int[] r){
        if(n <= 0)
            return 0;
        //如果已经有结果了,直接用
        if(r[n] != 0)
            return r[n];
        int profit = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            profit = Math.max(profit, p[i] + getDp(p,n - i,r));
        }
        r[n] = profit;
        return profit;
    }


    //bottom_2_top
    public static int dp_bottom_2_top(int n){
        int[] p = new int[]{0,1,5,8,9,10,17,17,20,24,30};
        int[] r = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int profit = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                profit = Math.max(profit,p[j] + r[i - j]);
            }
            r[i] = profit;
        }
        return r[n];
    }

    /**
     * 单次买卖股票收益最大值---暴力双循环
     * @param prices
     * @return
     */
//    1 9 5 2 4
    public int maxProfit(int[] prices) {

        int profix = Integer.MIN_VALUE;
        for (int left = 0; left < prices.length - 1; left++) {
            for (int right = left + 1; right < prices.length; right++) {
                if(prices[right] - prices[left] > profix)
                    profix = prices[right] - prices[left];
            }
        }
        if(profix < 0)
            return 0;
        return profix;
    }

    /**
     * 单次买卖股票收益最大值---优先级队列
     * @param prices
     * @return
     */
//    1 9 5 2 4
    public static int maxProfit_priority(int[] prices) {
        if(prices == null || prices.length < 2)
            return 0;
        int profix = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length - 1; i++) {
            if(prices[i] < min)
                min = prices[i];
            if(prices[i] - min > profix)
                profix = prices[i] - min;
        }
        return profix;
    }


    public static void main(String[] args) {
        System.out.println(maxProfit_priority(new int[]{1,2}));
    }
}
