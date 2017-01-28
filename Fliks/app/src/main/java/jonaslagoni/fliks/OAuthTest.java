package jonaslagoni.fliks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import jonaslagoni.fliks.OAuth.OAuthController;

public class OAuthTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_test);
        initialOauth();
    }

    public void initialOauth() {
        new OAuthController(super.findViewById(R.id.OAuthWebview)).execute();
    }
}
