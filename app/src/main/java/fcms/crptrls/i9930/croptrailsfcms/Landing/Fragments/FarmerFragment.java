package fcms.crptrls.i9930.croptrailsfcms.Landing.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.DatabaseHandler;
import fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler.SaveGpsGetterSetter;
import fcms.crptrls.i9930.croptrailsfcms.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FarmerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FarmerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarmerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    SaveGpsGetterSetter saveGpsGetterSetter;
    final List<SaveGpsGetterSetter> saveGpsGetterSetterList=new ArrayList<>();



    public FarmerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FarmerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FarmerFragment newInstance(String param1, String param2) {
        FarmerFragment fragment = new FarmerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(getContext());


        List<SaveGpsGetterSetter> contacts = db.getallgpsCordinates();

        if(contacts!=null) {
            if(contacts.size()>0) {
                contacts.get(contacts.size() - 1);

                String log = "Id: " + contacts.get(contacts.size() - 1).getId() + "    Latitude" + contacts.get(contacts.size() - 1).getLati_cord() + " ,Longitude: " + contacts.get(contacts.size() - 1).getLongi_cord() + "   Date" + contacts.get(contacts.size() - 1).getEnter_date();

                Log.e("GpsCordinatesdb:LAST", log);

                for (SaveGpsGetterSetter cn : contacts) {
                    String logall = "Id: " + cn.getId() + "    Latitude" + cn.getLati_cord() + " ,Longitude: " + cn.getLongi_cord() + "   Date" + cn.getEnter_date();
                    // Writing Contacts to log

                    saveGpsGetterSetter = new SaveGpsGetterSetter( cn.getLati_cord(), cn.getLongi_cord(),cn.getSv_id(), cn.getEnter_date());
                    //getprofile.setYear(123);
                    saveGpsGetterSetterList.add(saveGpsGetterSetter);
                    Log.e("GpsCordinates in dball ", logall);
                    //id_count=cn.get_id();
                }
            }
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_farmer, container, false);
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
}
