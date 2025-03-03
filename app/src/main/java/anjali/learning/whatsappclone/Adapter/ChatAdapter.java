package anjali.learning.whatsappclone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import anjali.learning.whatsappclone.R;
import anjali.learning.whatsappclone.model.MessageModel;

public class ChatAdapter extends RecyclerView.Adapter {

    ArrayList<MessageModel> messageModels;
    Context context;
    int SENDER_VIEW_TYPE=1;
    int RECIEVER_VIEW_TYPE=2;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            {View view= LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
                return new SenderViewHolder(view);
            }
        }else {
            {View view= LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
                return new ReceiverViewHolder(view);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return  SENDER_VIEW_TYPE;
        }else{
            return RECIEVER_VIEW_TYPE;
        }
//        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         MessageModel messageModel=messageModels.get(position);
         if(holder.getClass()==SenderViewHolder.class){
             ((SenderViewHolder)holder).senderMsg.setText(messageModel.getMessage());
         }else{
             ((ReceiverViewHolder)holder).receiverMsg.setText(messageModel.getMessage());
         }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }
    //2 viewholder 1 for sender 1 for receiver

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        TextView receiverMsg,receiverTime;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg=itemView.findViewById(R.id.receivertext);
            receiverTime=itemView.findViewById(R.id.receivertime);

        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView senderMsg,senderTime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg=itemView.findViewById(R.id.senderText);
            senderTime=itemView.findViewById(R.id.senderTime);
        }
    }
}
