package jonaslagoni.fliks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Fliks_splashscreen extends Controller {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, MenuDrawer.class));
    }
}
