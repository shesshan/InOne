package cn.shesshan.myapp.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class LoadBitmapThread extends Thread implements Runnable{

    private Handler handler;
    private List<String> url;
    private List<Bitmap> pics;

    public LoadBitmapThread(Handler handler, List<String> url){
        this.handler=handler;
        this.url=url;
    }

    @Override
    public void run() {
        super.run();
        try {
            Bitmap pic = BitmapFactory.decodeStream(new URL(url.get(0)).openStream());
            pics.add(pic);
        }catch (Exception e){
            e.printStackTrace();
        }
        Message message=new Message();
        message.obj=pics;
        message.what=1;
    }
}
