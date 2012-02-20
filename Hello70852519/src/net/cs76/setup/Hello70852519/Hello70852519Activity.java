// Reagan Williams
// reagan.williams@gmail.com
// HUID: 70852519
// CS-76

package net.cs76.setup.Hello70852519;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Hello70852519Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView tv = new TextView(this);
        tv.setText("Hello, cs76!");
        setContentView(tv);
    }
}