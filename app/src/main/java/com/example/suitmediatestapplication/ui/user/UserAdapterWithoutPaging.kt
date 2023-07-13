package com.example.suitmediatestapplication.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatestapplication.R
import com.example.suitmediatestapplication.data.remote.DataItem
import com.example.suitmediatestapplication.databinding.ItemUserBinding

class UserAdapterWithoutPaging(private val listUser: List<DataItem>) : RecyclerView.Adapter<UserAdapterWithoutPaging.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(private val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : DataItem) {
            binding.tvUserName.text = "${data.firstName} ${data.lastName}"
            binding.tvEmail.text = data.email
            Glide.with(itemView.context)
                .load(data.avatar)
                .placeholder(R.drawable.ic_photo_3x)
                .into(binding.ivUser)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUser[position]
        if (user != null) {
            holder.bind(user)
            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(
                    "${user.firstName} ${user.lastName}"
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    interface OnItemClickCallback {
        fun onItemClicked(fullName : String)
    }


}