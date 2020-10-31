package cn.shesshan.myapp.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadBitmapThread extends Thread implements Runnable{

    private Handler handler;
    private List<String> url;
    private List<Bitmap> pics;
    private static final String TAG="LoadBitmapThread";

    public LoadBitmapThread(Handler handler, List<String> url){
        this.handler=handler;
        this.url=url;
        this.pics=new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();
        HttpURLConnection con = null;
        Log.i(TAG,url.get(0));
        try {
            con=(HttpURLConnection)new URL(url.get(0)).openConnection();
            con.setReadTimeout(5000);
            con.connect();
            if(con.getResponseCode()==200){
                Log.i(TAG,"访问成功");
                InputStream in =con.getInputStream();
                Bitmap pic = BitmapFactory.decodeStream(in);
                if(pic!=null)
                    Log.i(TAG,"图片加载成功");
                else
                    Log.i(TAG,"图片加载失败");
                pics.add(pic);
                //in.close();
            }else
                Log.i(TAG,"访问失败");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (con != null) {
                con.disconnect();
            }else
                Log.i(TAG,"连接超时");
        }
        Message message=new Message();
        message.obj=pics;
        message.what=1;
        handler.sendMessageAtFrontOfQueue(message);
    }
}
