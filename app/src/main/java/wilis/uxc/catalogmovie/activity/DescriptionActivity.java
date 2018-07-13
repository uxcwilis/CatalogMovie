package wilis.uxc.catalogmovie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wilis.uxc.catalogmovie.R;


public class DescriptionActivity extends AppCompatActivity {
    TextView txtTitle,txtdate,txtDescrip,txtrating;
    ImageView bg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(getIntent().getStringExtra("title"));

        txtdate = (TextView) findViewById(R.id.tanggal);
        txtdate.setText(getIntent().getStringExtra("date"));

        txtDescrip = (TextView) findViewById(R.id.description);
        txtDescrip.setText(getIntent().getStringExtra("overview"));

//        txtrating = (TextView) findViewById(R.id.txtrating);
//        txtrating.setText(getIntent().getStringExtra("vote"));

        bg = (ImageView) findViewById(R.id.gambar);
        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + getIntent().getStringExtra("bg"))
                .resize(200, 300)
                .into(bg);
    }
}
