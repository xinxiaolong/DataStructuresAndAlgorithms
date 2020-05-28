package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020-04-21.
 * email：xinxiaolong123@foxmail.com
 *
 * 题目：一天（24小时）时针与分针又重合了多少次？时针，分针，秒针之内重合多少次？
 */
public class ClockPointer {


    public static void main(String[] agrs) {

        ClockPointer clock = new ClockPointer();

        int overlapping = clock.degreeDifference(0,24);

        System.out.println("重合次数" + overlapping);

        overlapping = clock.degreeDifference2(0,24);

        System.out.println("重合次数" + overlapping);

    }

    /**
     * 时针和分针重合的次数
     *
     * 思路：如果从0点开始思考，0点整时时针和分针是刚好重合的。
     *      * 当到1点整时，分针在12，时针在1上，夹角是30度
     *      * 当到2点整时，分针在12，时针在2上，夹角是60度
     *      * 当到3点整时，分针在12，时针在3上，夹角是90度
     *      * 从上面观察得出一个规律：如果是n点，当0<n<12时。分针和时针的夹角为n*30度。
     *      *                               当n>=12时。分针和时针的夹角为(n%12)*30度。
     *      *
     *      * 分针比时针跑的快：一分钟分针走6度，时针走0.5度。每分钟分针比时针多跑5.5度。当距离差为0时两针重合。
     *      * 而追赶上的用m时就是n点m分。但是m小于60，60分是一圈，在一圈内没有最赶上，则要从下个小时开始算。
     *
     * @param startTime  开始时间，只支持0-23点范围
     * @param endTime    结束时间，只支持0-24点范围
     * @return 时针和分针的重合次数。
     *
     *
     *
     *
     *
     */
    public int degreeDifference(int startTime, int endTime) {

        //重合的次数
        int overlapping = 0;

        //这里是按每小时进行循环计算的，因为分针走一圈需要一个小时。
        for (int i = startTime; i <= endTime; i++) {
            //计算出来n点开始，分针追上时针的时间。单位：minutes
            double d_minutes = ((i%12) * 30) / 5.5;
            //这里必须再一个小时内追上算有效。并且小于60。也就是说在59.999999分钟内追上才有效。当为60的时候也就是n+1点，应该算下一个圈的起点。
            if (d_minutes < 60) {
                overlapping++;
                System.out.println("重合发生在" + i + "点" + d_minutes + "分");
            }
        }
        return overlapping;
    }

    /**
     * 这里解答时针，分针，秒针的重合计算。
     * 三针重合，必须建立在分针和时针重合的基础上。
     *
     * 思路：当时针和分针重合时的角度和当时秒针的角度一致时，判定为三针重合。
     * 在前面的代码基础上加上对秒针角度的判断，来判定三针重合。
     *
     * @param startTime 开始时间，只支持0-23点范围
     * @param endTime   结束时间，只支持0-24点范围
     * @return 时针和分针和秒针的重合次数。
     */
    public int degreeDifference2(int startTime, int endTime) {

        //重合的次数
        int overlapping = 0;

        //这里是按每小时进行循环计算的，因为分针走一圈需要一个小时。
        for (int i = startTime; i <= endTime; i++) {
            //计算出来n点开始，分针追上时针的时间。单位：minutes
            double d_minutes = ((i % 12) * 30) / 5.5;

            //这里必须再一个小时内追上算有效。并且小于60。也就是说在59.999999分钟内追上才有效。当为60的时候也就是n+1点，应该算下一个圈的起点。
            if (d_minutes < 60) {

                int minutes = (int) d_minutes;
                double seconds = (d_minutes - minutes) * 60;
                double degreeMinutes = (d_minutes / 60) * 360;
                double degreeSeconds = (seconds / 60) * 360;

                if (degreeMinutes == degreeSeconds) {
                    overlapping++;
                    System.out.println("重合发生在" + i + "点" + (int) minutes + "分" + seconds + "秒" + "\n        分针角度：" + degreeMinutes + "  秒针角度：" + degreeSeconds);
                }
            }
        }
        return overlapping;
    }



}
