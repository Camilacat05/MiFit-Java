package com.amap.api.maps;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.amap.api.mapcore.ai;
import com.amap.api.mapcore.t;
import com.amap.api.mapcore.x;
import com.amap.api.maps.model.RuntimeRemoteException;

public class MapView extends FrameLayout {
    private x a;
    private AMap b;
    private int c = 0;

    public MapView(Context context) {
        super(context);
        getMapFragmentDelegate().a(context);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().a(context);
        getMapFragmentDelegate().a(this.c);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = attributeSet.getAttributeIntValue(16842972, 0);
        getMapFragmentDelegate().a(context);
        getMapFragmentDelegate().a(this.c);
    }

    public MapView(Context context, AMapOptions aMapOptions) {
        super(context);
        getMapFragmentDelegate().a(context);
        getMapFragmentDelegate().a(aMapOptions);
    }

    public AMap getMap() {
        try {
            t a = getMapFragmentDelegate().a();
            if (a == null) {
                return null;
            }
            if (this.b == null) {
                this.b = new AMap(a);
            }
            return this.b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    protected x getMapFragmentDelegate() {
        if (this.a == null) {
            this.a = new ai();
        }
        return this.a;
    }

    public final void onCreate(Bundle bundle) {
        try {
            addView(getMapFragmentDelegate().a(null, null, bundle), new LayoutParams(-1, -1));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onDestroy() {
        try {
            getMapFragmentDelegate().e();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onLowMemory() {
        try {
            getMapFragmentDelegate().f();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onPause() {
        try {
            getMapFragmentDelegate().c();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onResume() {
        try {
            getMapFragmentDelegate().b();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        try {
            getMapFragmentDelegate().b(bundle);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setLayerType(int i, Paint paint) {
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        getMapFragmentDelegate().a(i);
    }
}
