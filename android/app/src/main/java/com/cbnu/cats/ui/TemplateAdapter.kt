package com.cbnu.cats.ui
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cbnu.cats.R

data class Template(val name: String, val imageResId: Int)

class TemplateAdapter(
    private val templates: List<Template>,
    private val itemClickListener: (Template) -> Unit
) : RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_template, parent, false)
        return TemplateViewHolder(view)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        holder.bind(templates[position], itemClickListener)
    }

    override fun getItemCount(): Int = templates.size

    class TemplateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val templateImageView: ImageView = itemView.findViewById(R.id.iv_template)
        private val templateNameTextView: TextView = itemView.findViewById(R.id.tv_template_name)

        fun bind(template: Template, itemClickListener: (Template) -> Unit) {
            templateImageView.setImageResource(template.imageResId)
            templateNameTextView.text = template.name
            itemView.setOnClickListener { itemClickListener(template) }
        }
    }
}
