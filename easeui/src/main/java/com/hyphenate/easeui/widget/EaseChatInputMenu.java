package com.hyphenate.easeui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hyphenate.easeui.R;
import com.hyphenate.easeui.domain.EaseEmojicon;
import com.hyphenate.easeui.domain.EaseEmojiconGroupEntity;
import com.hyphenate.easeui.widget.EaseChatExtendMenu.EaseChatExtendMenuItemClickListener;
import com.hyphenate.easeui.widget.EaseChatPrimaryMenuBase.EaseChatPrimaryMenuListener;
import com.hyphenate.easeui.widget.emojicon.EmojiconAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.rockerhieu.emojicon.EmojiconGridFragment;
import io.github.rockerhieu.emojicon.EmojiconGridView;
import io.github.rockerhieu.emojicon.emoji.Emojicon;
import io.github.rockerhieu.emojicon.emoji.People;

/**
 * input menu
 * <p>
 * including below component:
 * EaseChatPrimaryMenu: main menu bar, text input, send button
 * EaseChatExtendMenu: grid menu with image, file, location, etc
 * EaseEmojiconMenu: emoji icons
 */
public class EaseChatInputMenu extends LinearLayout {
    FrameLayout primaryMenuContainer;
    ViewPager emojiconMenuContainer;
    protected EaseChatPrimaryMenuBase chatPrimaryMenu;
    //    protected EaseEmojiconMenuBase emojiconMenu;
    protected EaseChatExtendMenu chatExtendMenu;
    protected FrameLayout chatExtendMenuContainer;
    protected LayoutInflater layoutInflater;

    private Handler handler = new Handler();
    private ChatInputMenuListener listener;
    private Context context;
    private boolean inited;

    public EaseChatInputMenu(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public EaseChatInputMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EaseChatInputMenu(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.ease_widget_chat_input_menu, this);
        primaryMenuContainer = (FrameLayout) findViewById(R.id.primary_menu_container);
        emojiconMenuContainer = (ViewPager) findViewById(R.id.emojicon_menu_container);
        chatExtendMenuContainer = (FrameLayout) findViewById(R.id.extend_menu_container);

        // extend menu
        chatExtendMenu = (EaseChatExtendMenu) findViewById(R.id.extend_menu);


    }

    /**
     * init view
     * <p>
     * This method should be called after registerExtendMenuItem(), setCustomEmojiconMenu() and setCustomPrimaryMenu().
     *
     * @param emojiconGroupList --will use default if null
     */
    @SuppressLint("InflateParams")
    public void init(List<EaseEmojiconGroupEntity> emojiconGroupList) {
        if (inited) {
            return;
        }
        // primary menu, use default if no customized one
        if (chatPrimaryMenu == null) {
            chatPrimaryMenu = (EaseChatPrimaryMenu) layoutInflater.inflate(R.layout.ease_layout_chat_primary_menu, null);
        }
        primaryMenuContainer.addView(chatPrimaryMenu);

        // emojicon menu, use default if no customized one
//        if (emojiconMenu == null) {
//            emojiconMenu = (EaseEmojiconMenu) layoutInflater.inflate(R.layout.ease_layout_emojicon_menu, null);
//            if (emojiconGroupList == null) {
//                emojiconGroupList = new ArrayList<EaseEmojiconGroupEntity>();
//                emojiconGroupList.add(new EaseEmojiconGroupEntity(R.drawable.ee_1, Arrays.asList(EaseDefaultEmojiconDatas.getData())));
//            }
//            ((EaseEmojiconMenu) emojiconMenu).init(emojiconGroupList);
//        }
//        emojiconMenuContainer.addView(emojiconMenu);
//        ViewGroup.LayoutParams layoutParams = emojiconMenuContainer.getLayoutParams();
//        int keyboardHeight = getContext().getSharedPreferences("config", Context.MODE_PRIVATE).getInt("key_board_height", 0);
//        layoutParams.height = keyboardHeight > 0 ? keyboardHeight : 700;
        Emojicon[] data = People.DATA;
        List<Emojicon> emojicons = Arrays.asList(data);
        int size = emojicons.size();
        int i = size / 47;
        final List<EmojiconGridView> emojiconGridViews = new ArrayList<>();
        for (int i1 = 0; i1 <= i; i1++) {
            ArrayList<Emojicon> emojicons1 = null;
            if (i1 != i) {
                emojicons1 = new ArrayList<>(emojicons.subList(i1 * 47, (i1 + 1) * 47));
            } else {
                emojicons1 = new ArrayList<>(emojicons.subList(i1 * 47, emojicons.size()));
            }
            emojicons1.add(emojicons1.size(), emojicons1.get(0));
            EmojiconGridView emojiconGridView = new EmojiconGridView(context);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            emojiconGridView.setLayoutParams(layoutParams);
            emojiconGridView.setNumColumns(8);
            EmojiconAdapter emojiconAdapter = new EmojiconAdapter(context, emojicons1);
            emojiconGridView.setAdapter(emojiconAdapter);
            emojiconGridView.setOnEmojiconClickedListener(new EmojiconGridFragment.OnEmojiconClickedListener() {
                @Override
                public void onEmojiconClicked(Emojicon emojicon) {
                    chatPrimaryMenu.getEditText().setText(chatPrimaryMenu.getEditText().getText() + emojicon.getEmoji());
                    chatPrimaryMenu.getEditText().setSelection(chatPrimaryMenu.getEditText().length());
                }
            });
            emojiconAdapter.setOnBackpaceClickListener(new EmojiconAdapter.OnBackspaceClickListener() {
                @Override
                public void onBackspaceClick() {
                    int action = KeyEvent.ACTION_DOWN;
                    int code = KeyEvent.KEYCODE_DEL;
                    KeyEvent event = new KeyEvent(action, code);
                    chatPrimaryMenu.getEditText().onKeyDown(KeyEvent.KEYCODE_DEL, event);
                }
            });
            emojiconGridViews.add(emojiconGridView);
        }

        emojiconMenuContainer.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return emojiconGridViews.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(emojiconGridViews.get(position));
                return emojiconGridViews.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
//                super.destroyItem(container, position, object);
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });


//        emojiconMenuContainer.setEmojiData(Emojicon.TYPE_PEOPLE, People.DATA, false);
//        emojiconMenuContainer.setOnEmojiconClickedListener();

        processChatMenu();
        chatExtendMenu.init();

        inited = true;
    }

    public void init() {
        init(null);
    }

    /**
     * set custom emojicon menu
     *
     * @param customEmojiconMenu
     */
//    public void setCustomEmojiconMenu(EaseEmojiconMenuBase customEmojiconMenu) {
//        this.emojiconMenu = customEmojiconMenu;
//    }

    /**
     * set custom primary menu
     *
     * @param customPrimaryMenu
     */
    public void setCustomPrimaryMenu(EaseChatPrimaryMenuBase customPrimaryMenu) {
        this.chatPrimaryMenu = customPrimaryMenu;
    }

    public EaseChatPrimaryMenuBase getPrimaryMenu() {
        return chatPrimaryMenu;
    }

    public EaseChatExtendMenu getExtendMenu() {
        return chatExtendMenu;
    }

//    public EaseEmojiconMenuBase getEmojiconMenu() {
//        return emojiconMenu;
//    }


    /**
     * register menu item
     *
     * @param name        item name
     * @param drawableRes background of item
     * @param itemId      id
     * @param listener    on click event of item
     */
    public void registerExtendMenuItem(String name, int drawableRes, int itemId,
                                       EaseChatExtendMenuItemClickListener listener) {
        chatExtendMenu.registerMenuItem(name, drawableRes, itemId, listener);
    }

    /**
     * register menu item
     * <p>
     * resource id of item name
     *
     * @param drawableRes background of item
     * @param itemId      id
     * @param listener    on click event of item
     */
    public void registerExtendMenuItem(int nameRes, int drawableRes, int itemId,
                                       EaseChatExtendMenuItemClickListener listener) {
        chatExtendMenu.registerMenuItem(nameRes, drawableRes, itemId, listener);
    }


    protected void processChatMenu() {
        // send message button
        chatPrimaryMenu.setChatPrimaryMenuListener(new EaseChatPrimaryMenuListener() {

            @Override
            public void onSendBtnClicked(String content) {
                if (listener != null)
                    listener.onSendMessage(content);
            }

            @Override
            public void onToggleVoiceBtnClicked() {
                hideExtendMenuContainer();
            }

            @Override
            public void onToggleExtendClicked() {
                toggleMore();
            }

            @Override
            public void onToggleEmojiconClicked() {
                toggleEmojicon();
            }

            @Override
            public void onEditTextClicked() {
                hideExtendMenuContainer();
            }


            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                if (listener != null) {
                    return listener.onPressToSpeakBtnTouch(v, event);
                }
                return false;
            }
        });

        // emojicon menu
//        emojiconMenu.setEmojiconMenuListener(new EaseEmojiconMenuListener() {
//
//            @Override
//            public void onExpressionClicked(EaseEmojicon emojicon) {
//                if (emojicon.getType() != EaseEmojicon.Type.BIG_EXPRESSION) {
//                    if (emojicon.getEmojiText() != null) {
//                        chatPrimaryMenu.onEmojiconInputEvent(EaseSmileUtils.getSmiledText(context, emojicon.getEmojiText()));
//                    }
//                } else {
//                    if (listener != null) {
//                        listener.onBigExpressionClicked(emojicon);
//                    }
//                }
//            }
//
//            @Override
//            public void onDeleteImageClicked() {
//                chatPrimaryMenu.onEmojiconDeleteEvent();
//            }
//        });

    }


    /**
     * insert text
     *
     * @param text
     */
    public void insertText(String text) {
        getPrimaryMenu().onTextInsert(text);
    }

    /**
     * show or hide extend menu
     */
    protected void toggleMore() {
        if (chatExtendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    chatExtendMenuContainer.setVisibility(View.VISIBLE);
                    chatExtendMenu.setVisibility(View.VISIBLE);
                    emojiconMenuContainer.setVisibility(View.GONE);
                }
            }, 50);
        } else {
            if (emojiconMenuContainer.getVisibility() == View.VISIBLE) {
                emojiconMenuContainer.setVisibility(View.GONE);
                chatExtendMenu.setVisibility(View.VISIBLE);
            } else {
                chatExtendMenuContainer.setVisibility(View.GONE);
            }
        }
    }

    /**
     * show or hide emojicon
     */
    protected void toggleEmojicon() {
        if (chatExtendMenuContainer.getVisibility() == View.GONE) {
            hideKeyboard();
            handler.postDelayed(new Runnable() {
                public void run() {
                    chatExtendMenuContainer.setVisibility(View.VISIBLE);
                    chatExtendMenu.setVisibility(View.GONE);
                    emojiconMenuContainer.setVisibility(View.VISIBLE);
                }
            }, 50);
        } else {
            if (emojiconMenuContainer.getVisibility() == View.VISIBLE) {
                chatExtendMenuContainer.setVisibility(View.GONE);
                emojiconMenuContainer.setVisibility(View.GONE);
            } else {
                chatExtendMenu.setVisibility(View.GONE);
                emojiconMenuContainer.setVisibility(View.VISIBLE);
            }

        }
    }

    /**
     * hide keyboard
     */
    private void hideKeyboard() {
        chatPrimaryMenu.hideKeyboard();
    }

    /**
     * hide extend menu
     */
    public void hideExtendMenuContainer() {
        chatExtendMenu.setVisibility(View.GONE);
//        emojiconMenu.setVisibility(View.GONE);
        chatExtendMenuContainer.setVisibility(View.GONE);
        chatPrimaryMenu.onExtendMenuContainerHide();
    }

    /**
     * when back key pressed
     *
     * @return false--extend menu is on, will hide it first
     * true --extend menu is off
     */
    public boolean onBackPressed() {
        if (chatExtendMenuContainer.getVisibility() == View.VISIBLE) {
            hideExtendMenuContainer();
            return false;
        } else {
            return true;
        }

    }


    public void setChatInputMenuListener(ChatInputMenuListener listener) {
        this.listener = listener;
    }

    public interface ChatInputMenuListener {
        /**
         * when send message button pressed
         *
         * @param content message content
         */
        void onSendMessage(String content);

        /**
         * when big icon pressed
         *
         * @param emojicon
         */
        void onBigExpressionClicked(EaseEmojicon emojicon);

        /**
         * when speak button is touched
         *
         * @param v
         * @param event
         * @return
         */
        boolean onPressToSpeakBtnTouch(View v, MotionEvent event);
    }

}
