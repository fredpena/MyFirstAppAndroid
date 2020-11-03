package com.pucmm.app.fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.pucmm.app.R;

public class ItemDetailActivity extends FragmentActivity {
    private ItemDetailFragment fragmentItemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Item item = (Item) getIntent().getSerializableExtra("item");
        if (savedInstanceState == null) {
            fragmentItemDetail = ItemDetailFragment.newInstance(item);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragmentItemDetail);
            ft.commit();
        }
    }
}