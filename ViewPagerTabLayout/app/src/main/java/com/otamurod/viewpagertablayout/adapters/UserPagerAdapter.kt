package com.otamurod.viewpagertablayout.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.otamurod.viewpagertablayout.UserFragment
import com.otamurod.viewpagertablayout.models.User

class UserPagerAdapter(
    val userList: List<User>,
    val titleList: List<String>,
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int {
        return userList.size
    }

    override fun getItem(position: Int): Fragment {
        /*if (position == 0){
            return HomeFragment()
        }*/
        return UserFragment.newInstance(userList[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

}