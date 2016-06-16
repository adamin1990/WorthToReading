package com.adamin.popbubble;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class RecyclerViewListener extends RecyclerView.OnScrollListener {


    private PopupBubble popupBubble;


    public RecyclerViewListener(PopupBubble popupBubble) {

        this.popupBubble = popupBubble;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy >= 0) {
            // Scrolling up

                if (popupBubble != null)
                    popupBubble.setVisibility(View.VISIBLE);

        } else {
            // Scrolling down

                if (popupBubble != null)
                   popupBubble.setVisibility(View.INVISIBLE);

            if(isTopVisible(recyclerView))
            {
                popupBubble.deactivate();
            }
        }
    }

    private boolean isTopVisible(RecyclerView recyclerView) {

        int position = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if (position == 0)
            return true;
        else
            return false;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                //System.out.println("The RecyclerView is not scrolling");
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                //System.out.println("Scrolling now");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                //System.out.println("Scroll Settling");
                break;

        }

    }
}
