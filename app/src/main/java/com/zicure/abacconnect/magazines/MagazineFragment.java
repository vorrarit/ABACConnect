package com.zicure.abacconnect.magazines;

import android.app.DownloadManager;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zicure.abacconnect.ApplicationContext;
import com.zicure.abacconnect.alumni.search.Alumni;
import com.zicure.abacconnect.alumni.search.Users;
import com.zicure.abacconnect.alumni.search.Works;
import com.zicure.abacconnect.api.DataLayer;
import com.zicure.abacconnect.api.RequestInterface;
import com.zicure.abacconnect.R;
import com.zicure.abacconnect.api.DataLayerListener;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.jobs.Jobss;
import com.zicure.abacconnect.news.Newses;
import com.zicure.abacconnect.special.deals.SpecialDeals;

import java.io.File;
import java.util.List;


/**
 * Created by DUMP129 on 9/24/2015.
 */
public class MagazineFragment extends Fragment implements View.OnClickListener, DataLayerListener, RequestInterface {
    private RecyclerView recyclerViewVerticalYears = null;
    private RecyclerYearsAdapter recyclerYearsAdapter = null;
    private ImageView imgViewSelectedCoverMagazine, imgViewMagazineDownload;
    private TextView tvViewCount, tvSelectedMagazineMonthly;
    private DownloadManager downloadManager;
    private IntentFilter intentFilter;
    private long downloadReference;
    private String magIntro, magImg, magPath, magViewCount;
    private int magId;

    private final String PATH = "ABAC/Download/Magazines";
    private File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + PATH);

    private DataLayer dataLayer;

    private boolean isSetFirstMagazine = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_magazine, container, false);

        // Casting
        recyclerViewVerticalYears = (RecyclerView) v.findViewById(R.id.recyclerViewVerticalYears);
        imgViewMagazineDownload = (ImageView) v.findViewById(R.id.imgViewMagazineDownload);
        tvViewCount = (TextView) v.findViewById(R.id.tvViewCount);
        tvSelectedMagazineMonthly = (TextView) v.findViewById(R.id.tvSelectedMagazineMonthly);
        imgViewSelectedCoverMagazine = (ImageView) v.findViewById(R.id.imgViewSelectedCoverMagazine);

        // Set RecyclerAdapter
        recyclerYearsAdapter = new RecyclerYearsAdapter(ApplicationContext.getInstance().getContext(), null);
        recyclerYearsAdapter.sendRecycler(recyclerViewVerticalYears);
        recyclerYearsAdapter.setRecyclerYearsVerticalListener(this);
        recyclerYearsAdapter.setFirstDataVerticalListener(this);
        recyclerYearsAdapter.loadYear();

        // Set Onclick
        imgViewMagazineDownload.setOnClickListener(this);

        // Set filter to only when download is complete and register broadcast receiver
        intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);

        dataLayer = new DataLayer(ApplicationContext.getInstance().getContext());

        return v;
    }

    // Set Download ClickListener
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgViewMagazineDownload && magPath != null) {
            createDirectory();
            savePdfFile();
        }
    }

    // Create Directory.
    private void createDirectory() {
        dir.mkdirs();
    }

    private void savePdfFile() {
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri download_uri = Uri.parse(magPath);
        DownloadManager.Request request = new DownloadManager.Request(download_uri);

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // Restrict the types of networks over which this download may proceed.
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_WIFI);

        // Set whether this download may proceed over a roaming connection.
        request.setAllowedOverRoaming(false);

        // Set the title of this download, to be displayed in notifications (if enabled).
        request.setTitle(magIntro);

        String nameOfFile = URLUtil.guessFileName(magPath, null, MimeTypeMap.getFileExtensionFromUrl(magPath));
        request.setDestinationInExternalFilesDir(ApplicationContext.getInstance().getContext(), dir.getAbsolutePath(), nameOfFile);

        // Enqueue a new download and same the referenceId
        downloadReference = downloadManager.enqueue(request);
    }

    public void updateViewCount() {
        dataLayer.setDataLayerListener(this);
        dataLayer.addViewCount(magId);
    }

    @Override
    public void addMagazine(List<Magazine> magazineList) {}

    @Override
    public void addYears(List<String> years) {}

    @Override
    public void addViewCounts(String viewCount) {
        tvViewCount.setText(viewCount);
    }

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

    @Override
    public void onClickInMagazineListener(View v, int position, List<Magazine> listMagazines) {
        Magazine magazine = listMagazines.get(position);
        magId = magazine.id;
        magImg = magazine.magazine_thumbnail;
        Glide.with(getActivity())
                .load(magImg)
                .centerCrop()
                .into(imgViewSelectedCoverMagazine);

        magIntro = magazine.magazine_intro;
        tvSelectedMagazineMonthly.setText(magIntro);

        magPath = magazine.magazine_path;
        updateViewCount();
    }

    @Override
    public void onNewsClickListener(View v, List<Newses> listNewses, int position) {}

    @Override
    public void onSpecialDealsClickListener(View v, List<SpecialDeals> listDeals, int position) {}

    @Override
    public void firstDataListener(int position, List<Magazine> magazineList) {
        if (isSetFirstMagazine == false) {
            Magazine magazine = magazineList.get(0);
            magId = magazine.id;
            magImg = magazine.magazine_thumbnail;
            Glide.with(getActivity())
                    .load(magImg)
                    .centerCrop()
                    .into(imgViewSelectedCoverMagazine);

            magIntro = magazine.magazine_intro;
            tvSelectedMagazineMonthly.setText(magIntro);

            magPath = magazine.magazine_path;
            magViewCount = magazine.view_count;
            tvViewCount.setText(magViewCount);
            isSetFirstMagazine = true;
        }
    }

    @Override
    public void onBusinessClickListener(View v, List<BusinessConnections> listBusiness, int position) {}

    @Override
    public void onJobsClickListener(View v, List<Jobss> listJobs, int position) {}
}