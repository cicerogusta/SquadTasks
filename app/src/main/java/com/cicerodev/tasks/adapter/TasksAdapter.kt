package com.cicerodev.tasks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cicerodev.tasks.R
import com.cicerodev.tasks.databinding.TasksItemBinding
import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.ui.viewmodels.TasksFragmentViewModel

class TasksAdapter(
    private var tasksList: MutableList<Task>, private val viewModel: TasksFragmentViewModel
) :
    RecyclerView.Adapter<TasksAdapter.MyViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.MyViewHolder {
        val itemLista = LayoutInflater.from(parent.context)
            .inflate(R.layout.tasks_item, parent, false)
        return MyViewHolder(itemLista)
    }


    override fun onBindViewHolder(holder: TasksAdapter.MyViewHolder, position: Int) {
        val task = tasksList[position]
        holder.binding.task = task

//        holder.binding.button2.setOnClickListener {
//            viewModel.removerCartao(cartao)
//            listaCartoes.remove(cartao)
//            viewModel.returnCards().value?.remove(cartao)
//            notifyItemRemoved(position)
//        }




    }


    override fun getItemCount(): Int {
        return tasksList.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding: TasksItemBinding by lazy { TasksItemBinding.bind(itemView) }
    }

}