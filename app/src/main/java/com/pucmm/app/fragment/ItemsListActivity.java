package com.pucmm.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.pucmm.app.R;

public class ItemsListActivity extends FragmentActivity
        implements ItemsListFragment.OnItemSelectedListener {

    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        /*
        * 		FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
		if (fragmentItemDetail != null) {
			isTwoPane = true;
			ItemsListFragment fragmentItemsList =
					(ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemsList);
			fragmentItemsList.setActivateOnItemClick(true);
		}
        * */

        // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            isTwoPane = true;
            // Create an instance of ExampleFragment
            ItemsListFragment firstFragment = new ItemsListFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }


    @Override
    public void onItemSelected(Item item) {
        if (isTwoPane) { // single activity with list and detail
            // Replace frame layout with correct detail fragment
            ItemDetailFragment fragmentItem = ItemDetailFragment.newInstance(item);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragmentItem);
            ft.commit();
        } else { // separate activities
            // launch detail activity using intent
            Intent i = new Intent(this, ItemDetailActivity.class);
            i.putExtra("item", item);
            startActivity(i);
        }
//        // The user selected the headline of an article from the HeadlinesFragment
//
//        // Capture the article fragment from the activity layout
//        ArticleFragment articleFrag = (ArticleFragment)
//                getSupportFragmentManager().findFragmentById(R.id.article_fragment);
//
//        if (articleFrag != null) {
//            // If article frag is available, we're in two-pane layout...
//
//            // Call a method in the ArticleFragment to update its content
//            articleFrag.updateArticleView(position);
//
//        } else {
//            // If the frag is not available, we're in the one-pane layout and must swap frags...
//
//            // Create fragment and give it an argument for the selected article
//            ArticleFragment newFragment = new ArticleFragment();
//            Bundle args = new Bundle();
//            args.putInt(ArticleFragment.ARG_POSITION, position);
//            newFragment.setArguments(args);
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//            // Replace whatever is in the fragment_container view with this fragment,
//            // and add the transaction to the back stack so the user can navigate back
//            transaction.replace(R.id.fragment_container, newFragment);
//            transaction.addToBackStack(null);
//
//            // Commit the transaction
//            transaction.commit();
//        }
    }
}