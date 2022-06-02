package cabanzo.checkgeorgio.Adapter;

import android.content.Intent;

public interface OnIntentReceived {
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
