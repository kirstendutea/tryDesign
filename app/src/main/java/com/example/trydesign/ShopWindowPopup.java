package com.example.trydesign;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopWindowPopup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopWindowPopup extends DialogFragment {
    //popup window for displaying shop item details, increments the number owned when an item is bought


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "id";

    // TODO: Rename and change types of parameters
    private int idint;
    private ShopItemDetails shopItemDetails;

    public ShopWindowPopup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShopWindowPopup.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopWindowPopup newInstance(int idint) {
        ShopWindowPopup fragment = new ShopWindowPopup();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, idint);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //initialising the shopItemDetails variabke
        super.onCreate(savedInstanceState);
        ShopItemDetails thisShopItem = ShopItem.findItemById(idint);
        ShopItemDetails shopItemDetails = thisShopItem;
        if (getArguments() != null) {
            idint = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_window_popup, container, false);
        ShopItemDetails aaAthing = ShopItemDetails.findItemById(idint);

        int itemDetailsID = getArguments().getInt("itemDetailsID");

        //setting the fields for said item
        ImageView itemImage = getView().findViewById(R.id.item_image);
        int imgSrcint = shopItemDetails.imageID;
        itemImage.setImageResource(imgSrcint);

        TextView itemName = getView().findViewById(R.id.item_name);
        itemName.setText((CharSequence) shopItemDetails.itemName);

        TextView itemDescription = getView().findViewById(R.id.item_description);
        itemDescription.setText((CharSequence) shopItemDetails.itemDescription);

        TextView itemPrice = getView().findViewById(R.id.item_price);
        itemPrice.setText(String.valueOf(shopItemDetails.itemPrice));

        TextView itemAmount = getView().findViewById(R.id.item_amount);
        itemAmount.setText(String.valueOf(shopItemDetails.itemAmount));


        Button button = view.findViewById(R.id.popup_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onclick listener for the buy button: when clicked the amount owned increases by one
                int boughtAmount = shopItemDetails.getAmount() +1;
                shopItemDetails.setAmount(boughtAmount);
                TextView itemAmount = getView().findViewById(R.id.item_amount);
                itemAmount.setText(String.valueOf(shopItemDetails.itemAmount));
            }
        });

        return view;
    }
}