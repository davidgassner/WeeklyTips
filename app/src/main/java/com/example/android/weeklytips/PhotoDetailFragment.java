package com.example.android.weeklytips;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.weeklytips.data.DataItemHelper;

import java.io.IOException;
import java.io.InputStream;

/**
 * A fragment representing a single Photo detail screen.
 * This fragment is either contained in a {@link PhotoListActivity}
 * in two-pane mode (on tablets) or a {@link PhotoDetailActivity}
 * on handsets.
 */
public class PhotoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DataItemHelper.DataItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PhotoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DataItemHelper.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            String imageFile = mItem.content + ".webp";

            // get input stream
            InputStream inputStream = null;
            try {
                inputStream = getContext().getAssets().open(imageFile);
                Drawable d = Drawable.createFromStream(inputStream, null);
                imageView.setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return rootView;
    }
}
