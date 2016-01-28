package com.example.android.osmdroidofflinedemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.InfoWindow;
import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class MainActivity extends Activity implements MapEventsReceiver {

    private static final String TAG =null ;
    MapView mapView;
    public static final GeoPoint ROSARIO= new GeoPoint(-32.94424, -60.65054);
    public static final GeoPoint Fabrica= new GeoPoint(-32.940380,-60.665102);
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

       // File file = new File(getFilesDir() + "/osmdroid/tiles/" + "tiles.zip");

        File folder = new File(Environment.getExternalStorageDirectory().toString()+"/osmdroid/tiles/MapquestOSM");


        if(folder.exists()) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(),
                    R.drawable.icono);

            mapView = (MapView) findViewById(R.id.mapview);
            mapView.setTilesScaledToDpi(true);
            mapView.setTilesScaledToDpi(true);
            mapView.setClickable(true);
            mapView.setBuiltInZoomControls(true);
            mapView.setMultiTouchControls(true);
            mapView.setUseDataConnection(false);
            mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
           // mapView.setUseDataConnection(false);

            IMapController mapViewController = mapView.getController();
            mapViewController.setZoom(10);
            mapViewController.setCenter(ROSARIO);
            Marker startMarker = new Marker(mapView);
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            GeoPoint currentLocation = new GeoPoint(location);

            if( location != null ) {
                currentLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
            }



            startMarker.setPosition(Fabrica);
            //startMarker.setPosition(new GeoPoint(-32.940380, -60.665102));
            startMarker.setPosition(currentLocation);
        /*int lat= 30;
        int lng=20;
        startMarker.setPosition(new GeoPoint(lat, lng));
*/
            startMarker.setIcon(getDrawable(R.drawable.icono));
            startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            startMarker.setTitle("Fabrica");
            startMarker.setSnippet("chicos");
            mapView.getOverlays().add(startMarker);
        }
        else {
            new CopyData().execute();
        }
        /*String FILENAME = "hello_file";
        String string = "hello world!";
        File mydir = this.getDir("mydir", Context.MODE_PRIVATE); //Creating an internal dir;*/
     /*   try {
            FileOutputStream fos = new FileOutputStream(myInternalFile);
            fos.write(myInputText.getText().toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
      /*  File wallpaperDirectory = new File("/sdcard/");
        File folder = new File(Environment.getExternalStorageDirectory().toString()+"/sdcard/");
        folder.mkdirs();*/
        /*String filename = "prueba";
        String string = "dsdfsd";
        FileOutputStream outputStream;
        File wallpaperDirectory = new File("/sdcard2/");
        File folder = new File(Environment.getExternalStorageDirectory().toString()+"/sdcard2/");
        folder.mkdirs();
        //Save the path as a string value
        String extStorageDirectory = folder.toString();
try{
        //File file = new File(extStorageDirectory, "demo.txt");
    File file = new File(extStorageDirectory, "demo.txt");
        FileOutputStream outStream = new FileOutputStream(file);
         outStream.write(string.getBytes());

            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



       /* try {
            File wallpaperDirectory = new File("/sdcard2/");
            File folder = new File(Environment.getExternalStorageDirectory().toString()+"/osmdroid/");
            folder.mkdirs();
            AssetManager assetManager = getAssets();
            InputStream inputStream = null;

            InputStream in = assetManager.open("/tiles-example/tiles.zip");

            AssetFileDescriptor descriptor = assetManager.openFd("/tiles-example/tiles.zip");
            File outFile = new File(folder, String.valueOf(descriptor));

            FileOutputStream outStream = new FileOutputStream(String.valueOf(descriptor));
            outStream.write(outStream.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Create New file and name it Image2.PNG
        //File file = new File(extStorageDirectory, "Image2.PNG");


        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.icono);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setTilesScaledToDpi(true);
        mapView.setTilesScaledToDpi(true);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.setUseDataConnection(true);
        mapView.setTileSource(TileSourceFactory.MAPQUESTOSM);

        IMapController mapViewController = mapView.getController();
        mapViewController.setZoom(10);
        mapViewController.setCenter(ROSARIO);
        Marker startMarker = new Marker(mapView);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        GeoPoint currentLocation = new GeoPoint(location);

        if( location != null ) {
            currentLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
        }



        //startMarker.setPosition(Fabrica);
        //startMarker.setPosition(new GeoPoint(-32.940380, -60.665102));
        startMarker.setPosition(currentLocation);
        /*int lat= 30;
        int lng=20;
        startMarker.setPosition(new GeoPoint(lat, lng));*/

        startMarker.setIcon(getDrawable(R.drawable.icono));
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("Fabrica");
        startMarker.setSnippet("chicos");
        mapView.getOverlays().add(startMarker);
      /*  public void setTileSource(final ITileSource aTileSource) {
            mTileProvider.setTileSource(aTileSource);
            float density = getResources().getDisplayMetrics().density;
            TileSystem.setTileSize((int) (aTileSource.getTileSizePixels() * density));
            this.checkZoomButtons();
            this.setZoomLevel(mZoomLevel); // revalidate zoom level
            mapView.postInvalidate();*/
        /*ArrayList<OverlayItem> anotherOverlayItemArray;
        anotherOverlayItemArray = new ArrayList<OverlayItem>();

        anotherOverlayItemArray.add(new OverlayItem("0, 0", "0, 0", new GeoPoint(0, 0)));*/

        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this, this);
        mapView.getOverlays().add(0, mapEventsOverlay);

        }


    @Override public boolean singleTapConfirmedHelper(GeoPoint p) {
        //Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show();
        ArrayList<InfoWindow> opened = getOpenedInfoWindowsOn(mapView);
        for (InfoWindow infoWindow:opened){
            infoWindow.close();
        }
        return true;
    }

    static public ArrayList<InfoWindow> getOpenedInfoWindowsOn(MapView mapView){
        int count = mapView.getChildCount();
        ArrayList<InfoWindow> opened = new ArrayList<InfoWindow>(count);
        for (int i = 0; i < count; i++) {
            final View child = mapView.getChildAt(i);
            Object tag = child.getTag();
            if (tag != null && tag instanceof InfoWindow){
                InfoWindow infoWindow = (InfoWindow)tag;
                opened.add(infoWindow);
            }
        }
        return opened;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override public boolean longPressHelper(GeoPoint p) {
        //DO NOTHING FOR NOW:

       // MotionEvent e=null;
      /*  Projection proj = mapView.getProjection();
        //GeoPoint point = (GeoPoint) proj.fromPixels((int) mapView.getX(), (int) mapView.getY());
        proj = mapView.getProjection();

        p = (GeoPoint) proj.fromPixels((int) mapView.getX(), (int) mapView.getY());

        int lat= p.getLatitudeE6();
        int lgn= p.getLongitudeE6();*/

        double lg = p.getLongitude();
        double lt = p.getLatitude();
       //Toast.makeText(this, lat, Toast.LENGTH_SHORT).show();
        Intent i=new Intent(MainActivity.this, FormularioActivity.class);
        String lat2 =(String.valueOf(lg));
        String lgn2 =(String.valueOf(lt));
        i.putExtra("lat",lat2);
        //i.putExtra("lat",lgn2);
        //startActivity(i);
    /*    IMapController mapViewController = mapView.getController();
        mapViewController.setZoom(10);
        mapViewController.setCenter(ROSARIO);*/


        Marker GeoMarker = new Marker(mapView);
        GeoMarker.setPosition(new GeoPoint(lt, lg));
        /*int lat= 30;
        int lng=20;
        startMarker.setPosition(new GeoPoint(lat, lng));*/

        GeoMarker.setIcon(getDrawable(R.drawable.icono));
        GeoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        InfoWindow infoWindow = new InfoWindow(R.layout.bubble_layout, mapView) {
            @Override
            public void onOpen(Object arg0) {
                LinearLayout layout = (LinearLayout) mView.findViewById(R.id.bubble_layout);
                Button btnMoreInfo = (Button) mView.findViewById(R.id.button);
                TextView txtTitle = (TextView) mView.findViewById(R.id.TV_title);
                TextView txtDescription = (TextView) mView.findViewById(R.id.bubble_description);
                TextView txtSubdescription = (TextView) mView.findViewById(R.id.bubble_subdescription);

               /* txtTitle.setText("Title of my marker");
                txtDescription.setText("Click here to view details!");
                txtSubdescription.setText("You can also edit the subdescription");
               */ layout.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Override Marker's onClick behaviour here
                    }
                });
            }

            @Override
            public void onClose() {


            }
        };

        GeoMarker.setInfoWindow(infoWindow);

        GeoMarker.setTitle("Fabrica");
        GeoMarker.setSnippet("chicos");
        mapView.getOverlays().add(GeoMarker);
        mapView.invalidate();

       // Toast.makeText(this, "LONGGGG Tapped", Toast.LENGTH_SHORT).show();

        return false;
    }



//////////////////////////////////////////////////////////////////////////////////////
public class CopyData extends AsyncTask<Void, Void, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(getApplicationContext(), "Load Map", Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(Void... params) {
        copyAssets();
        return "Done";

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(getApplicationContext(), "Copy Complete", Toast.LENGTH_LONG).show();
    }

}


    private void copyAssets() {



        InputStream in = null;
        OutputStream out = null;
        try {

            in = getAssets().open("tiles.zip");

            Log.i(TAG, ": " + Environment.getExternalStorageDirectory());
            File dir = new File(Environment.getExternalStorageDirectory(),
                    "osmdroid");
            File wallpaperDirectory = new File("/osmdroid/tiles/");
            File folder = new File(Environment.getExternalStorageDirectory().toString()+"/osmdroid/tiles/");
            folder.mkdirs();
            Log.i(TAG, "isExist : " + folder.exists());
            if (!folder.exists())
                folder.mkdirs();
            File fileZip = new File(folder, "tiles.zip");
            Log.i(TAG, "isExist : " + fileZip.exists());

            out = new FileOutputStream(fileZip);
            copyFile(in, out);
            in.close();
            unzip(fileZip, folder);
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            Log.e("tag", "Failed to copy asset file: " + e.getMessage());
        }
    }



    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }

    }

    public static void unzip(File zipFile, File targetDirectory) throws IOException {
        ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(new FileInputStream(zipFile)));
        try {
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[8192];
            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(targetDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " +
                            dir.getAbsolutePath());
                if (ze.isDirectory())
                    continue;
                FileOutputStream fout = new FileOutputStream(file);
                try {
                    while ((count = zis.read(buffer)) != -1)
                        fout.write(buffer, 0, count);
                } finally {
                    fout.close();
                }
            /* if time should be restored as well
            long time = ze.getTime();
            if (time > 0)
                file.setLastModified(time);
            */
            }
        } finally {
            zis.close();
        }
    }

}




