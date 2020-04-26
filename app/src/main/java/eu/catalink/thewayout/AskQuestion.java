package eu.catalink.thewayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import eu.catalink.thewayout.chatbot.ChatMessage;
import eu.catalink.thewayout.chatbot.ChatMessageAdapter;

public class AskQuestion extends AppCompatActivity {

    private ListView mListView;
    private FloatingActionButton mButtonSend;
    private EditText mEditTextMessage;
    private ImageView mImageView;
    private ChatMessageAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.askquestion_activity);
        mListView = (ListView) findViewById(R.id.listView);
        mButtonSend = (FloatingActionButton) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);

        sendMessage("start");
//code for sending the message
        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditTextMessage.getText().toString();
                sendMessage(message);
                mEditTextMessage.setText("");
                mListView.setSelection(mAdapter.getCount() - 1);
            }
        });
    }
    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        mAdapter.add(chatMessage);
        //respond as Helloworld
        if (message.contains("start"))
            mimicOtherMessage("Hello I am Doris. I am here to assist you.");
        else if (message.contains("how"))
            mimicOtherMessage("Make a question. For example, ask for medical support.");
        else if (message.contains("medical"))
            mimicOtherMessage("If you have symptoms of covid-19, call 1420.\n" +
                    "If you need general information about covid-19, call 1450, 24hours a day.\n" +
                    "If you need general information about covid-19, call 1412, 08:00 till 20:00 d\n");
        else if (message.contains("psychological"))
            mimicOtherMessage("Please visit https://www.pio.gov.cy/coronavirus/pdf/1042020_i2.pdf for information.");
        else if (message.contains("confused"))
            mimicOtherMessage("Do not trust random internet pages. Read World’s Health Organisation Q&A.\n" +
                    "https://www.who.int/news-room/q-a-detail/q-a-coronaviruses\n");
        else if (message.contains("help"))
            mimicOtherMessage("You can join a group at your region/neighbourhood and help the isolated persons by providing them food/drugs/essentials.");
        else
            mimicOtherMessage("HelloWorld");
    }

    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);
    }

}
