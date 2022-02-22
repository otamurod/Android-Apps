package com.otamurod.fragments2

import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.labo.kaji.fragmentanimations.CubeAnimation
import com.labo.kaji.fragmentanimations.MoveAnimation
import kotlinx.android.synthetic.main.fragment_first.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//class FirstFragment : Fragment(), MyInterface {
class FirstFragment : Fragment() {

    lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_first, container, false)

        setHasOptionsMenu(true)


        root.register_tv.setOnClickListener {

            val secondFragment = SecondFragment()
            fragmentManager?.beginTransaction() // ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                ?.replace(R.id.container, secondFragment) //replace fragment
                ?.addToBackStack(secondFragment.toString())
                ?.commit()
        }

        root.button.setOnClickListener {
            /*val str = root.edit_txt.text.toString()
            val bundle = Bundle()
            bundle.putString("username", str)

            val secondFragment = SecondFragment()
            secondFragment.arguments = bundle
            fragmentManager?.beginTransaction()
                ?.replace(R.id.container, secondFragment) //replace fragment
                ?.addToBackStack(secondFragment.toString())
                ?.commit()*/

            /*val str = root.username.text.toString()

            val secondFragment = SecondFragment.newInstance(str)
            fragmentManager?.beginTransaction() // ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                ?.replace(R.id.container, secondFragment) //replace fragment
                ?.addToBackStack(secondFragment.toString())
                ?.commit()*/

            val str = root.username.text.toString()

            val homeFragment = HomeFragment()
            fragmentManager?.beginTransaction() // ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
                ?.replace(R.id.container, homeFragment) //replace fragment
                ?.addToBackStack(homeFragment.toString())
                ?.commit()

        }

        return root
    }

    fun showData(str:String){
        root.username.setText(str)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter){
            return MoveAnimation.create(MoveAnimation.UP, enter, 1000)
        }
        else{
            return CubeAnimation.create(CubeAnimation.UP, enter, 1000)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when(itemId){
            R.id.add ->{
                Toast.makeText(root.context, "Click", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*override fun setResult(s: String) {
        root.edit_txt.setText(s)
    }*/
}