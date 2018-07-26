package fcms.crptrls.i9930.croptrailsfcms.ServicesAndBroadCastRecivers;

import android.location.Location;

public interface OnNewLocationListener {
    public abstract void onNewLocationReceived(Location location);
}