package com.gustam.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Figure>()
    private var title  = "Mode List"
    private var mode: Int = 0

    companion object {
        private const val  STATE_TITLE = "state_string"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_figureww2.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
                list.addAll(getListFigure())
                showRecyclerList()
                mode = R.id.action_list
    } else {
        title = savedInstanceState.getString(STATE_TITLE).toString()
        val stateList = savedInstanceState.getParcelableArrayList<Figure>(STATE_LIST)
        val stateMode = savedInstanceState.getInt(STATE_MODE)

        setActionBarTitle(title)
        if (stateList != null) {
            list.addAll(stateList)
        }
        setMode(stateMode)
    }
}
    fun getListFigure(): ArrayList<Figure> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listFigure = ArrayList<Figure>()
        for (position in dataName.indices) {
            val figure = Figure(
                        dataName[position],
                        dataDescription[position],
                        dataPhoto[position]
            )
            listFigure.add(figure)
        }
        return listFigure
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
        outState.putInt(STATE_MODE, mode)
    }

    private fun showRecyclerList() {
        rv_figureww2.layoutManager = LinearLayoutManager(this)
        val listFigureAdapter = ListFigureAdapter (list)
        rv_figureww2.adapter = listFigureAdapter

        listFigureAdapter .setOnItemClickCallBack(object : ListFigureAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: Figure) {
                showSelectedFigure(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }
        }
        mode = selectedMode
        setActionBarTitle(title)
    }
    private  fun showRecyclerGrid() {
        rv_figureww2.layoutManager = GridLayoutManager(this, 2)
        val gridFigureAdapter = GridFigureAdapter(list)
        rv_figureww2.adapter = gridFigureAdapter

        gridFigureAdapter .setOnItemClickCallBack(object : GridFigureAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: Figure) {
                showSelectedFigure(data)
            }
        })
    }
    private fun showRecyclerCardView() {
        rv_figureww2.layoutManager = LinearLayoutManager(this)
        val cardViewFigureAdapter = CardViewFigureAdapter (list)
        rv_figureww2.adapter = cardViewFigureAdapter
    }
    private fun setActionBarTitle(title: String?){
        supportActionBar?.title = title
    }
    private  fun showSelectedFigure(figure: Figure) {
    Toast.makeText(this, "Kamu Memilih ${figure.name}", Toast.LENGTH_SHORT).show()}
}