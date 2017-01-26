package jonaslagoni.fliks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Fliks_splashscreen extends Controller {
    private ProgressBar splashSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        splashSpinner = (ProgressBar)findViewById(R.id.SplashLoading);
        startActivity(new Intent(Fliks_splashscreen.this, MenuDrawer.class));
    }
}
