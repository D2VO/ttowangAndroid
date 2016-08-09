package com.app.ttowang.ttowang.Main.Setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.ttowang.ttowang.Main.MainActivity;
import com.app.ttowang.ttowang.ModeChange.ChangeModeMain;
import com.app.ttowang.ttowang.R;


public class Mainsetting extends Fragment {
    public final static String ITEMS_COUNT_KEY = "home$ItemsCount";

    View view;
    Button modeChange;
    Button myInfo;
    Context context;

    TextView myName, myPhone, myEmail;


    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor Edit;

    public static Mainsetting createInstance(int itemsCount) {
        Mainsetting Setting = new Mainsetting();
        Bundle bundle = new Bundle();
        bundle.putInt(ITEMS_COUNT_KEY, itemsCount);
        Setting.setArguments(bundle);
        return Setting;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.setting,container, false);
        modeChange = (Button) view.findViewById(R.id.modeChange);
        myInfo=(Button) view.findViewById(R.id.myInfo);//위에꺼처럼 myInfo해

        myName=(TextView)view.findViewById(R.id.myName);//xml에서 TextView id가져와
        myPhone=(TextView)view.findViewById(R.id.myPhone);//xml에서 TextView id가져와
        myEmail=(TextView)view.findViewById(R.id.myEmail);//xml에서 TextView id가져와

        sharedPreferences = getActivity().getSharedPreferences("setting",getActivity().MODE_PRIVATE);//초기화
        Edit = sharedPreferences.edit();

        Edit.putString("name", "이재훈");//데이터 저장하기
        Edit.putString("phone","01031933763");
        Edit.putString("email","hoon123@naver.com");
        Edit.commit();//커밋




        String myname = sharedPreferences.getString("name", "" );//값 가져와서 String에 저장
        String Phone = sharedPreferences.getString("phone", "" );
        String Email = sharedPreferences.getString("email", "" );
        myName.setText(myname);//가져온 String myName에다가 넣어
        myPhone.setText(Phone);
        myEmail.setText(Email);



        modeChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity.Edit.putString("nowMode", "on");
                Log.i("setting - ", "가맹점 모드 : on");
                MainActivity.Edit.commit();
                Intent intent = new Intent(MainActivity.mContext, ChangeModeMain.class);
                Toast.makeText(getActivity(), "사업자 모드 실행", Toast.LENGTH_SHORT).show();
                getActivity().startActivityForResult(intent, 0);
            }
        });


        myInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), myInfoEdit.class);//ChangeModeMain수정
                startActivity(intent);

            }
        });


        //myinfo.~새창열리게해
        //Intent intent = new Intent(getContext(),business.class);
        //startActivity(intent);
       return view;
    }

}
