package com.bdone.asus.bdvoucher.dialogFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bdone.asus.bdvoucher.R;

/**
 * Created by ASUS on 3/25/2017.
 */

public class ViewCodeDialog extends DialogFragment {

    String offerCode;
    ImageView imageView;

    public ViewCodeDialog() {
    }

    public static ViewCodeDialog newInstance(String offerCode) {
        ViewCodeDialog fragment = new ViewCodeDialog();
        fragment.offerCode = offerCode;

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_view_code, null);
        imageView = (ImageView) view.findViewById(R.id.icon_copy);
        final TextView codeTextView = (TextView) view.findViewById(R.id.textViewCode);
        codeTextView.setText(String.valueOf(offerCode));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClipboard(getActivity(),offerCode);
                dismiss();
                Toast.makeText(getActivity(),"Voucher code is successfully copied",Toast.LENGTH_SHORT).show();
            }
        });

        alert.setView(view, 0, 0, 0, 0);


        return alert.create();
    }

    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }
}

