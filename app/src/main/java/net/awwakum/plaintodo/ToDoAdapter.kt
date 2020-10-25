package net.awwakum.plaintodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.awwakum.plaintodo.model.ToDo

class ToDoAdapter(
    private val todoList: ArrayList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.items_todo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.todoId.text = todoList[position].id
        holder.todoName.text = todoList[position].name
        holder.todoDesc.text = todoList[position].description
    }

    override fun getItemCount(): Int = todoList.size

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        val todoId: TextView = itemsView.findViewById(R.id.todoId)
        val todoName: TextView = itemsView.findViewById(R.id.todoName)
        val todoDesc: TextView = itemsView.findViewById(R.id.todoDescription)
    }

}