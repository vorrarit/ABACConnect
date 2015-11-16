package com.zicure.abacconnect.api;

import com.zicure.abacconnect.alumni.search.Alumni;
import com.zicure.abacconnect.alumni.search.Users;
import com.zicure.abacconnect.alumni.search.Works;
import com.zicure.abacconnect.business.connect.BusinessConnections;
import com.zicure.abacconnect.jobs.Jobss;
import com.zicure.abacconnect.magazines.Magazine;
import com.zicure.abacconnect.news.Newses;
import com.zicure.abacconnect.special.deals.SpecialDeals;

import java.util.List;

/**
 * Created by DUMP129 on 10/1/2015.
 */

public interface DataLayerListener {
    public void addMagazine(List<Magazine> magazineList);
    public void addYears(List<String> years);
    public void addViewCounts(String viewCount);
   // public void getProfile(List<Profile> profileList);
    public void fetchNews(List<Newses> newsesList);
    public void fetchSpecialDeals(List<SpecialDeals> specialDealList);
    public void fetchBusiness(List<BusinessConnections> businessConnectionsList);
    public void fetchAlumni(List<Alumni> usersList);
    public void fetchJobs(List<Jobss> jobssList);
}
