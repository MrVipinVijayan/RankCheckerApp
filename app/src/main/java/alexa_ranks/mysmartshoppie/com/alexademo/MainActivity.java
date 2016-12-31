package alexa_ranks.mysmartshoppie.com.alexademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ldoublem.loadingviewlib.LVCircularCD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String queueTAG = "Req1";
    private LVCircularCD lvCircularCD;
    private TableLayout t1;
    private EditText siteName;
    private Button btnMoreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.DEBUG = false;

        siteName = (EditText) findViewById(R.id.edtSiteName);

        t1 = (TableLayout) findViewById(R.id.t1);
        btnMoreInfo = (Button) findViewById(R.id.btnMoreInfo);
        lvCircularCD = (LVCircularCD) findViewById(R.id.pbLoading);
        lvCircularCD.setViewColor(ContextCompat.getColor(this, R.color.colorPrimary));

        lvCircularCD.setVisibility(View.GONE);
        btnMoreInfo.setVisibility(View.GONE);
        t1.setVisibility(View.GONE);

        findViewById(R.id.go).setOnClickListener(this);
        btnMoreInfo.setOnClickListener(this);

        siteName.append(getString(R.string.default_site));
        Utils.addAppBrainBanner(this, (RelativeLayout) findViewById(R.id.rel_adView));
    }

    private void getRank(String url) {

        lvCircularCD.setVisibility(View.VISIBLE);
        lvCircularCD.startAnim();
        t1.setVisibility(View.GONE);

        // Get a RequestQueue
        RequestQueue mRequestQueue = MySingleton.getInstance(this.getApplicationContext()).
                getRequestQueue();

        mRequestQueue.cancelAll(queueTAG);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Logger.logInfo(TAG, "response : " + response);
                        displayData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.logError(TAG, "Error :" + error.getLocalizedMessage());
                lvCircularCD.stopAnim();
                lvCircularCD.setVisibility(View.GONE);
                Utils.showMessage("ERROR OCCURED", findViewById(R.id.activity_main));
            }
        });

        stringRequest.setTag(queueTAG);

        // Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);
    }

    void displayData(String response) {
        siteName.setBackgroundResource(R.drawable.edt_bg);
        Rank rank = new Parser().parse(response);
        if (null != rank) {
            String site = siteName.getText().toString().trim();
            TextView siteName = (TextView) findViewById(R.id.siteName);
            siteName.setText(site);
            TextView globalRank = (TextView) findViewById(R.id.globalRank);
            globalRank.setText(rank.getGlobalRank());
            TextView countryName = (TextView) findViewById(R.id.countryName);
            countryName.setText(rank.getCountry());
            TextView countryRank = (TextView) findViewById(R.id.countryRank);
            countryRank.setText(rank.getCountryRank());
            TextView rankCountry = (TextView) findViewById(R.id.rankCountry);
            rankCountry.setText("RANK IN " + rank.getCountry());
        }

        lvCircularCD.stopAnim();
        lvCircularCD.setVisibility(View.GONE);
        t1.setVisibility(View.VISIBLE);
        btnMoreInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        String site = siteName.getText().toString().trim();
        if (view.getId() == R.id.go) {
            if (!TextUtils.isEmpty(site)) {
                site = getString(R.string.prefix_url) + site;
                getRank(site);
            } else
                siteName.setBackgroundResource(R.drawable.edt_error_bg);
        } else if (view.getId() == R.id.btnMoreInfo) {
            Intent intent = new Intent(MainActivity.this, MoreInfoActivity.class);
            intent.putExtra("SiteName", site);
            startActivity(intent);
        }
    }
}
