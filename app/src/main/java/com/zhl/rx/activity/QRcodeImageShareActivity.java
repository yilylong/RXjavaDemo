package com.zhl.rx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhl.cbdialog.CBDialogBuilder;
import com.zhl.rx.R;

public class QRcodeImageShareActivity extends AppCompatActivity {
    private View platformContanier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_image_share);
        TextView shareTV = findViewById(R.id.generate_qrshare);
        platformContanier = LayoutInflater.from(this).inflate(R.layout.qrcode_share_layout, null);
        shareTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQRcodeCard();
            }
        });
    }

    private void showQRcodeCard() {
        if(platformContanier.getParent()!=null){
            ((ViewGroup)platformContanier.getParent()).removeView(platformContanier);
        }
        ImageView share = platformContanier.findViewById(R.id.share_qrcode);
//        share.setImageURI(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/21EpaperFile/cache/images/1111.jpg")));
        new CBDialogBuilder(this, R.layout.custom_qrcode_share_dialog_layout, 1.0f)
                .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_BOTTOM)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .showCancelButton(false)
                .showIcon(false)
                .showConfirmButton(false)
                .setMessage(null)
                .setTitle(null)
                .setCancelable(true)
                .setView(platformContanier)
                .create().show();
    }
}
