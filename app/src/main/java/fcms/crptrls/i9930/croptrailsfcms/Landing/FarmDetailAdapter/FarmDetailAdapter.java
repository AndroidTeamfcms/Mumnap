package fcms.crptrls.i9930.croptrailsfcms.Landing.FarmDetailAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.DataHandler.DataHandler;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.Datum;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailsActivity;
import fcms.crptrls.i9930.croptrailsfcms.Farm_Farmer_Details.FarmDetailsUpdate.FarmDetailsUpdateActivity;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmResult;
import fcms.crptrls.i9930.croptrailsfcms.R;

/**
 * Created by hp on 03-07-2018.
 */
public class FarmDetailAdapter extends RecyclerView.Adapter<FarmDetailAdapter.ViewHolder> implements Filterable {
    private String[] mDataSet;
    Context context;
    List<FetchFarmResult> fetchFarmResults;
    List<FetchFarmResult> fetchFarmResultsFiltered;
    private FarmDetailAdapterListener farmDetailAdapterListener;
    //FarmDetailAdapterListner listner;

    public FarmDetailAdapter( Context context,List<FetchFarmResult> fetchFarmResults/*FarmDetailAdapterListener farmDetailAdapterListener*/) {
        this.context = context;
        this.fetchFarmResults = fetchFarmResults;
        this.fetchFarmResultsFiltered=fetchFarmResults;
        this.farmDetailAdapterListener=farmDetailAdapterListener;

        //Log.d("Data:", "TaskRecyclerAdapter :" + farmImages);
    }

    public FarmDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fetch_farm_content, parent, false);
        FarmDetailAdapter.ViewHolder vh = new FarmDetailAdapter.ViewHolder(v);
        return vh;
    }

    public void onBindViewHolder(final FarmDetailAdapter.ViewHolder holder, int position) {
       final FetchFarmResult fetchFarmResult = fetchFarmResultsFiltered.get(position);

        String firstLetter="";

        firstLetter=fetchFarmResult.getName().substring(0,1).toUpperCase();

        String cap = fetchFarmResult.getName().substring(1);

        holder.exp_area_in_acer.setText(fetchFarmResult.getExpArea());
        holder.farm_address.setText(fetchFarmResult.getAddL1());
        if(fetchFarmResult.getExpHarvestDate()!=null){
        holder.farm_harvest_date.setText(fetchFarmResult.getExpHarvestDate().toString());}
        holder.farm_lot_no.setText(fetchFarmResult.getLotNo());
        holder.farmer_name.setText(firstLetter+cap);
        holder.farmer_name_single.setText(firstLetter);

/*
        holder.date.setText(fetchFarmResult.getExpDate());
        holder.comment.setText(datum.getComment());
        holder.amount.setText(datum.getAmount());*/

        //String img_url=datum.getImgUrl();

       /* if(datum.getImgUrl()!=null) {
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
        }*/

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
    public void onBindViewHolder(FarmDetailAdapter.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    public int getItemCount() {
        return fetchFarmResultsFiltered.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView farmer_name_single,farm_lot_no,exp_area_in_acer,farmer_name,farm_harvest_date,farm_address;

        public ViewHolder(View v) {
            super(v);
            farmer_name_single = (TextView) v.findViewById(R.id.farmer_single_tv);
            farmer_name=(TextView)v.findViewById(R.id.farmer_name);
            farm_lot_no=(TextView)v.findViewById(R.id.farm_lot_no);
            exp_area_in_acer=(TextView)v.findViewById(R.id.exp_area_in_acer);
            farm_harvest_date=(TextView)v.findViewById(R.id.farm_harvest_date);
            farm_address=(TextView)v.findViewById(R.id.farm_address);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final FetchFarmResult fetchFarmResult = fetchFarmResultsFiltered.get(position);
                    DataHandler.newInstance().setFetchFarmResult(fetchFarmResult);
                    Log.e("TAG",fetchFarmResult.getName());
                    Intent intent = new Intent(context, FarmDetailsActivity.class);
                   // DataHandler.newInstance().setFarmnum(farmers.getFarmnum());
                    //intent.putExtra("farm_num",farmers.getFarmnum());
                   // progressDialog.dismiss();
                    v.getContext().startActivity(intent);
                    //((Activity)v.getContext()).finish();
                }
            });
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    fetchFarmResultsFiltered = fetchFarmResults;
                } else {
                    List<FetchFarmResult> filteredList = new ArrayList<>();
                    for (FetchFarmResult fetchFarmResult : fetchFarmResults) {
                        if (fetchFarmResult.getName().toLowerCase().contains(charString.toLowerCase()) || fetchFarmResult.getAddL1().toLowerCase().contains(charString.toLowerCase()) ||
                                fetchFarmResult.getLotNo().toLowerCase().contains(charString.toLowerCase())  ) {
                            filteredList.add(fetchFarmResult);
                        }
                    }
                    fetchFarmResultsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = fetchFarmResultsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                fetchFarmResultsFiltered = (ArrayList<FetchFarmResult>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    public interface FarmDetailAdapterListener {
        void OnFarmSelected(FetchFarmResult fetchFarmResult);
    }

}
