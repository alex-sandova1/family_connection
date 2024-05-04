import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.familyconnection.FAQsClass
import com.example.familyconnection.R

class FAQAdapter(private val faqList: List<FAQsClass>) : RecyclerView.Adapter<FAQAdapter.FAQViewHolder>() {

    class FAQViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val question : TextView = itemView.findViewById(R.id.question)
        val answer : TextView = itemView.findViewById(R.id.answer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.faq_item, parent, false)
        return FAQViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FAQViewHolder, position: Int) {
        val faq : FAQsClass = faqList[position]
        holder.question.text = faq.questions
        holder.answer.text = faq.answers
    }

    override fun getItemCount() = faqList.size
}