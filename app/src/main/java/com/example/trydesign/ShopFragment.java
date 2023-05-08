package com.example.trydesign;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopFragment extends Fragment {
    //creates the layout for shop items to be displayed and adds the ShopItem fragments for however many shop items there are to the overall layout



    private View ShopItem1;
    private View ShopItem2;
    private View ShopItem3;
    private View ShopItem4;

    public static ArrayList<ShopItemDetails> items = new ArrayList<>();
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //initial shop items
        items.add(new ShopItemDetails("poppy",R.drawable.poppy, 100, "its a poppy",0 ));
        items.add(new ShopItemDetails("poppy1",R.drawable.poppy, 110, "its a poppy1",0 ));
        items.add(new ShopItemDetails("poppy2",R.drawable.poppy, 120, "its a poppy2",0 ));
        items.add(new ShopItemDetails("poppy3",R.drawable.poppy, 130, "its a poppy3",0 ));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        LinearLayout shopItemsLayout = view.findViewById(R.id.shopitemsgohere);

        for (int i = 0; i<ShopItemDetails.items.size();i++){
            // Create the ShopItem fragment and add this to the shopItemsLayout
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            ShopItem shopItemFragment1 = ShopItem.newInstance(ShopItemDetails.items.get(i).itemID);
            fragmentTransaction.add(R.id.shopitemsgohere, shopItemFragment1,"someTag");
            fragmentTransaction.commit();
        }


        return view;
    }

    public void onClick(View v){
        ShopItemDetails itemDetailsID = null;

    }

}
