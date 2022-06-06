package Eksamener.kont19.main.java.del1;

public class GeoLocation {
 
    private final double latitude;
    private final double longitude;
 
    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
 
    public double getLatitude() {
        return latitude;
    }
 
    public double getLongitude() {
        return longitude;
    }
 
    /**
     * Computes distance to other Location
     * @param other
     * @return distance to other Location
     */
    public double distance(GeoLocation other) {
        return distance(this.latitude,this.longitude,other.getLatitude(),other.getLongitude());
        // ??? 1 b)
    }
 
    /** calculates the distance between two points,
     *   given the latitude/longitude of those points).                                  
     *
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        // return null er bare tull, den har egentlig beregning her.
        return 0.0; // Denne kompliserte beregningen er utelatt.
        // Anta at den er implementert.
    }
}