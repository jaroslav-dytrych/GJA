package cz.vutbr.fit.gja.androidmaps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import java.io.ByteArrayOutputStream;

/**
 * This demonstrates tile overlay coordinates.
 */
public class TileActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            MapsInitializer.initialize(this);
            // Try to obtain the map from the SupportMapFragment.
            SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            smf.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Check if we were successful in obtaining the map.
        if (mMap != null) {
            setUpMap();
        }
    }

    private void setUpMap() {
         TileProvider coordTileProvider = new CoordTileProvider(this.getApplicationContext());
         mMap.addTileOverlay(new TileOverlayOptions().tileProvider(coordTileProvider));
    }

    private static class CoordTileProvider implements TileProvider {
        private static final int TILE_SIZE_DP = 256;
        private float mScaleFactor;
        private Paint mBorderPaint;
        private Bitmap mBorderTile;

        public CoordTileProvider(Context context) {
            /* Scale factor based on density, with a 0.6 multiplier to increase tile generation
             * speed */
            mScaleFactor = context.getResources().getDisplayMetrics().density * 0.6f;
            mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBorderPaint.setStyle(Paint.Style.STROKE);
            mBorderTile = Bitmap.createBitmap((int) (TILE_SIZE_DP * mScaleFactor),
                (int) (TILE_SIZE_DP * mScaleFactor), android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mBorderTile);
            canvas.drawRect(0, 0, TILE_SIZE_DP * mScaleFactor, TILE_SIZE_DP * mScaleFactor,
                mBorderPaint);
        }

        public Tile getTile(int x, int y, int zoom) {
            Bitmap coordTile = drawTileCoords(x, y, zoom);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            coordTile.compress(Bitmap.CompressFormat.PNG, 0, stream);
            byte[] bitmapData = stream.toByteArray();
            return new Tile((int) (TILE_SIZE_DP * mScaleFactor),
                (int) (TILE_SIZE_DP * mScaleFactor), bitmapData);
        }

        private Bitmap drawTileCoords(int x, int y, int zoom) {
            Bitmap copy = mBorderTile.copy(android.graphics.Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(copy);
            String tileCoords = "(" + x + ", " + y + ")";
            String zoomLevel = "zoom = " + zoom;
            /* Paint is not thread safe. */
            Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            mTextPaint.setTextSize(18 * mScaleFactor);
            canvas.drawText(tileCoords, TILE_SIZE_DP * mScaleFactor / 2,
                TILE_SIZE_DP * mScaleFactor / 2, mTextPaint);
            canvas.drawText(zoomLevel, TILE_SIZE_DP * mScaleFactor / 2,
                TILE_SIZE_DP * mScaleFactor * 2 / 3, mTextPaint);
            return copy;
        }
    }
}