package vtj.ir.mcimtn;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Created by JavaDroid on 7/18/2015.
 */
public class AdapterContact extends RecyclerView.Adapter<AdapterContact.MainHolder> {

    private List<ModelContact> contacts;
    private ClickAction clickAction;

    public AdapterContact(List<ModelContact> list, ClickAction Click) {
        this.contacts = list;
        this.clickAction = Click;
    }

    public interface ClickAction {
        void click(ModelContact contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    @Override
    public void onBindViewHolder(MainHolder mainHolder, int position) {
        final ModelContact contact = contacts.get(position);
        mainHolder.tvName.setText(contact.getName());
        mainHolder.tvPhone.setText(contact.getNumber());

        if (contact.isMtn)
            mainHolder.mainLin.setBackgroundColor(Color.parseColor("#f5cd00"));
        else
            mainHolder.mainLin.setBackgroundColor(Color.parseColor("#00b8f5"));
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.adapter_item_order, viewGroup, false);
        return new MainHolder(itemView);

    }

    public static class MainHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPhone;
        private LinearLayout mainLin;

        public MainHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvName);
            tvPhone = (TextView) v.findViewById(R.id.tvPhone);
            mainLin = (LinearLayout) v.findViewById(R.id.mainLin);
        }


    }
}
