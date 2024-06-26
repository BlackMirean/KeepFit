package mrkj.healthylife.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读取assets下的数据库到本地
 */
public class BringData {
    //数据库路径
    public static final String DATA_PATH = "/data/data/mrkj.healthylife/databases/";
    //文件名称
    public static final String DATA_NAME = "keepfit";
    public static void getDataFromAssets(Context context) throws IOException {
        Log.e("输入流的长度","执行了！");
        AssetManager assetManager = context.getAssets();
        InputStream is;
        is = assetManager.open("db/" + DATA_NAME);
        Log.e("文件", "data/" + DATA_NAME);
        is.available();
        Log.e("输入流的长度", "执行了！");
        Log.e("输入流的长度", is.available() + "字节");
        File file = new File(DATA_PATH);
        boolean have = file.exists();
        Log.e("是否有这个文件夹",have+"");
        if (have == false){
            //创建文件夹
            file.mkdir();
        }
        //文件夹存在时进行读写操作
        if (file.exists()){
            Log.e("文件夹创建成功",file.exists()+"");
            BufferedInputStream bIs = new BufferedInputStream(is);
            BufferedOutputStream bOs = new BufferedOutputStream(new FileOutputStream(new File(DATA_PATH+DATA_NAME)));

            byte[] buff = new byte[1024];
            int len;
            while ((len = bIs.read(buff))!=-1){
                bOs.write(buff,0,len);
                bOs.flush();
            }
            is.close();
            bIs.close();
            bOs.close();
        }

    }
}
