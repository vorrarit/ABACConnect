package com.zicure.abacconnect.special.deals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.alumni.search.Alumni;
import com.zicure.abacconnect.alumni.search.Users;
import com.zicure.abacconnect.alumni.search.Works;
import com.zicure.abacconnect.api.ApiConfig;
import com.zicure.abacconnect.api.DataLayer;
import com.zicure.abacconnect.api.DataLayerListener;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.jobs.Jobss;
import com.zicure.abacconnect.magazines.Magazine;
import com.zicure.abacconnect.news.Newses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by DUMP129 on 10/26/2015.
 */
public class SpecialDealViewFragment extends Fragment implements View.OnClickListener, DataLayerListener {
    private TextView tvSpecialDealViewName, tvSpecialDealViewExpDate, tvSpecialDealViewConditionDetail, tvSpecialDealViewDiscountDetail;
    private ImageView imgViewSpecialDealViewShopLogo;
    private Button btnSpecialDealViewClaim;
    private String specialDealName, specialDealPromotionDiscountDetail, specialDealConditionDetail, specialDealViewExpDate;
    private String imgUrl;
    private int specialDealId;
    private View v;
    private List<SpecialDeals> dealsList = null;
    private DataLayer dataLayer;
    private int position;

    public SpecialDealViewFragment(View v, List<SpecialDeals> listDeals, int position) {
        this.v = v;
        this.dealsList = listDeals;
        this.position = position;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.special_deal_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgViewSpecialDealViewShopLogo = (ImageView) view.findViewById(R.id.imgViewSpecialDealViewShopLogo);
        tvSpecialDealViewName = (TextView) view.findViewById(R.id.tvSpecialDealViewName);
        tvSpecialDealViewExpDate = (TextView) view.findViewById(R.id.tvSpecialDealViewExpDate);
        tvSpecialDealViewConditionDetail = (TextView) view.findViewById(R.id.tvSpecialDealViewConditionDetail);
        tvSpecialDealViewDiscountDetail = (TextView) view.findViewById(R.id.tvSpecialDealViewDiscountDetail);
        btnSpecialDealViewClaim = (Button) view.findViewById(R.id.btnSpecialDealViewClaim);
        btnSpecialDealViewClaim.setOnClickListener(this);

        specialDealId = dealsList.get(position).id;

        imgUrl = ApiConfig.IMG_URL + dealsList.get(position).deal_thumbnail;
        Glide.with(getActivity())
                .load(imgUrl)
                .into(imgViewSpecialDealViewShopLogo);

        specialDealName = dealsList.get(position).deal_name;
        tvSpecialDealViewName.setText(specialDealName);

        specialDealViewExpDate = dealsList.get(position).deal_expiry_date;

        String convert = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date tmpDate = simpleDateFormat.parse(specialDealViewExpDate);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("d MMM yyyy HH:mm");
            convert = outputDateFormat.format(tmpDate);
            tvSpecialDealViewExpDate.setText(convert);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        specialDealConditionDetail = dealsList.get(position).deal_condition;
        tvSpecialDealViewConditionDetail.setText(specialDealConditionDetail);

        specialDealPromotionDiscountDetail = dealsList.get(position).deal_discount;
        tvSpecialDealViewDiscountDetail.setText(specialDealPromotionDiscountDetail);
    }

    @Override
    public void onClick(View v) {
        String url = "https://buffet-au.ookbee.com/";
        if (!url.startsWith("http:/") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
       /* dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ABACLogin", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");
        dataLayer.setDataLayerListener(this);
        dataLayer.claimDeal(Integer.parseInt(userId), specialDealId);

        backToSpecialDeal();*/
    }

    private void backToSpecialDeal() {
        Fragment fragment = new SpecialDealsFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
        transaction.replace(R.id.main, fragment).commit();
    }

    @Override
    public void addMagazine(List<Magazine> magazineList) {}

    @Override
    public void addYears(List<String> years) {}

    @Override
    public void addViewCounts(String viewCount) {}

    @Override
    public void fetchNews(List<Newses> newsesList) {}

    @Override
    public void fetchSpecialDeals(List<SpecialDeals> specialDealList) {}

    @Override
    public void fetchBusiness(List<BusinessConnections> businessConnectionsList) {}

    @Override
    public void fetchAlumni(List<Alumni> usersList) {}

    @Override
    public void fetchJobs(List<Jobss> jobssList) {}
}
