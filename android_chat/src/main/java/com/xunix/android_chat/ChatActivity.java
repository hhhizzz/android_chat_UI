package com.xunix.android_chat;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;
import com.vanniktech.emoji.listeners.OnEmojiPopupDismissListener;
import com.vanniktech.emoji.listeners.OnEmojiPopupShownListener;
import com.vanniktech.emoji.listeners.OnSoftKeyboardCloseListener;


import java.io.File;
import java.util.Date;
import java.util.LinkedList;

public class ChatActivity extends AppCompatActivity {
    private LinkedList<Bean> beans = null;
    private Integer ACTION_REQUEST_GALLERY=10290;
    private Integer ACTION_REQUEST_FILE=12342;

    /** message list */
    private ListView listView;
    /** EditText */
    private EmojiEditText edt;
    /** Send Button */
    private Button btnEnter;
    /** Face Button  */
    private ImageButton btnFace;
    /** More Button  */
    private ImageButton btnMore;
    /** emoji keyboard */
    private EmojiPopup emojiPopup;

    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //shut down the shadow
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setElevation(0);
        }




        setContentView(R.layout.activity_chat);
        initViewsMethod();
        onHandleMethod();

    }

    /**
     * deal with the listView
     * */
    private void initViewsMethod(){
        listView = (ListView) findViewById(R.id.lvMessages);
        edt = (EmojiEditText) findViewById(R.id.toolbox_et_message);
        btnEnter = (Button) findViewById(R.id.toolbox_btn_send);
        btnFace=(ImageButton) findViewById(R.id.toolbox_btn_face);
        btnMore=(ImageButton)findViewById(R.id.toolbox_btn_more);


        //setting the emojiPopup
        ViewGroup rootView = (ViewGroup) findViewById(R.id.chat_main_activity);
        emojiPopup = EmojiPopup.Builder.fromRootView(rootView).setOnEmojiPopupShownListener(new OnEmojiPopupShownListener() {
            @Override
            public void onEmojiPopupShown() {
                btnFace.setBackgroundResource(R.drawable.icon_keyboard_nor);
            }
        }).setOnEmojiPopupDismissListener(new OnEmojiPopupDismissListener() {
            @Override
            public void onEmojiPopupDismiss() {
                btnFace.setBackgroundResource(R.drawable.icon_face_normal);
            }
        }).setOnSoftKeyboardCloseListener(new OnSoftKeyboardCloseListener() {
            @Override
            public void onKeyboardClose() {
                emojiPopup.dismiss();
            }
        })
        .build(edt);

        //setting the more menu
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(ChatActivity.this, btnMore);
                DroppyMenuPopup droppyMenu = droppyBuilder.fromMenu(R.menu.more_menu)
                        .triggerOnAnchorClick(false)
                        .setOnClick(new DroppyClickCallbackInterface() {
                            @Override
                            public void call(View v, int id) {
                                if(id==R.id.one){
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("image/*");
                                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                                    Intent chooser = Intent.createChooser(intent, "Choose a Picture");
                                    startActivityForResult(chooser, ACTION_REQUEST_GALLERY);
                                }
                                if (id==R.id.two){
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                                    intent.setType("*/*");
                                    Intent chooser = Intent.createChooser(intent, "Choose a file");
                                    startActivityForResult(chooser, ACTION_REQUEST_FILE);
                                }
                            }
                        }).setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback(){
                            @Override
                            public void call() {
                                btnMore.setBackgroundResource(R.drawable.icon_more_nor);
                            }
                        }).setPopupAnimation(new DroppyFadeInAnimation())
                        .setXOffset(5)
                        .setYOffset(5)
                        .build();
                droppyMenu.show();
                btnMore.setBackgroundResource(R.drawable.icon_more_pressed);
            }

        });


        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v,
                                            ContextMenu.ContextMenuInfo menuInfo) {

                menu.setHeaderTitle("Notice");
                menu.setHeaderIcon(android.R.drawable.stat_notify_error);
                menu.add(0, 0, 1, "delete");
                menu.add(1, 1, 0, "cancel");

            }
        });
        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                emojiPopup.toggle();
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (emojiPopup != null && emojiPopup.isShowing()) {
            emojiPopup.dismiss();
        } else {
            super.onBackPressed();
        }
    }


    /**
     * deal with the messages
     **/
    public void onHandleMethod(){
        beans=new LinkedList<>();
        adapter = new CustomAdapter(this, beans);
        listView.setAdapter(adapter);


        //sending messages
        btnEnter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String txt = edt.getText().toString();
                if(txt.equals(""))
                    Toast.makeText(getApplicationContext(), R.string.empty_message, Toast.LENGTH_SHORT).show();
                else {
                    adapter.sendMessage(new Bean(txt, R.drawable.me, new Date(), 1));
                    edt.setText("");
                    listView.setSelection(beans.size() - 1);    //jump to bottom
                }
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(getApplicationContext(), R.string.delete_successful, Toast.LENGTH_SHORT).show();
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Bean bean = (Bean) adapter.getItem(info.position);
                beans.remove(bean);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==ACTION_REQUEST_GALLERY&&resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            adapter.sendMessage(new Bean("", R.drawable.me, new Date(),uri, adapter.MyMessage));
            listView.setSelection(beans.size() - 1);
        }
        else if(requestCode==ACTION_REQUEST_FILE&&resultCode==Activity.RESULT_OK){
            Uri uri=data.getData();
            File file=new File(uri.getPath());
            adapter.sendMessage(new Bean(file,adapter.MyMessage));
            listView.setSelection(beans.size() - 1);
        }
        btnMore.setBackgroundResource(R.drawable.icon_more_nor);
    }


    protected void sendMessage(Bean bean){
        adapter.sendMessage(bean);
    }
}
