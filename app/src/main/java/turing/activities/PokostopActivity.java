package turing.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;
import turing.interfaces.APICallback;
import turing.pokomongo.R;
import turing.utils.UtilsAPI;
import turing.utils.UtilsCircleTransform;
import turing.utils.UtilsPreferences;

public class PokostopActivity extends AppCompatActivity implements APICallback {

    private TextView mEditTextName;
    private ImageView mPokostopPicture;


    private Handler mHandler;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokostop);

        mContext = this;
        mHandler = new Handler();

        mEditTextName = (TextView) findViewById(R.id.pokostop_name);
        mPokostopPicture = (ImageView) findViewById(R.id.img_pokostop);

        Button mPokostopButton = (Button) findViewById(R.id.btn_pokostop_item);


        Intent intent = getIntent();

        String jsonPokostops = intent.getStringExtra("pokostops");
        final String pokostopsId = intent.getStringExtra("idPokostop");

        mPokostopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getItemPokostop(pokostopsId);
            }
        });
        Log.d("Test", jsonPokostops);

        //Gson gson = new Gson();
        //Type listeType = new TypeToken<List<Pokostop>>(){}.getType();


        //List<Pokostop> pokostops = gson.fromJson(jsonPokostops, listeType);

        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(jsonPokostops);

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                    if (jsonobject.getString("_id").equals(pokostopsId)){
                        mEditTextName.setText(jsonobject.getString("name"));
                        Picasso.get().load(jsonobject.getString("picture"))
                                //.centerCrop()
                                .transform(new UtilsCircleTransform())
                                .into(mPokostopPicture);
                        break;
                    }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*for (Pokostop pokostop : pokostops){
            if(pokostop._id.equals(pokostopsId)){
                mEditTextName.setText(pokostop.name);
                Picasso.get().load(pokostop.picture)
                        .centerCrop()
                        .transform(new UtilsCircleTransform())
                        .into(mPokostopPicture);
                break;
            }
        }*/
    }

    @Override
    public void successCallback(Response response, int code) throws IOException {
        Log.d("----> Pokostop : ", response.body().string());
       // Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
        // todo: Ajouter les items au sac
    }

    @Override
    public void failCallback(Response response, int code) {
        Log.d("---> Erreur", "Je suis dans le Fail");
        try {
            Log.d("---> Body", response.body().string());
            Log.d("---> Code", String.valueOf(response.code()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getItemPokostop(String idPokostop){
        String url = String.format(UtilsAPI.URL_POKOSTOP, idPokostop);
        try{
            UtilsAPI.getInstance().post(
                    PokostopActivity.this,
                    url,
                    null,
                    UtilsPreferences.getPreferences(mContext).getString("token"),
                    0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
