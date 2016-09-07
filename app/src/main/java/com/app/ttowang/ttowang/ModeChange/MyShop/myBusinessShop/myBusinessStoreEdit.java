package com.app.ttowang.ttowang.ModeChange.MyShop.myBusinessShop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.ttowang.ttowang.Main.MainActivity;
import com.app.ttowang.ttowang.ModeChange.Stamp.stamp;
import com.app.ttowang.ttowang.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by srpgs2 on 2016-08-25.
 */
public class myBusinessStoreEdit extends AppCompatActivity {
    public static Context mContext;

    Button upload,cancel;
    EditText
            businessLicenseEdittext,
            businessNameEdittext,
            businessTelEdittext,
            businessInfoEdittext,
            businessTimeEdittext,
            businessAddressEdittext,
            businessMenuEdittext,
            businessBenefitEdittext,
            businessGroupEdittext;

    String
            businessId,
            businessLicense,
            businessName,
            businessTel,
            businessInfo,
            businessTime,
            businessAddress,
            businessMenu,
            businessBenefit,
            businessGroup;

    int userId;
    int nowposition;
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybusinessstoreedit);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences",MODE_PRIVATE);
        ip = sharedPreferences.getString("ip", "");
        userId = sharedPreferences.getInt("userId", 0);

        mContext = this;
        Intent i = getIntent();
        //userId = i.getExtras().getString("userId");
        businessId = i.getExtras().getString("businessId");
        nowposition = i.getExtras().getInt("position");

        Log.i("내 매장 수정 - ", userId + " " + businessId);

        businessLicenseEdittext = (EditText)findViewById(R.id.businessLicense);
        businessNameEdittext = (EditText)findViewById(R.id.businessName);
        businessTelEdittext = (EditText)findViewById(R.id.businessTel);
        businessInfoEdittext = (EditText)findViewById(R.id.businessInfo);
        businessTimeEdittext = (EditText)findViewById(R.id.businessTime);
        businessAddressEdittext = (EditText)findViewById(R.id.businessAddress);
        businessMenuEdittext = (EditText)findViewById(R.id.businessMenu);
        businessBenefitEdittext = (EditText)findViewById(R.id.businessBenefit);
        businessGroupEdittext = (EditText)findViewById(R.id.businessGroup);
        upload  =   (Button)findViewById(R.id.upload);
        cancel = (Button)findViewById(R.id.cancel);
        //new businessDownAsyncTask().execute();

        businessLicenseEdittext.setText(i.getExtras().getString("businessLicense"));
        businessNameEdittext.setText(i.getExtras().getString("businessName"));
        businessTelEdittext.setText(i.getExtras().getString("businessTel"));
        businessInfoEdittext.setText(i.getExtras().getString("businessInfo"));
        businessTimeEdittext.setText(i.getExtras().getString("businessTime"));
        businessAddressEdittext.setText(i.getExtras().getString("businessAddress"));
        businessMenuEdittext.setText(i.getExtras().getString("businessMenu"));
        businessBenefitEdittext.setText(i.getExtras().getString("businessBenefit"));
        businessGroupEdittext.setText(i.getExtras().getString("businessGroup"));



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                businessLicense = String.valueOf(businessLicenseEdittext.getText());
                businessName =String.valueOf(businessNameEdittext.getText());
                businessTel =String.valueOf(businessTelEdittext.getText());
                businessInfo =String.valueOf(businessInfoEdittext.getText());
                businessTime =String.valueOf(businessTimeEdittext.getText());
                businessAddress =String.valueOf(businessAddressEdittext.getText());
                businessMenu =String.valueOf(businessMenuEdittext.getText());
                businessBenefit =String.valueOf(businessBenefitEdittext.getText());
                businessGroup =String.valueOf(businessGroupEdittext.getText());

                if(
                    !businessLicense.equals("") &
                    !businessName.equals("") &
                    !businessTel.equals("") &
                    !businessInfo.equals("") &
                    !businessTime.equals("") &
                    !businessAddress.equals("") &
                    !businessMenu.equals("") &
                    !businessBenefit.equals("") &
                    !businessGroup.equals("")
                ){
                    new businessUpdateStoreAsyncTask().execute();
                    //Intent intent = new Intent(getApplicationContext(), myBusinessCouponAdd.class);   //인텐트로 넘겨줄건데요~
                    //intent.putExtra("currentViewPager", currentViewPager);
                    //startActivity(intent);
                    //Toast.makeText(myBusinessCoupon.this,"추가 버튼", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(myBusinessStoreEdit.this,"빈칸이 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }




    public class businessUpdateStoreAsyncTask extends AsyncTask<String,Integer,String> {

        protected void onPreExecute(){
        }

        @Override
        protected String doInBackground(String... params) {  // 통신을 위한 Thread
            String result =recvList();
            return result;
        }

        public String encodeString(Properties params) {  //한글 encoding??
            StringBuffer sb = new StringBuffer(256);
            Enumeration names = params.propertyNames();

            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                String value = params.getProperty(name);
                sb.append(URLEncoder.encode(name) + "=" + URLEncoder.encode(value) );

                if (names.hasMoreElements()) sb.append("&");
            }
            return sb.toString();
        }

        private String recvList() { //데이터 보내고 받아오기!!

            HttpURLConnection urlConnection=null;
            URL url =null;
            DataOutputStream out=null;
            BufferedInputStream buf=null;
            BufferedReader bufreader=null;

            Properties prop = new Properties();
/*
            prop.setProperty("userId", String.valueOf(userId));
            prop.setProperty("businessLicense", String.valueOf(businessLicense.getText()));
            prop.setProperty("businessName", String.valueOf(businessName.getText()));
            prop.setProperty("businessTel", String.valueOf(businessTel.getText()));
            prop.setProperty("businessInfo", String.valueOf(businessInfo.getText()));
            prop.setProperty("businessTime", String.valueOf(businessTime.getText()));
            prop.setProperty("businessAddress", String.valueOf(businessAddress.getText()));
            prop.setProperty("businessMenu", String.valueOf(businessMenu.getText()));
            prop.setProperty("businessBenefit", String.valueOf(businessBenefit.getText()));
            prop.setProperty("businessLat","");
            prop.setProperty("businessLng","");
            prop.setProperty("businessType", "1");
            prop.setProperty("businessGroup", String.valueOf(businessGroup.getText()));
*/
            prop.setProperty("businessId", businessId);
            prop.setProperty("userId", String.valueOf(userId));
            prop.setProperty("businessLicense", businessLicense);
            prop.setProperty("businessName", businessName);
            prop.setProperty("businessTel", businessTel);
            prop.setProperty("businessInfo", businessInfo);
            prop.setProperty("businessTime", businessTime);
            prop.setProperty("businessAddress", businessAddress);
            prop.setProperty("businessMenu", businessMenu);
            prop.setProperty("businessBenefit", businessBenefit);
            prop.setProperty("businessLat","");
            prop.setProperty("businessLng","");
            prop.setProperty("businessType", "1");
            prop.setProperty("businessGroup", businessGroup);
            String encodedString = encodeString(prop);

            try{
                //url=new URL("http://192.168.0.2:8181/ttowang/businessAdd.do");
                url=new URL("http://" + ip + ":8080/ttowang/businessUpdate.do");
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setUseCaches(false);

                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                out = new DataOutputStream(urlConnection.getOutputStream());

                out.writeBytes(encodedString);

                out.flush();    //서버로 버퍼의 내용 전송

                buf = new BufferedInputStream(urlConnection.getInputStream());
                bufreader = new BufferedReader(new InputStreamReader(buf,"utf-8"));

                String line=null;
                String result="";

                while((line=bufreader.readLine())!=null){
                    result += line;
                }

                return result;

            }catch(Exception e){
                e.printStackTrace();
                return "";
            }finally{
                urlConnection.disconnect();  //URL 연결해제
            }
        }
        protected void onPostExecute(String result){  //Thread 이후 UI 처리 result는 Thread의 리턴값!!!
            Log.i("myBusinessShop - ", " 내 매장 등록");
            Log.i("서버에서 받은 전체 내용 : ", result);

            if(!result.equals("")){

                myBusinessShopItem item = new myBusinessShopItem();

                item.setBusinessType("1");
                item.setBusinessId(businessId);
                item.setBusinessLicense(businessLicense);
                item.setBusinessName(businessName);
                item.setBusinessTel(businessTel);
                item.setBusinessTel(businessTel);
                item.setBusinessInfo(businessInfo);
                item.setBusinessTime(businessTime);
                item.setBusinessAddress(businessAddress);
                item.setBusinessMenu(businessMenu);
                item.setBusinessBenefit(businessBenefit);
                item.setBusinessGroup(businessGroup);

                myBusinessShopAdapter.listViewItemList.set(nowposition, item);
                myBusinessShop.adapter.notifyDataSetChanged();
                Log.i("매장 어댑터 ","새로 고친다");
                //stamp.spinnerRefresh();
            }else{
                Toast.makeText(myBusinessStoreEdit.mContext,"수정 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
