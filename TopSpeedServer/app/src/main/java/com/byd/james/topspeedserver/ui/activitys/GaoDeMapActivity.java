package com.byd.james.topspeedserver.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.byd.james.topspeedserver.R;
import com.byd.james.topspeedserver.base.BaseActivity;
import com.byd.james.topspeedserver.presenter.contract.GeDeMapContract;
import com.byd.james.topspeedserver.utils.EventUtil;
import com.byd.james.topspeedserver.utils.Pretreatment;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GaoDeMapActivity extends BaseActivity implements AMapLocationListener, LocationSource
        , GeDeMapContract.View {
    @BindView(R.id.aMapView)
    MapView mMapView;
    //声明LocationClientOption对象
    public AMapLocationClientOption mOption = null;
    @BindView(R.id.location_tv_noChange)
    TextView locationTvNoChange;
    @BindView(R.id.location_tv_Change)
    TextView locationTvChange;
    @BindView(R.id.location_tv_Change_bySelf)
    EditText locationTvChangeBySelf;
    @BindView(R.id.location_finish)
    TextView locationTvFinish;

    private AMapLocationClient mAMapLocationClient;
    private AMap mAMap;
    OnLocationChangedListener mListener;
    private String location;
    public Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gao_de_map);
        unbinder = ButterKnife.bind(this);
        mMapView.onCreate(savedInstanceState);

        setAMap();
    }

    private void setAMap() {
        //設置地圖
        mAMap = mMapView.getMap();
        mAMap.setLocationSource(this);
        mAMap.setMyLocationEnabled(true);
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        //初始化定位參數
        mAMapLocationClient = new AMapLocationClient(this);
        //声明定位对象
        mOption = new AMapLocationClientOption();
        //设置定位监听
        mAMapLocationClient.setLocationListener(this);
        //设置定位监听间隔时间
        mOption.setInterval(2000);
        //选择高精度定位模式
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息
        mOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mOption.setMockEnable(false);
        mOption.setHttpTimeOut(15000);
        //关闭定位缓存
        mOption.setLocationCacheEnable(false);
        //设置定位参数
        mAMapLocationClient.setLocationOption(mOption);
        //开始定位
        mAMapLocationClient.startLocation();

    }

    //停止定位
    @Override
    public void deactivate() {
        mListener = null;
        if (mAMapLocationClient != null) {
            mAMapLocationClient.stopLocation();
            mAMapLocationClient.onDestroy();
        }
        mAMapLocationClient = null;
    }

    @Override//定位的监听
    public void onLocationChanged(AMapLocation aMapLocation) {
        //此方法中含有定位信息
        if (aMapLocation != null) {
            mIntent = new Intent();
            if (aMapLocation.getErrorCode() == 0) {
                //显示系统小蓝点
                mListener.onLocationChanged(aMapLocation);
                long time = aMapLocation.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(new Date(time));
                location = format + aMapLocation.getProvince() + aMapLocation.getCity() + aMapLocation.getDistrict();

            } else {
                //错误码和错误的信息
                int errorCode = aMapLocation.getErrorCode();
                String errorInfo = aMapLocation.getErrorInfo();
                Toast.makeText(this, errorCode + errorInfo, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
       if(mMapView!=null)
       {
           mMapView.onDestroy();
       }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(GeDeMapContract.Presenter presenter) {
        mPresenter = Pretreatment.checkNotNull(presenter);
    }

    @Override
    public void showError(String error) {
        EventUtil.showToast(this, error);
    }

    @OnClick({R.id.location_tv_noChange, R.id.location_tv_Change,R.id.location_finish})
    public void locationClick(View view) {
        String trim = locationTvChangeBySelf.getText().toString().trim();
        if(mIntent!=null) {
            switch (view.getId()) {
                case R.id.location_tv_noChange:
                    mIntent.putExtra("noChange", "NO");
                    this.setResult(100, mIntent);
                    finish();
                    break;
                case R.id.location_tv_Change:
                    if(location!=null && location.length()>0) {
                        mIntent.putExtra("location", location);
                        this.setResult(100, mIntent);
                        finish();
                    }
                    break;
                case R.id.location_finish:
                    if (trim != null && trim.length()>0) {
                        mIntent.putExtra("location", trim);
                    }
                    this.setResult(100, mIntent);
                    finish();
                    break;
            }
        }
    }
}
