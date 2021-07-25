package com.tech.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.tech.assignment.databinding.ListItemBinding
import com.tech.assignment.model.ZipCodeModel
import io.realm.RealmResults

class HomeScreenAdapter(val context: Context, val zipCodeModel: RealmResults<ZipCodeModel>) :
    RecyclerView.Adapter<HomeScreenAdapter.HomeScreenViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup, i: Int
    ): HomeScreenViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)

        return HomeScreenViewHolder(
            ListItemBinding.inflate(inflater, viewGroup, false)
        )
    }

    override fun onBindViewHolder(
        @NonNull holder: HomeScreenViewHolder, i: Int
    ) {
        //setting the data on the UI field
        holder.binding.textView.text = zipCodeModel.get(i)?.pinCode
    }

    override fun getItemCount() = zipCodeModel.size

    class HomeScreenViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}