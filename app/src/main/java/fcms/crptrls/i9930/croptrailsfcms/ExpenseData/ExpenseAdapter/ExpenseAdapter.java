package fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseAdapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.Datum;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.ExpenseData;
import fcms.crptrls.i9930.croptrailsfcms.R;

/**
 * Created by hp on 03-07-2018.
 */
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private String[] mDataSet;
    Context context;
    List<Datum> expenseData;

    public ExpenseAdapter(List<Datum> expenseData, Context context) {
        this.context = context;
        this.expenseData = expenseData;
        //Log.d("Data:", "TaskRecyclerAdapter :" + farmImages);
    }

    public ExpenseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_content, parent, false);
        ExpenseAdapter.ViewHolder vh = new ExpenseAdapter.ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(final ExpenseAdapter.ViewHolder holder, int position) {
        Datum datum = expenseData.get(position);


        holder.date.setText(datum.getExpDate());
        holder.comment.setText(datum.getComment());
        holder.amount.setText(datum.getAmount());

        //String img_url=datum.getImgUrl();

        if(datum.getImgUrl()!=null) {
            if (datum.getImgUrl().equals("null")||datum.getImgUrl().equals("")) {
                holder.expense_image.setImageDrawable(context.getResources().getDrawable(R.drawable.cloud));
            } else {

                Uri uriprofile = Uri.parse(datum.getImgUrl());
                //Picasso.with(context).load(uriprofile).into(holder.expense_image);
                RequestOptions options = new RequestOptions()
                        .placeholder(R.drawable.cloud)
                        .error(R.drawable.leaf);
                Glide.with(context).load(uriprofile).apply(options).into(holder.expense_image);
            }
        }

       /* if (farm_images.getTaskTitle() != null) {
            holder.tvtitle.setText(farm_images.getTaskTitle());
        }*/
      /* if(farm_images.getFarm_image_link()!=null){
           holder.farm_image.setImageResource();
       }*/
       /* RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.addfarm)
                .error(R.drawable.addfarmnew);

        if (farm_images.getFarm_image_link() != null) {
            if (farm_images.getFarm_image_link().equals("null")) {
                holder.farm_image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_person_green_24dp));
            } else {
                Uri uriprofile = Uri.parse(farm_images.getFarm_image_link());
                Glide.with(context).load(uriprofile).apply(options).into(holder.farm_image);

            }
        }*/
    }

    @Override
    public void onBindViewHolder(ExpenseAdapter.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    public int getItemCount() {
        return expenseData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView expense_image;
        TextView amount,comment,date;

        public ViewHolder(View v) {
            super(v);
            expense_image = (ImageView) v.findViewById(R.id.img_expense_recycler_content);
            amount=(TextView)v.findViewById(R.id.amount_expense_content);
            comment=(TextView)v.findViewById(R.id.comment_exp_content);
            date=(TextView)v.findViewById(R.id.date_exp_content);

        }
    }


}
