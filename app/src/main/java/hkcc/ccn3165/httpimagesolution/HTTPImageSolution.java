package hkcc.ccn3165.httpimagesolution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.development.R;

public class HTTPImageSolution extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_image);

        Button next = (Button) findViewById(R.id.Button02);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        ImageView iv = (ImageView) findViewById(R.id.aSyncTaskImageView);
        // E/ImageDownloader: javax.net.ssl.SSLProtocolException: SSL handshake aborted: ssl=0x1090618: Failure in SSL library, usually a protocol error
        // error:14077410:SSL routines:SSL23_GET_SERVER_HELLO:sslv3 alert handshake failure (external/openssl/ssl/s23_clnt.c:744 0x52513d74:0x00000000)
        // https://www.cnblogs.com/renhui/p/6591347.html
        new ImageDownloader().execute("https://www.oracle.com/webfolder/s/brand/assets/i/specimens/identity/logo/primary-badge.gif", iv);
    }
}
