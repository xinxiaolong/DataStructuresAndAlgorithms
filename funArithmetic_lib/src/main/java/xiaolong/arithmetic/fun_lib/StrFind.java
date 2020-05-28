package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020-05-06 17:21
 * email：xinxiaolong123@foxmail.com
 */
public class StrFind {


    public static void main(String[] agr) {


        String haystack = "mississippi";

        String needle = "issip";

        StrFind strFind = new StrFind();

        int i = strFind.find1(haystack, needle);

        System.out.print(i);
    }


    /**
     * 双指针从后搜索。
     *
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int find(String haystack, String needle) {

        if(needle.equals("")){
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {

            //首字母匹配上了
            if (haystack.charAt(i) == needle.charAt(0)) {

                int endIndex=i+needle.length()-1;
                int nEndIndex=needle.length()-1;

                //首字母位置固定，开始从后到前匹配

                while (haystack.length()>endIndex&&nEndIndex>=0){

                     if(haystack.charAt(endIndex)==needle.charAt(nEndIndex)){
                         endIndex--;
                         nEndIndex--;
                     }else {
                         break;
                     }

                     if(nEndIndex<0){
                         return i;
                     }
                }
            }
        }
        return -1;
    }



    /**
     *
     * on 2020-05-06 18:18
     *
     * 直接用字符串进行对比，比双指针直接
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int find1(String haystack, String needle) {

        if(needle.equals("")){
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {

            //首字母匹配上了
            if (haystack.charAt(i) == needle.charAt(0)) {

                //得到和目标needle同长的范围，截取字符串。直接进行对比
                int endIndex=i+needle.length()-1;

                if(endIndex<haystack.length()&&haystack.substring(i,endIndex+1).equals(needle)){
                    return i;
                }
            }
        }
        return -1;
    }
}

