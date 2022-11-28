package utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**这是个工具类，一次性把所有请求参数注入到javaBean对象中
 * Map中放的就是请求参数
 * @author wbj
 * @date 2022/8/24 - 18:58
 */
public class webUtils {
    public  static <T> T copyParamToBean(Map value, T obj){
        //把所有请求参数都注入到obj对象中
        try {
            BeanUtils.populate(obj, value) ;
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return obj;

    }

    /**
     * 将String转为 int
     * @param strInt
     * @param defaultValue
     * @return
     */
    //字符串转整型很常见，但是需要try-catch，导致代码很长，所以抽取到这，变成一个工具方法
    public static int parseInt(String strInt,int defaultValue){

        try {
            if(strInt!=null){
                return Integer.parseInt(strInt);
            }

        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defaultValue;//如果strInt是空的，则采用传过来的默认值
    }


}
