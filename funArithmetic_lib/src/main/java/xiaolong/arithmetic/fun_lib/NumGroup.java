package xiaolong.arithmetic.fun_lib;

/**
 * Created by xiaolong on 2020-05-16 01:01
 * email：xinxiaolong123@foxmail.com
 */
public class NumGroup {

    public static void main(String[] agr){
        int[] nums={1,2,3,4,5};

        NumGroup numGroup=new NumGroup();

        numGroup.group(nums);

    }

    public void group(int[] nums){

        for (int i=0;i<nums.length;i++){

            int j=0;

            while (j<nums.length){

                int num=nums[j];

                if(j==i){

                }else {
                    num=nums[i]*10+num;
                    System.out.println(num);
                }

                j++;
            }
        }

    }


}
