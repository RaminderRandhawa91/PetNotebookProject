package com.raywenderlich.petnotebookproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.raywenderlich.alltherages.R;

public class PetListFragment extends Fragment {

  private int[] mImageResIds;
  private String[] mNames;
  private String[] mDescriptions;
  private String[] mUrls;
  private OnRageComicSelected mListener;

  public static PetListFragment newInstance() {
    return new PetListFragment();
  }

  public PetListFragment() {
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

    if (context instanceof OnRageComicSelected) {
      mListener = (OnRageComicSelected) context;
    } else {
      throw new ClassCastException(context.toString() + " must implement OnRageComicSelected.");
    }

    // Get rage face names and descriptions.

    final Resources resources = context.getResources();
    mNames = resources.getStringArray(R.array.names);
    mDescriptions = resources.getStringArray(R.array.descriptions);
    mUrls = resources.getStringArray(R.array.gender);

    // Get rage face images.
    final TypedArray typedArray = resources.obtainTypedArray(R.array.images);
    final int imageCount = mNames.length;
    mImageResIds = new int[imageCount];
    for (int i = 0; i < imageCount; i++) {
      mImageResIds[i] = typedArray.getResourceId(i, 0);
    }
    typedArray.recycle();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_pet_list, container, false);

    final Activity activity = getActivity();
    final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
    recyclerView.setAdapter(new RageComicAdapter(activity));

    return view;
  }

  class RageComicAdapter extends RecyclerView.Adapter<ViewHolder> {

    private LayoutInflater mLayoutInflater;

    public RageComicAdapter(Context context) {
      mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
      return new ViewHolder(mLayoutInflater
          .inflate(R.layout.recycler_item_rage_comic, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
      final int imageResId = mImageResIds[position];
      final String name = mNames[position];
      final String description = mDescriptions[position];
      final String url = mUrls[position];
      viewHolder.setData(imageResId, name);
      viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mListener.onRageComicSelected(imageResId, name, description, url);
        }
      });
    }

    @Override
    public int getItemCount() {
      return mNames.length;
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    // Views
    private ImageView mImageView;
    private TextView mNameTextView;
    FloatingActionButton fab;
    private ViewHolder(View itemView) {
      super(itemView);

      // Get references to image and name.
      mImageView = (ImageView) itemView.findViewById(R.id.comic_image);
      mNameTextView = (TextView) itemView.findViewById(R.id.name);
      fab = (FloatingActionButton) itemView.findViewById(R.id.fab);
    }

    private void setData(int imageResId, String name) {
      mImageView.setImageResource(imageResId);
      mNameTextView.setText(name);
    }
  }


  public interface OnRageComicSelected {
    void onRageComicSelected(int imageResId, String name, String description, String url);
  }
}
