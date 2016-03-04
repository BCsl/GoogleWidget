package github.hellocsl.example.googlewidget.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by HelloCsl(cslgogogo@gmail.com) on 2016/3/1 0001.
 */
public class TestFragmentAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragments;


    public TestFragmentAdapter(List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        this.mFragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments==null?0:mFragments.size();
    }
}
