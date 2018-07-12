package fcms.crptrls.i9930.croptrailsfcms.Landing.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpApiInterface;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseAdapter.ExpenseAdapter;
import fcms.crptrls.i9930.croptrailsfcms.ExpenseData.ExpenseModel.Datum;
import fcms.crptrls.i9930.croptrailsfcms.Landing.FarmDetailAdapter.FarmDetailAdapter;
import fcms.crptrls.i9930.croptrailsfcms.Landing.LandingActivity;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmData;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmResult;
import fcms.crptrls.i9930.croptrailsfcms.Landing.Models.FetchFarmSendData;
import fcms.crptrls.i9930.croptrailsfcms.R;
import fcms.crptrls.i9930.croptrailsfcms.TestRetrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FarmFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FarmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarmFragment extends Fragment implements FarmDetailAdapter.FarmDetailAdapterListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Context context;
    FarmDetailAdapter farmDetailAdapter;
    LinearLayoutManager linearLayoutManager;
    List<FetchFarmResult> farmResults;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView farm_recycler_view;
    ProgressBar progressBar;
    EditText et_search;
    ImageView search_img;

    private OnFragmentInteractionListener mListener;

    public FarmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FarmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FarmFragment newInstance(String param1, String param2) {
        FarmFragment fragment = new FarmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity().getApplicationContext();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_farm, container, false);
        farm_recycler_view=(RecyclerView)view.findViewById(R.id.farm_data_recycler_view);
        et_search=(EditText)view.findViewById(R.id.search_query_et);
        search_img=(ImageView)view.findViewById(R.id.search_img);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar_cyclic);

        progressBar.setVisibility(View.VISIBLE);
        MyAsyncTask myAsyncTask=new MyAsyncTask(context);
        myAsyncTask.execute();

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(farmDetailAdapter!=null) {
                    farmDetailAdapter.getFilter().filter(s.toString());
                }
            }
        });
        search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(farmDetailAdapter!=null) {
                    farmDetailAdapter.getFilter().filter(et_search.getText().toString());
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnFarmSelected(FetchFarmResult fetchFarmResult) {
        Toast.makeText(context, "Selected"+fetchFarmResult.getName(), Toast.LENGTH_SHORT).show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    class MyAsyncTask extends AsyncTask<String,Void,String> {
        Context context;
        public MyAsyncTask(Context context){
            this.context=context;
            //super();
        }

        @Override
        protected String doInBackground(String... strings) {
            ExpApiInterface apiService = RetrofitClientInstance.getRetrofitInstance().create(ExpApiInterface.class);
            FetchFarmSendData fetchFarmSendData=new FetchFarmSendData();

            fetchFarmSendData.setComp_id("1");
            fetchFarmSendData.setCluster_id("1");
            Call<FetchFarmData> fetchFarmDataCall=apiService.fetchFarmDatafncn(fetchFarmSendData);
            fetchFarmDataCall.enqueue(new Callback<FetchFarmData>() {
                @Override
                public void onResponse(Call<FetchFarmData> call, Response<FetchFarmData> response) {

                    try {
                        if (response != null) {
                            FetchFarmData fetchFarmData = response.body();
                            List<FetchFarmResult> fetchFarmResultList = fetchFarmData.getResult();
                            //Toast.makeText(context, fetchFarmData.getMsg(), Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.INVISIBLE);
                            farmDetailAdapter = new FarmDetailAdapter(context, fetchFarmResultList);
                            farm_recycler_view.setHasFixedSize(true);
                            farm_recycler_view.setAdapter(farmDetailAdapter);
                            farmDetailAdapter.notifyDataSetChanged();

                            linearLayoutManager = new LinearLayoutManager(context);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            farm_recycler_view.setLayoutManager(linearLayoutManager);

                        }
                    }catch (Exception e){
                        progressBar.setVisibility(View.INVISIBLE);
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<FetchFarmData> call, Throwable t) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();

                }
            });
            return null;
        }
    }



}
