package cn.com.smartdevices.bracelet.weibo;

import android.content.Context;
import cn.com.smartdevices.bracelet.C0596r;
import cn.com.smartdevices.bracelet.model.ResponseEntity;
import com.d.a.a.p;
import com.huami.android.widget.f;
import com.xiaomi.channel.gamesdk.b;
import com.xiaomi.e.a;
import com.xiaomi.hm.health.R;
import org.apache.http.Header;
import org.json.JSONObject;

class C extends p<ResponseEntity> {
    final /* synthetic */ ResponseEntity a;
    final /* synthetic */ int b;
    final /* synthetic */ Context c;
    final /* synthetic */ v d;

    C(v vVar, ResponseEntity responseEntity, int i, Context context) {
        this.d = vVar;
        this.a = responseEntity;
        this.b = i;
        this.c = context;
    }

    protected ResponseEntity a(String str, boolean z) {
        C0596r.d("WeiboHealthManager", "bindThirdPartyApp response = " + str);
        if (this.b != 1) {
            C0596r.e("WeiboHealthManager", "bind parse:" + str);
        } else {
            C0596r.e("WeiboHealthManager", "unbind parse:" + str);
        }
        ResponseEntity responseEntity = new ResponseEntity();
        if (z) {
            responseEntity.state = false;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = !jSONObject.isNull(b.a) ? jSONObject.optInt(b.a) : -1;
                String str2 = a.f;
                if (!jSONObject.isNull(f.a)) {
                    str2 = jSONObject.optString(f.a);
                }
                responseEntity.code = optInt;
                responseEntity.msg = str2;
            } catch (Exception e) {
                responseEntity.state = false;
                responseEntity.msg = this.c.getString(R.string.parse_data_failed);
            }
        }
        return responseEntity;
    }

    public void a(int i, Header[] headerArr, String str, ResponseEntity responseEntity) {
        if (responseEntity == null) {
            this.a.state = false;
            return;
        }
        this.a.state = responseEntity.state;
        this.a.code = responseEntity.code;
        this.a.msg = responseEntity.msg;
    }

    public void a(int i, Header[] headerArr, Throwable th, String str, ResponseEntity responseEntity) {
        C0596r.a("WeiboHealthManager", "Bind interface --- Server return status code:" + i);
        this.a.state = false;
    }

    public /* synthetic */ void onFailure(int i, Header[] headerArr, Throwable th, String str, Object obj) {
        a(i, headerArr, th, str, (ResponseEntity) obj);
    }

    public /* synthetic */ void onSuccess(int i, Header[] headerArr, String str, Object obj) {
        a(i, headerArr, str, (ResponseEntity) obj);
    }

    protected /* synthetic */ Object parseResponse(String str, boolean z) {
        return a(str, z);
    }
}
