package my.edu.tarc.chat3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter(val context: Context, val messageList: ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val  ITEM_RECEIVE = 1;
    val  ITEM_SEND = 2;

     class SendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
         val  sendMessage = itemView.findViewById<TextView>(R.id.txt_send_message)
     }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val  receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)
    }

    override fun getItemViewType(position: Int): Int {

        val  currenMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currenMessage.senderId)){
            return ITEM_SEND
        }else{
            return ITEM_RECEIVE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            //inflate receive
            val view: View = LayoutInflater.from(context).inflate(R.layout.receive,
                parent, false)
            return ReceiveViewHolder(view)
        }else{
            val view: View = LayoutInflater.from(context).inflate(R.layout.send,
                parent, false)
            return SendViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage = messageList[position]

        if (holder.javaClass == SendViewHolder::class.java){
            //do the stuff for send view holder

            val  viewHolder = holder as SendViewHolder
            holder.sendMessage.text = currentMessage.message
        }else{
            val  viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

}