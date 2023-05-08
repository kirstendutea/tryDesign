package com.example.trydesign;


import android.util.Log;
import static com.example.trydesign.ShopItemDetails.items;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopItem extends Fragment {
    //Fragment to display the details of a shop item


    private Button buyButton;
    private int itemDetailsID;
    private ShopItemDetails itemDetails;

    public ShopItem() {
        // Required empty public constructor
    }
    public static ShopItemDetails findItemById(int id) {
        //Returns the information for an item when given the id of the item
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).itemID == id){
                return items.get(i);
            }
        }
        return null;
    }

    public static ShopItem newInstance(int itemDetailsID) {
        //creating a new instance of the shopItem frag using itemDetailsID
        ShopItem fragment = new ShopItem();
        Bundle args = new Bundle();
        args.putInt("itemDetailsID", itemDetailsID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //getting the arguments set in newInstance() and using that to get the itemDetails using findItemById(itemDetailsID)
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            itemDetailsID = getArguments().getInt("itemDetailsID");
            itemDetails = ShopItemDetails.findItemById(itemDetailsID);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflates the fragment layout and sets the text and image views to the corresponding information from itemDetials
        View view = inflater.inflate(R.layout.fragment_shop_item, container, false);

        //need to set all the textviews from  itemDetails
        TextView itemName = view.findViewById(R.id.item_name);
        itemName.setText(itemDetails.itemName);

        ImageView itemImage = view.findViewById(R.id.item_image);
        itemImage.setImageResource(itemDetails.imageID);
        Log.d("itemname", "onCreateView: "+itemName);

        TextView itemPrice = view.findViewById(R.id.item_price);
        Log.d("itemPrice issue", "onCreateView: " + itemPrice);
        itemPrice.setText(String.valueOf(itemDetails.itemPrice));

        TextView itemDescription = view.findViewById(R.id.item_description);
        itemDescription.setText(itemDetails.itemDescription);

        TextView itemAmount = view.findViewById(R.id.item_amount);
        itemAmount.setText(String.valueOf(itemDetails.itemAmount));
        return view;
    }
}