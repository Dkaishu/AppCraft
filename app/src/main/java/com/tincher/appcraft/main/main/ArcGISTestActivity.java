package com.tincher.appcraft.main.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.MotionEvent;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PolygonBuilder;
import com.esri.arcgisruntime.geometry.PolylineBuilder;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.DrawStatus;
import com.esri.arcgisruntime.mapping.view.DrawStatusChangedEvent;
import com.esri.arcgisruntime.mapping.view.DrawStatusChangedListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LayerViewStateChangedEvent;
import com.esri.arcgisruntime.mapping.view.LayerViewStateChangedListener;
import com.esri.arcgisruntime.mapping.view.LayerViewStatus;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.view.WrapAroundMode;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleRenderer;
import com.tincher.appcraft.R;
import com.tincher.appcraft.base.BaseActivity;
import com.tincher.appcraft.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.esri.arcgisruntime.loadable.LoadStatus.LOADED;
import static com.tincher.appcraft.R.id.mapView;

/**
 * Created by dks on 2017/11/24.
 */

public class ArcGISTestActivity extends BaseActivity {
    private MapView mMapView;

    @Override
    protected int initLayout() {
        return R.layout.activity_test_arcgis;
    }

    @Override
    protected void initView() {
        mMapView = (MapView) findViewById(mapView);
        displayMyMap();
        addGraphics();
        addGraphicsOverlay();
    }

    /**
     * https://developers.arcgis.com/android/latest/guide/display-a-map.htm
     */
    private void displayMyMap() {
        final ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 34.056295, -117.195800, 16);

//        LoadStatus:  NOT_LOADED, LOADING, LOADED, FAILED_TO_LOAD;
        map.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                if (map.getLoadStatus() == LOADED) {
                    LogUtil.e("LoadStatus==LOADED");

                    // Once map is loaded, can check its properties and content
                    if (map.getBookmarks().size() > 0) {
                        // For example, show UI and allow user to choose a map bookmark...
                        LogUtil.e("HasBookmarks");

                    }
                } else {
                    // If loading failed, deal with failure depending on the cause...
//                    dealWithLoadFailure();
                    LogUtil.e("LoadFailure");
                }
            }
        });

        mMapView.setMap(map);

        //Monitor layer loading
//        LayerViewStatus:  ACTIVE, NOT_VISIBLE, OUT_OF_SCALE, LOADING, ERROR;
        mMapView.addLayerViewStateChangedListener(new LayerViewStateChangedListener() {
            @Override
            public void layerViewStateChanged(LayerViewStateChangedEvent layerViewStateChangedEvent) {
                // Each layer may have more than one layer view state.
                StringBuilder layerStatuses = new StringBuilder();
                for (LayerViewStatus status : layerViewStateChangedEvent.getLayerViewStatus()) {
                    if (layerStatuses.length() > 0) {
                        layerStatuses.append(",");
                    }
                    layerStatuses.append(status.name());
                }

//                showMessage(String.format("Layer '%s' status=%s", layerViewStateChangedEvent.getLayer().getName(),
//                        layerStatuses.toString()));
                LogUtil.e(String.format("Layer '%s' status=%s", layerViewStateChangedEvent.getLayer().getName(),
                        layerStatuses.toString()));
            }
        });

//        Monitor map drawing,移动位置时，区块会有加载延迟，监听其加载状态
//        to detect whether the map drawing is in progress or has completed
//        DrawStatus :  IN_PROGRESS,COMPLETED;
        mMapView.addDrawStatusChangedListener(new DrawStatusChangedListener() {
            @Override
            public void drawStatusChanged(DrawStatusChangedEvent drawStatusChangedEvent) {
                if (drawStatusChangedEvent.getDrawStatus() == DrawStatus.IN_PROGRESS) {
//                    progressBar.setVisibility(View.VISIBLE);
                    LogUtil.e("DrawStatus==IN_PROGRESS  ");
                } else if (drawStatusChangedEvent.getDrawStatus() == DrawStatus.COMPLETED) {
//                    progressBar.setVisibility(View.INVISIBLE);
                    LogUtil.e("DrawStatus==COMPLETED  ");

                }
            }
        });

//        Navigate the map
//      当旋转、缩放时：getVisibleArea 获取可见区域，返回值为多边形；
//      setViewpoint 可设置哪个多边形可见与否，你可以：
//        Rotate the map to a specified angle.
//        Zoom the map to the specified scale.
//        Zoom or pan the map so that a given geometry fits the visible area of the map view.
//        Zoom or pan the map to a specified location.
//        Zoom or pan the map to a specific view point. You can define a view point using a:
//              Center and scale
//              Center, scale, and rotation
//              Latitude, longitude, and scale
//              Target extent
//              Target extent and rotation

        timer1 = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                //两秒动画移动至此点，返回值 为是否动画完成（可能被用户中断）
                Viewpoint viewpoint = new Viewpoint(27.3805833, 33.6321389, 6E3);
                final ListenableFuture<Boolean> viewpointSetFuture = mMapView.setViewpointAsync(viewpoint, 2);
                viewpointSetFuture.addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boolean completed = viewpointSetFuture.get();
                            if (completed) LogUtil.e("Animation completed successfully");
//                        showMessage("Animation completed successfully");
                        } catch (InterruptedException e) {
//                    showMessage("Animation interrupted");
                            LogUtil.e("Animation interrupted");
                        } catch (ExecutionException e) {
                            // Deal with exception during animation...
                        }
                    }
                });
            }
        }
        ;
//        .start();

        map.loadAsync();

/**
 * **************************** Configure the map view **********************************************
 */
//        Configure the map view
//        enhance your user's map experience by displaying the device's current location,
//          rotating the map display, or making the world map seamless.

        //定位
        LocationDisplay locationDisplay = mMapView.getLocationDisplay();
        locationDisplay.addDataSourceStatusChangedListener(new LocationDisplay.DataSourceStatusChangedListener() {
            @Override
            public void onStatusChanged(LocationDisplay.DataSourceStatusChangedEvent dataSourceStatusChangedEvent) {
                if (dataSourceStatusChangedEvent.getSource().getLocationDataSource().getError() == null) {
//                    showMessage("Location Display Started=" + dataSourceStatusChangedEvent.isStarted());
                    LogUtil.e("Location Display Started=" + dataSourceStatusChangedEvent.isStarted());
                } else {
                    // Deal with problems starting the LocationDisplay...
                    LogUtil.e("Location Display Error" + dataSourceStatusChangedEvent.getSource()
                            .getLocationDataSource().getError().getMessage());

                }
            }
        });
        locationDisplay.startAsync();

//    全景图  Enable wrap around

        // wraparound is enabled if layers within map support it
//        https://developers.arcgis.com/android/latest/guide/wraparound-maps.htm#ESRI_SECTION1_8DAB0310D6A4442B9714B059F362FED7
//
//   Note:
//        If your map has wrap around enabled and you're working with map locations, map extents,
//              or are editing or sketching geometries with a map, you must normalize the geometries.
        mMapView.setWrapAroundMode(WrapAroundMode.ENABLE_WHEN_SUPPORTED);

//      旋转角度  Rotate the map
        final ListenableFuture<Boolean> viewpointSetFuture = mMapView.setViewpointRotationAsync(90);
        viewpointSetFuture.addDoneListener(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean completed = viewpointSetFuture.get();
                    if (completed)
//                        showMessage("Rotation completed successfully");
                        LogUtil.e("Rotation completed successfully");
                } catch (InterruptedException e) {
//                    showMessage("Rotation interrupted");
                    LogUtil.e("Rotation interrupted");

                } catch (ExecutionException e) {
                    // Deal with exception during animation...
                }
            }
        });


//     交互 ：监听 touch 事件。Enhance the user's map interaction

//      Note:
//        Alternatively, you can directly implement the MapView.OnTouchListener interface,
//              and handle all gestures yourself.
        class MapSingleTapListener extends DefaultMapViewOnTouchListener {
            public MapSingleTapListener(Context context, MapView mapView) {
                super(context, mapView);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Point mapPoint = mMapView.screenToLocation(new android.graphics.Point((int) e.getX(), (int) e.getY()));
//                showMessage(String.format("User tapped on the map at (%.3f,%.3f)", mapPoint.getX(), mapPoint.getY()));
                LogUtil.d(String.format("User tapped on the map at (%.3f,%.3f)", mapPoint.getX(), mapPoint.getY()));
                return true;
            }
        }
        mMapView.setOnTouchListener(new MapSingleTapListener(this, mMapView));

//    获取缩略图或截屏 Bitmap
//        保存到本地
//      Note: 缩略图有大小限制 1M.
//        If you want to store this image as a thumbnail of a portal item, be aware that the size
//             limit of a thumbnail is 1 MB. Check the image size before saving the image as
//             a thumbnail and, if necessary resize it.
        timer2 = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                final ListenableFuture<Bitmap> exportImageFuture = mMapView.exportImageAsync();
                exportImageFuture.addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Get the resulting Bitmap from the future
                            Bitmap bitmap = exportImageFuture.get();

                            if (bitmap != null) {
                                // Create a File to write the Bitmap into
                                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "map.png");
                                FileOutputStream fileOutputStream;
                                try {
                                    // Write the Bitmap into the file and close the file stream.
                                    fileOutputStream = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                                    fileOutputStream.close();
                                    LogUtil.e("save thumbnail successfully");
                                } catch (IOException e) {
                                    // Deal with exception writing file...
                                    LogUtil.e("save thumbnail :  IOException");
                                }
                            }

                        } catch (InterruptedException | ExecutionException e) {
                            // Deal with exception during export...
                            LogUtil.e("save thumbnail :  InterruptedException | ExecutionException e");

                        }
                    }
                });

            }
        }.start();

    }

    private void addGraphics(){

//   Add point graphics

        SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
        Point buoy1Loc = new Point(-2.72, 56.065, SPATIAL_REFERENCE);
        Point buoy2Loc = new Point(-2.69, 56.065, SPATIAL_REFERENCE);
        Point buoy3Loc = new Point(-2.66, 56.065, SPATIAL_REFERENCE);
        Point buoy4Loc = new Point(-2.63, 56.065, SPATIAL_REFERENCE);

        // create a red (0xFFFF0000) circle simple marker symbol
        SimpleMarkerSymbol redCircleSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);

        // create graphics and add to graphics overlay
        Graphic buoyGraphic1 = new Graphic(buoy1Loc, redCircleSymbol);
        Graphic buoyGraphic2 = new Graphic(buoy2Loc, redCircleSymbol);
        Graphic buoyGraphic3 = new Graphic(buoy3Loc, redCircleSymbol);
        Graphic buoyGraphic4 = new Graphic(buoy4Loc, redCircleSymbol);

//        graphicsOverlay.getGraphics().addAll(Arrays.asList(buoyGraphic1, buoyGraphic2, buoyGraphic3, buoyGraphic4));

    }


    private void addGraphicsOverlay(){
        // point graphic
        Point pointGeometry = new Point(60e5, 60e5, SpatialReferences.getWebMercator());
        // red diamond point symbol
        SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.DIAMOND, Color.RED, 10);
        // create graphic for point
        Graphic pointGraphic = new Graphic(pointGeometry);
        // create a graphic overlay for the point
        GraphicsOverlay pointGraphicOverlay = new GraphicsOverlay();
        // create simple renderer
        SimpleRenderer pointRenderer = new SimpleRenderer(pointSymbol);
        pointGraphicOverlay.setRenderer(pointRenderer);
        // add graphic to overlay
        pointGraphicOverlay.getGraphics().add(pointGraphic);
        // add graphics overlay to the MapView
        mMapView.getGraphicsOverlays().add(pointGraphicOverlay);

        // line graphic
        PolylineBuilder lineGeometry = new PolylineBuilder(SpatialReferences.getWebMercator());
        lineGeometry.addPoint(-10e5, 40e5);
        lineGeometry.addPoint(20e5, 50e5);
        // solid blue line symbol
        SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 5);
        // create graphic for polyline
        Graphic lineGraphic = new Graphic(lineGeometry.toGeometry());
        // create graphic overlay for polyline
        GraphicsOverlay lineGraphicOverlay = new GraphicsOverlay();
        // create simple renderer
        SimpleRenderer lineRenderer = new SimpleRenderer(lineSymbol);
        // add graphic to overlay
        lineGraphicOverlay.setRenderer(lineRenderer);
        // add graphic to overlay
        lineGraphicOverlay.getGraphics().add(lineGraphic);
        // add graphics overlay to the MapView
        mMapView.getGraphicsOverlays().add(lineGraphicOverlay);

        //polygon graphic
        PolygonBuilder polygonGeometry = new PolygonBuilder(SpatialReferences.getWebMercator());
        polygonGeometry.addPoint(-20e5, 20e5);
        polygonGeometry.addPoint(20e5, 20e5);
        polygonGeometry.addPoint(20e5, -20e5);
        polygonGeometry.addPoint(-20e5, -20e5);
        // solid yellow polygon symbol
        SimpleFillSymbol polygonSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.YELLOW, null);
        // create graphic for polygon
        Graphic polygonGraphic = new Graphic(polygonGeometry.toGeometry());
        // create graphic overlay for polygon
        GraphicsOverlay polygonGraphicOverlay = new GraphicsOverlay();
        // create simple renderer
        SimpleRenderer polygonRenderer = new SimpleRenderer(polygonSymbol);
        // add graphic to overlay
        polygonGraphicOverlay.setRenderer(polygonRenderer);
        // add graphic to overlay
        polygonGraphicOverlay.getGraphics().add(polygonGraphic);
        // add graphics overlay to MapView
        mMapView.getGraphicsOverlays().add(polygonGraphicOverlay);

    }

    CountDownTimer timer1, timer2, timer3;

    @Override
    protected void onPause() {
        mMapView.pause();
        timer1.cancel();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.resume();
    }
}
