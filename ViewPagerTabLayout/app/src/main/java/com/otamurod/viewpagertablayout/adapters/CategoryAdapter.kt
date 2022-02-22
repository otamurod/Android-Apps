package com.otamurod.viewpagertablayout.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.otamurod.viewpagertablayout.CategoryFragment
import com.otamurod.viewpagertablayout.models.Category

class CategoryAdapter(var categoryList: List<Category>, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return categoryList.size
    }

    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(categoryList[position].imageList)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categoryList[position].title
    }
}