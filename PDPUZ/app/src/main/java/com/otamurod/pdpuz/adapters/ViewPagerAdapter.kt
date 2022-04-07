package com.otamurod.pdpuz.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.otamurod.pdpuz.fragments.group_fragments.GroupToOpenFragment
import com.otamurod.pdpuz.fragments.group_fragments.OpenGroupFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, var courseId: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                OpenGroupFragment.newInstance(courseId)
            }
            1 -> {
                GroupToOpenFragment.newInstance(courseId)
            }
            else -> {
                OpenGroupFragment.newInstance(courseId)
            }
        }
    }

}
