package com.app.ttowang.ttowang.Main.Home.coupon;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.ttowang.ttowang.Main.Home.home;
import com.app.ttowang.ttowang.Main.MainActivity;
import com.app.ttowang.ttowang.R;

import java.util.ArrayList;

/**
 * Created by srpgs2 on 2016-08-01.
 */
public class couponItemAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<couponItemClass> listViewItemList = new ArrayList<couponItemClass>() ;
    private Button useCoupon;
    private TextView couponName, couponNumber;

    private LinearLayout thisCoupon;
    // ListViewAdapter의 생성자
    public couponItemAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.couponitem, parent, false);
        }

        useCoupon = (Button)convertView.findViewById(R.id.useCoupon);
        couponName = (TextView)convertView.findViewById(R.id.couponName);
        couponNumber = (TextView)convertView.findViewById(R.id.couponNumber);
        thisCoupon = (LinearLayout)convertView.findViewById(R.id.thisCoupon);
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        couponItemClass couponItemClass = listViewItemList.get(position);

        couponName.setText(home.myAllBusinessCouponName.get(home.nowbusiness).get(position));
        couponNumber.setText(home.myAllBusinessCouponNum.get(home.nowbusiness).get(position));
        if(home.myAllBusinessCouponUse.get(home.nowbusiness).get(position).equals("0")){    //사용했으면
            thisCoupon.setBackgroundColor(Color.parseColor("#ededed"));
            useCoupon.setBackgroundColor(Color.parseColor("#d8d8d8"));
            useCoupon.setText("사용 완료");
            //useCoupon.setClickable(false);
        }
        // 아이템 내 각 위젯에 데이터 반영

        useCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(useCoupon.getText().equals("사용 하기")) {
                    Toast.makeText(MainActivity.mContext, position + "번째", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.mContext, position + "번째 사용 완료", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.

    public void clearItem(){
        listViewItemList.clear();
    }

    public void addItem() {
        couponItemClass item = new couponItemClass();
        listViewItemList.add(item);
    }

}
