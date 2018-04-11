package appsinthesky.com.firebasewrite

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import appsinthesky.com.firebasewrite.adapters.CakeCardAdapter
import appsinthesky.com.firebasewrite.model.CakeModel
import appsinthesky.com.firebasewrite.model.Cake
import kotlinx.android.synthetic.main.main_layout.*
import java.util.*

class MainActivity : AppCompatActivity(), Observer {

    private var mCakeListAdapter: CakeCardAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        CakeModel

        val dataList: ListView = findViewById(R.id.person_list)


        val data:ArrayList<Cake> = ArrayList()
        mCakeListAdapter = CakeCardAdapter(this, R.layout.cake_card_item, data)
        dataList.adapter = mCakeListAdapter

        add_cake.setOnClickListener {
            addCake()
        }

    }

    private fun addCake() {
        val intent = Intent(this, AddCakeActivity::class.java)
        startActivity(intent)
    }

    override fun update(p0: Observable?, p1: Any?) {
        refresh()
    }

    private fun refresh() {
        mCakeListAdapter?.clear()

        val data = CakeModel.getData()
        if (data != null) {
            mCakeListAdapter?.clear()
            mCakeListAdapter?.addAll(data)
            mCakeListAdapter?.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        CakeModel.addObserver(this)
        refresh()
    }


    override fun onStop() {
        super.onStop()
        CakeModel.deleteObserver(this)

    }

    override fun onPause() {
        super.onPause()
        CakeModel.deleteObserver(this)
    }


}
