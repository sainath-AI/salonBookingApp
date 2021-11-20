package com.masai.sainath.salonbookingapp.view.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.masai.sainath.salonbookingapp.model.AdminModel
import com.masai.sainath.salonbookingapp.view.interfaces.OnItemClickListener
import com.masai.sainath.salonbookingapp.R

import com.ramotion.foldingcell.FoldingCell

class User1viewholder(itemView: View, private val itemClickListener: OnItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    private lateinit var mIvImage: ImageView

    private lateinit var mTvMallname: TextView

    private lateinit var mIvImageTitle: ImageView

    private lateinit var mTvMallnameTitle: TextView

    private lateinit var mTvDistance: TextView

    private lateinit var mTvSlotsAvailable: TextView

    private lateinit var foldingCell: FoldingCell

    private lateinit var mBtnBook: Button

    fun setData(mallItem: AdminModel) {
        itemView.apply {
            mIvImage = findViewById(R.id.ivImage)
            mTvMallname = findViewById(R.id.tvMallName)
            mIvImageTitle = findViewById(R.id.ivImageTitle)
            mTvMallnameTitle = findViewById(R.id.tvMallNameTitle)
            mTvDistance = findViewById(R.id.tvDistance)
            mTvSlotsAvailable = findViewById(R.id.tvSlotsAvailable)
            foldingCell = findViewById(R.id.folding_cell)
            mBtnBook = findViewById(R.id.btnBook)
            val mBtnDirections: Button = findViewById(R.id.btnDirections)

            Glide.with(mIvImage).load(mallItem.imgurl).into(mIvImage)
            mTvMallname.text = mallItem.salonname
            Glide.with(mIvImageTitle).load(mallItem.imgurl).into(mIvImageTitle)
            mTvMallnameTitle.text = mallItem.salonname
            mTvDistance.text = mallItem.barbarname
            mTvSlotsAvailable.text = mallItem.location

            foldingCell.setOnClickListener {
                foldingCell.toggle(false)
            }
            mBtnBook.setOnClickListener {
                itemClickListener.onItemClicked(mallItem)
            }
            mBtnDirections.setOnClickListener {
                itemClickListener.onDirectionsClicked()
            }
        }

    }
}