package com.adamin.popupbubble;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import com.adamin.popbubble.PopupBubble;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PopupBubble bubble;
    private MainAdapter adapter;
    private List<Domain> domains;
    private LinearLayoutManager linearLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        bubble= (PopupBubble) findViewById(R.id.popup_bubble);
        linearLayoutManager=new LinearLayoutManager(this);
        domains=new ArrayList<>();
        for(int i=0;i<100;i++){
            Domain domain=new Domain();
            domain.setName("adamin90"+"   "+i);
            domains.add(domain);
        }
        adapter=new MainAdapter(domains);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        bubble.setRecyclerView(recyclerView);
        bubble.setPopupBubbleListener(new PopupBubble.PopupBubbleClickListener() {
            @Override
            public void bubbleClicked(Context context) {
                     bubble.hide();
                Log.e("点击","点击");
            }
        });
  bubble.withAnimation(true);
        bubble.activate();
    }
}
