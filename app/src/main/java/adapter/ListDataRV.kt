package adapter

import `interface`.CardListener
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vintech.visprog_321.R
import com.vintech.visprog_321.databinding.CardDataBinding
import model.Animal

class ListDataRV(val ListData: ArrayList<Animal>, val cardListener: CardListener) :
    RecyclerView.Adapter<ListDataRV.viewHolder>() {
    class viewHolder(val itemView: View, val cardListener: CardListener) :
        RecyclerView.ViewHolder(itemView) {
        val binding = CardDataBinding.bind(itemView)

        fun setData(data: Animal) {
            binding.textViewNama.text = data.namahewan
            binding.textViewJenis.text = data.jenishewan
            binding.textViewUsia.text = data.usiahewan.toString()
            if (data.imageUri.isNotEmpty()) {
                binding.imageView.setImageURI(Uri.parse(data.imageUri))
            }
            binding.imageButtonDelete.setOnClickListener {
                cardListener.onCardClick1("delete", data.id)
            }
            binding.imageButtonEdit.setOnClickListener {
                cardListener.onCardClick1("edit", data.id)
            }
            binding.imageButtonSound.setOnClickListener {
                cardListener.onCardClick1("sound", data.id)
            }
            binding.imageButtonFeed.setOnClickListener {
                cardListener.onCardClick1("feed", data.id)
            }

            itemView.setOnClickListener {
                cardListener.onCardClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_data, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(ListData[position])
    }

    override fun getItemCount(): Int {
        return ListData.size
    }
}