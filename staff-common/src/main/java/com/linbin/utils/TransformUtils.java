package com.linbin.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/4/23 12:23
 * @Description: 驼峰转化下划线
 */
public class TransformUtils {
    public static String trans(String str){

        List record =new ArrayList();
        for(int i=0;i<str.length();i++)
        {
            char tmp =str.charAt(i);

            if((tmp<='Z')&&(tmp>='A'))
            {
                record.add(i);//记录每个大写字母的7a686964616fe58685e5aeb931333335333763位置
            }

        }

        str= str.toLowerCase();
        char[] charofstr = str.toCharArray();
        String[] t =new String[record.size()];
        for(int i=0;i<record.size();i++)
        {
            t[i]="_"+charofstr[(int)record.get(i)];//加“_”
        }
        String result ="";
        int flag=0;
        for(int i=0;i<str.length();i++)
        {
            if((flag<record.size())&&(i==(int)record.get(flag))){
                result+=t[flag];
                flag++;
            }
            else
                result+=charofstr[i];
        }

        return result;
    }

    public static boolean isContain(String str){
        char[] c = str.toCharArray();
        for(int i=0;i<str.length();i++){
            if(c[i]>='A'&&c[i]<='Z'){
               return true;
            }
        }
        return false;
    }
}
