package cn.shesshan.myapp.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import cn.shesshan.myapp.R;

public class SquareFragment extends Fragment {
    private static final String TAG="SquareFragment";
    private ImageView ivSquare;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_square,container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
    public void initView(){
        ivSquare=getView().findViewById(R.id.ivSquare);
        //Glide.with(getContext()).load("https://shesshan.cn/img/square_music.png").into(ivSquare);
        ivSquare.setBackgroundResource(R.mipmap.square_music);
        fitImage(getActivity(),ivSquare,1060.0f,5692.0f);
    }
    /**
     * 图片适配
     * @param imageView
     * @param picWidth
     * @param picHeight
     */
    public static void fitImage(Activity activity, ImageView imageView, float picWidth, float picHeight) {
        WindowManager wm = activity.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        float height = (float) width / picWidth * picHeight;
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = (int) height;
        imageView.setLayoutParams(layoutParams);
    }
}
