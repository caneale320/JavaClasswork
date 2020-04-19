import java.util.ArrayList;
import java.util.HashSet;

public class PhotographContainer {

    protected String name;
    protected ArrayList<Photograph> photos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photograph> photos) {
        this.photos = photos;
    }

    public PhotographContainer(String name) {
        this.name = name;
        this.photos = new ArrayList<Photograph>();
    }

    public PhotographContainer(String name, ArrayList<Photograph> photos) {
        this.name = name;
        this.photos = photos;
    }

    /**
     * adds photos to container
     * 
     * @param p
     * @return true if successful, false otherwise
     */
    public boolean addPhoto(Photograph p) {
        if (this.photos.contains(p)) {
            return false;
        } else if (!this.photos.contains(p) && !(p == null)) {
            this.photos.add(p);
            return true;
        } else
            return false;
    }

    /**
     * determines whether a container contains the given photograph
     * 
     * @param p
     * @return true if p is contained, false if not
     */
    public boolean hasPhoto(Photograph p) {
        return this.photos.contains(p);
    }

    /**
     * removes photo p if present
     * 
     * @param p
     * @return true if successful, false otherwise
     */
    public boolean removePhoto(Photograph p) {
        if (this.photos.contains(p)) {
            this.photos.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * determines the number of photographs in the album
     * 
     * @return int of photos in library
     */
    public int numPhotographs() {
        return this.photos.size();
    }

    /**
     * equals method override
     */
    public boolean equals(Object o) {
        if ((o instanceof PhotographContainer) && o != null) {
            PhotographContainer container = (PhotographContainer) o;
            return (this.name.equals(container.name));
        } else
            return false;
    }

    public String toString() {
        return this.name + "\n" + this.photos.toString();
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * method to retrieve photos of a specific rating from a photolibrary
     * 
     * @param rating
     * @return an ArrayList of photos of the correct rating
     */
    public ArrayList<Photograph> getPhotos(int rating) {
        ArrayList<Photograph> output = new ArrayList<Photograph>();

        if (rating <= 5 && rating >= 0) { // check if rating is in a valid range

            for (Photograph photo : this.photos) {
                if (photo.getRating() >= rating) {
                    output.add(photo);
                }
            }
            return output;
        } else
            return null;
    }

    /**
     * retrieves all photos in a given year in the photolibrary
     * 
     * @param year
     * @return an ArrayList of photos in the month
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        ArrayList<Photograph> output = new ArrayList<Photograph>();

        if (Integer.toString(year).matches("^[0-9]{1,4}$")) { // check formatting of input year
            for (Photograph photo : this.photos) {  // iterate though available photos
                if (Integer.parseInt(photo.getDateTaken().substring(0, 4)) == year) { // check if year taken of a given photo
                                                                                      // matches the given year
                    output.add(photo);
                }
            }
            return output;
        } else
            return null;
    }

    /**
     * retrieves all photos in a given month in a given year in the photolibrary
     * 
     * @param month
     * @param year
     * @return an ArrayList of photos in the month
     */
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        ArrayList<Photograph> output = new ArrayList<Photograph>();

        if (month <= 12 && month >= 0) {
            ArrayList<Photograph> inYear = this.getPhotosInYear(year); // create list of photos in the right year
            for (Photograph photo : inYear) {  // iterate though available photos
                if (Integer.parseInt(photo.getDateTaken().substring(5, 7)) == month) { // check if year taken of a given photo
                                                                                       // matches the given year
                    output.add(photo);
                }
            }
            return output;
        } else
            return null;
    }

    /**
     * gets all photos between two specific dates
     * 
     * @param beginDate
     * @param endDate
     * @return an arraylist of photos between the two dates
     */
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
        // validate input
        String pattern = "^[0-9]{4}-((0[1-9])|(1[0-2]))-(([0-2][0-9])|(3[0-1]))$";

        ArrayList<Photograph> output = new ArrayList<Photograph>();

        if (beginDate.matches(pattern) && endDate.matches(pattern)) {
            for (Photograph p : this.photos) {
                if ((beginDate.compareTo(p.getDateTaken()) <= 0)) {
                    if ((endDate.compareTo(p.getDateTaken()) >= 0)) {
                        output.add(p);
                    }
                }

            }
        } else
            return null;

        return output;
    }

}
