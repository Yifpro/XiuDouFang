package com.test.jcit.imagepicker.util;

import android.content.Context;

/**
 * @author WYF on 2018/8/22/022.
 */
public class ProviderUtil {

    public static String getFileProviderName(Context context){
        return context.getPackageName()+".provider";
    }
}
