package com.hyphenate.easeui.widget.emojicon;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.hyphenate.easeui.R;

import java.util.List;

import io.github.rockerhieu.emojicon.EmojiconTextView;
import io.github.rockerhieu.emojicon.emoji.Emojicon;

/**
 * @author Morphine
 * @date 2018-04-08.
 */

public class EmojiconAdapter extends ArrayAdapter<Emojicon> {
    private boolean mUseSystemDefault = false;
    private OnBackspaceClickListener onBackspaceClickListener;


    public EmojiconAdapter(Context context, List<Emojicon> data) {
        super(context, R.layout.item_emojicon, data);
        mUseSystemDefault = false;
    }

    public EmojiconAdapter(Context context, List<Emojicon> data, boolean useSystemDefault) {
        super(context, R.layout.item_emojicon, data);
        mUseSystemDefault = useSystemDefault;
    }

    public EmojiconAdapter(Context context, Emojicon[] data) {
        super(context, R.layout.item_emojicon, data);
        mUseSystemDefault = false;
    }

    public EmojiconAdapter(Context context, Emojicon[] data, boolean useSystemDefault) {
        super(context, R.layout.item_emojicon, data);
        mUseSystemDefault = useSystemDefault;
    }

    public void setOnBackpaceClickListener(OnBackspaceClickListener onBackspaceClickListener) {
        this.onBackspaceClickListener = onBackspaceClickListener;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = View.inflate(getContext(), R.layout.item_emojicon, null);
            ViewHolder holder = new ViewHolder();
            holder.icon = (EmojiconTextView) v.findViewById(R.id.emojicon_icon);
            holder.ibBackspace = v.findViewById(R.id.ib_backspace);
            holder.icon.setUseSystemDefault(mUseSystemDefault);
            v.setTag(holder);
        }
        ViewHolder holder = (ViewHolder) v.getTag();
        if (position == getCount() - 1) {
            holder.icon.setVisibility(View.GONE);
            holder.ibBackspace.setVisibility(View.VISIBLE);
        } else {
            holder.icon.setVisibility(View.VISIBLE);
            holder.ibBackspace.setVisibility(View.GONE);
            Emojicon emoji = getItem(position);
            holder.icon.setText(emoji.getEmoji());
        }
        holder.ibBackspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackspaceClickListener.onBackspaceClick();
            }
        });
        return v;
    }

    public interface OnBackspaceClickListener {
        void onBackspaceClick();
    }


    static class ViewHolder {
        EmojiconTextView icon;
        ImageButton ibBackspace;
    }
}
