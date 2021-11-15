package com.heng.dreamstart.luckFrag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.heng.dreamstart.R;
import com.heng.dreamstart.startFrag.StarBean;

import java.util.List;

/**
 * @author : HengZhang
 * @date : 2021/11/14 17:40
 * 运势界面
 */

public class LuckFragment extends Fragment {
    GridView luckGv;
    List<StarBean.StarinfoBean> mDatas;
    private LuckBaseAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_luck, container, false);

        luckGv = view.findViewById(R.id.luckfrag_gv);

        //获取数据源
        Bundle bundle = getArguments();
        StarBean infoBean = (StarBean) bundle.getSerializable("info");
        mDatas = infoBean.getStarinfo();

        //创建适配器对象
        adapter = new LuckBaseAdapter(getContext(), mDatas);
        //设置适配器
        luckGv.setAdapter(adapter);

        //设置GridView每一项的监听事件
        setListener();

        return view;
    }

    /**
     * 创建监听事件
     */
    private void setListener() {
        luckGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarBean.StarinfoBean bean = mDatas.get(position);
                String name = bean.getName();
                Intent intent = new Intent(getContext(), LuckAnalysisActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
