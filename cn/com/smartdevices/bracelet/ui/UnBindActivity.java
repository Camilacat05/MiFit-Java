package cn.com.smartdevices.bracelet.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.smartdevices.bracelet.B;
import cn.com.smartdevices.bracelet.C0401a;
import cn.com.smartdevices.bracelet.C0596r;
import cn.com.smartdevices.bracelet.C0667t;
import cn.com.smartdevices.bracelet.Keeper;
import cn.com.smartdevices.bracelet.Utils;
import cn.com.smartdevices.bracelet.datasource.DeviceSource;
import cn.com.smartdevices.bracelet.eventbus.shoes.EventDeviceBoundState;
import cn.com.smartdevices.bracelet.j.l;
import com.tencent.connect.common.Constants;
import com.xiaomi.hm.health.R;
import com.xiaomi.hm.health.bt.bleservice.a;
import com.xiaomi.hm.health.bt.model.HwBatteryStatus;
import de.greenrobot.event.EventBus;

public class UnBindActivity extends SystemBarTintActivity implements OnClickListener {
    private String a = Utils.b;

    private void a(String str) {
        int i;
        Utils.a((Activity) this, (int) R.string.unbinding);
        C0596r.d("switch", "unbind device " + str);
        if (str.equals(Utils.c)) {
            i = 1;
            DeviceSource.unbindWeight();
            a.h();
            Keeper.setSyncWeightInfoToServer(0);
        } else {
            if (B.a().f()) {
                B.a().a(Keeper.readBraceletBtInfo().b);
            }
            Utils.c((Context) this, 0);
            DeviceSource.unbindBracelet();
            Keeper.keepSyncTime(-1, 0);
            Keeper.keepSyncTime(-1, 3);
            Keeper.keepRealtimeStepsTimestamp(-1, 0);
            Keeper.keepRealtimeStepsTimestamp(-1, 3);
            Keeper.keepNeedBind(0);
            Keeper.setSyncBraceletInfoToServer(0);
            EventBus.getDefault().post(new HwBatteryStatus(0, 100));
            l.d(cn.com.smartdevices.bracelet.e.a.f(this), cn.com.smartdevices.bracelet.e.a.d(this), new fh(this));
            i = 0;
        }
        EventBus.getDefault().post(new EventDeviceBoundState(i, 0));
        new Handler().postDelayed(new fi(this), 1);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_button:
                a(this.a);
                C0401a.a((Context) this, C0401a.dQ, this.a, C0401a.dC);
                return;
            case R.id.right_button:
                onHomeBackPressed();
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_mili_unbind);
        String stringExtra = getIntent().getStringExtra(Constants.VIA_RESULT_SUCCESS);
        if (stringExtra != null) {
            this.a = stringExtra;
        }
        findViewById(R.id.left_button).setOnClickListener(this);
        findViewById(R.id.right_button).setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.mili_unbind_info_1);
        if (this.a.equals(Utils.c)) {
            textView.setVisibility(4);
            ((TextView) findViewById(R.id.mili_unbind_title_tv)).setText(R.string.weight_unbind_question);
            ((TextView) findViewById(R.id.mili_unbind_info)).setText(R.string.mili_weight_unbind_info);
            textView = (TextView) findViewById(R.id.mili_unbind_info_1);
            textView.setVisibility(0);
            textView.setText(R.string.mili_weight_unbind_info_1);
            ((ImageView) findViewById(R.id.not_binded_iv)).setImageResource(R.drawable.weight_not_binded);
        } else if (!C0667t.c(this)) {
            textView.setVisibility(4);
        }
    }

    protected void onHomeBackPressed() {
        C0401a.a((Context) this, C0401a.dQ, this.a, C0401a.dD);
        super.onHomeBackPressed();
    }

    protected void onPause() {
        super.onPause();
        C0401a.b(C0401a.R);
        C0401a.b((Activity) this);
    }

    protected void onResume() {
        super.onResume();
        C0401a.a(C0401a.R);
        C0401a.a((Activity) this);
    }
}
