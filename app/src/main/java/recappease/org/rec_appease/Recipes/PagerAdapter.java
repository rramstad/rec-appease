package recappease.org.rec_appease.Recipes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by jungr on 3/26/18.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numTabs;
    public PagerAdapter(FragmentManager fm, int tabs) {
        super(fm);
        this.numTabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BrowseRecipes br = new BrowseRecipes();
                return br;
            case 1:
                SavedRecipes sr = new SavedRecipes();
                return sr;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
