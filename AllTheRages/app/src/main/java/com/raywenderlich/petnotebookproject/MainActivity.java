package com.raywenderlich.petnotebookproject;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.raywenderlich.alltherages.R;

public class MainActivity extends AppCompatActivity
    implements PetListFragment.OnRageComicSelected {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
      android.support.v7.app.ActionBar actionBar = getSupportActionBar();
      actionBar.setLogo(R.drawable.app_text_logo);
      actionBar.setTitle("OMEE'S PET");
      actionBar.setDisplayUseLogoEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);



    if (savedInstanceState == null) {
      getSupportFragmentManager()
          .beginTransaction()
                  .replace(R.id.root_layout, HomeFragment.newInstance(),"rage")
       //   .replace(R.id.root_layout, PetListFragment.newInstance(), "rageComicList")
          .commit();
    }
  }

  @Override
  public void onRageComicSelected(int imageResId, String name, String description, String url) {
    final PetDetailsFragment detailsFragment =
        PetDetailsFragment.newInstance(imageResId, name, description, url);
         getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.root_layout, detailsFragment, "rageComicDetails")
        .addToBackStack(null)
        .commit();
  }
}
