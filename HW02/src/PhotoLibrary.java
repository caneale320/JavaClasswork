import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Homework 02
 * 
 * @author Caleb Neale, can4ku Sources: https://javascript.info/object-methods,
 *         https://stackoverflow.com/questions/13695547/arraylist-of-strings-to-one-single-string,
 *         https://stackoverflow.com/questions/10242380/how-can-i-generate-a-list-or-array-of-sequential-integers-in-java/22829036
 */

public class PhotoLibrary {

    /**
     * name of the an instance of the class
     */
    private String name;

    /**
     * immutable int id of each photolibray
     */
    private final int id;

    /**
     * contains all the actual photograph objects in the library
     */
    private ArrayList<Photograph> photos;

    private HashSet<Album> albums;

    /**
     * constructor method for the photolibrary class
     * 
     * @param nameInput string to be set as the name for each instance
     * @param idInput   int to be set as the id for each instance
     */
    public PhotoLibrary(String nameInput, int idInput) {
        this.name = nameInput;
        this.id = idInput;
        this.photos = new ArrayList<Photograph>();
        this.albums = new HashSet<Album>();
    }

    /**
     * adds a photograph object to the library given the object is not already in the photolibrary photos object
     * 
     * @param p photograph object to add to the library
     * @return true if the photograph was successfully added, false if not
     */
    public boolean addPhoto(Photograph p) {
        boolean pInPhotos = false;

        for (Photograph photo : this.photos) {
            if (photo.equals(p)) {
                pInPhotos = true;
            }
        }

        if (!pInPhotos) {
            this.photos.add(p);
            return true;
        }

        else
            return false;

    }

    /**
     * checks if a photo is in the photolibrary
     * 
     * @param p photograph to check
     * @return true if is in, false if not
     */
    public boolean hasPhoto(Photograph p) {
        boolean pInPhotos = false;

        for (Photograph photo : this.photos) {
            if (photo.equals(p)) {
                pInPhotos = true;
            }
        }

        return pInPhotos;
    }

    /**
     * method to retrieve photos of a specific rating from a photolibrary
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

    /**
     * adds an album with the given name to a photolibrary if no album with the same name is present
     * @param albumName
     * @return true if successful, false if not
     */
    public boolean createAlbum(String albumName) {
        Album placeHolder = new Album(albumName);

        if (this.albums.add(placeHolder)) {
            this.albums.add(placeHolder);
            return true;
        } else
            return false;
    }

    /**
     * removes an album with the given name from a photolibrary is an album with the name is present
     * @param albumName
     * @return true if successful, false if not
     */
    public boolean removeAlbum(String albumName) {
    
        if (this.albums.contains(getAlbumByName(albumName))) {
            this.albums.remove(getAlbumByName(albumName));
            return true;
        } else
            return false;
    }

    /**
     * adds a photo to an album in a photolibrary if possible
     * @param p
     * @param albumName
     * @return true if successful, false otherwise
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        
        if (this.albums.contains(getAlbumByName(albumName)) && !(this.photos.contains(p))) {
            getAlbumByName(albumName).addPhoto(p);
            return true;
        } else
            return false;
    }

    /**
     * removes a photo to an album in a photolibrary if possible
     * @param p
     * @param albumName
     * @return true if successful, false otherwise
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {

        if (this.albums.contains(getAlbumByName(albumName))) {
            this.getAlbumByName(albumName).removePhoto(p);
            return true;
        } else
            return false;
    }

    /**
     * retrieves an album using an albumname
     * @param albumName
     * @return the album if present, null if not
     */
    private Album getAlbumByName(String albumName) {
        for(Album a : this.albums) {
            if(a.getName().equals(albumName)) {
                return a;
            }
        }
        return null;     
    }

    /**
     * method to erase a photograph from an photolibrary
     * 
     * @param p photograph to be erased
     * @return true if sucessfully erased, false if photo was not present
     */
    public boolean erasePhoto(Photograph p) {
        boolean result1;

        if (!this.hasPhoto(p)) {
            result1 = false;
        } else if (this.hasPhoto(p)) {
            this.photos.remove(p);
            result1 = true;
        } else
            result1 = false;

        boolean result2 = false;
        for (Album album : this.albums) {
            if (album.hasPhoto(p)) {
                album.removePhoto(p);
                result2 = true;
            }
        }
        return result1 && result2;
    }

    /**
     * method to retrieve the number of photograph objects in a photolibrary
     * 
     * @return num photograph objects
     */
    public int numPhotographs() {
        return this.photos.size();
    }

    /**
     * method to determine equalit of two photolibrary objects based on id number
     * 
     * @param o object (photolibrary) to compare
     * @return true if equal, false if not
     */
    public boolean equals(Object o) {
        if ((o instanceof PhotoLibrary) && o != null) {
            PhotoLibrary photo = (PhotoLibrary) o;
            if (photo.id == this.id) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    /**
     * method to return a string describing the photolibrary
     * 
     * @return a string with the id, name and photograph objects
     */
    public String toString() {
        String output = "id: " + this.id + "\n" + "name: " + this.name + "\n" + "filenames: ";
        ArrayList<String> strings = new ArrayList<String>();

        for (Photograph item : this.photos) {
            strings.add(item.toString());
        }
        String joined = String.join("; ", strings);

        ArrayList<String> albumNames = new ArrayList<String>();

        for (Album album : this.albums) {
            albumNames.add(album.toString());
        }

        String joined1 = String.join("; ", albumNames);

        String finalOutput = output + joined + "\nAlbum Names " + joined1;
        return finalOutput;
    }

    /**
     * a method to find the common photograph objects between two photolibraries
     * 
     * @param a the first photolibrary to compare
     * @param b the second photolibrary to compare
     * @return an arraylist of all the common photos
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {

        ArrayList<Photograph> output = new ArrayList<Photograph>();

        for (Photograph photo1 : a.photos) {
            for (Photograph photo2 : b.photos) {
                if (photo1.equals(photo2)) {
                    output.add(photo1);
                }
            }
        }
        return output;
    }

    /**
     * a method to determine the simalairty of two photolibrary objects
     * 
     * @param a the first photolibrary to compare
     * @param b the second photolibrary to compare
     * @return a double from 0.0 to 1.0 describing simalairity from least to greatests
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        int count = 0;

        for (Photograph photo1 : a.photos) {
            for (Photograph photo2 : b.photos) {
                if (photo1.equals(photo2)) {
                    count++;
                }
            }
        }

        double length = 0.0;

        if (a.photos.size() > b.photos.size()) {
            length = b.photos.size();
        } else if (a.photos.size() < b.photos.size()) {
            length = a.photos.size();
        } else
            return 0.0;

        double output = count / length;
        return output;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

         PhotoLibrary petsLib = new PhotoLibrary("pets", 01);
         PhotoLibrary uvaLib = new PhotoLibrary("UVA", 02);
         PhotoLibrary dogTundyLib = new PhotoLibrary("doggieTundy", 03);
        //
        //
        //
         Photograph dog = new Photograph("a cute dog!", "doggie.jpg", "2000-04-20", 5);
         Photograph cat = new Photograph("a cute cat!", "kitty.jpg", "2000-04-20", 4);
         Photograph rotunda = new Photograph("my favorite place!", "tundy.jpg", "1999-04-20", 3);
         Photograph dardenCourt = new Photograph("a great study spot!", "dardy.jpg", "1999-04-21", 2);
        //
//         System.out.println("hasPhoto Method Test 1:" + petsLib.hasPhoto(dog));
//         System.out.println("hasPhoto Method Test 2:" + petsLib.hasPhoto(rotunda)+ "\n");
        //
         System.out.println("erasePhoto Method Test 1:" + uvaLib.erasePhoto(dog));
         System.out.println("erasePhoto Method Test 2:" + uvaLib.erasePhoto(rotunda)+ "\n");
        //
        // uvaLib.addPhoto(rotunda);
        // uvaLib.addPhoto(dardenCourt);
        // petsLib.addPhoto(cat);
        // dogTundyLib.addPhoto(dog);
        // dogTundyLib.addPhoto(rotunda);
        //
        // System.out.println("numPhotographs Method Test 1:" + uvaLib.numPhotographs());
        // System.out.println("numPhotographs Method Test 2:" + petsLib.numPhotographs());
        // System.out.println("numPhotographs Method Test 3:" + dogTundyLib.numPhotographs()+ "\n");
        //
        // System.out.println("equals Method Test 1:" + uvaLib.equals(petsLib));
        // System.out.println("equals Method Test 2:" + uvaLib.equals(uvaLib)+ "\n");
        //
        // System.out.println("toString Method Test 1:" + uvaLib.toString());
        // System.out.println("toString Method Test 2:" + dogTundyLib.toString());
        // System.out.println("toString Method Test 3:" + petsLib.toString() + "\n");
        //
        // System.out.println("commonPhotos Method Test 1:" + PhotoLibrary.commonPhotos(uvaLib, dogTundyLib));
        // System.out.println("commonPhotos Method Test 2:" + PhotoLibrary.commonPhotos(uvaLib, petsLib) + "\n");
        //
        // System.out.println("similarity Method Test 1:" + PhotoLibrary.similarity(uvaLib, dogTundyLib));
        // System.out.println("similarity Method Test 2:" + PhotoLibrary.similarity(uvaLib, petsLib) + "\n");
        //


        PhotoLibrary all = new PhotoLibrary("all", 1);
        System.out.println("Add Photo Method Test 2:" + all.addPhoto(rotunda));
        System.out.println("Add Photo Method Test 3:" + all.addPhoto(rotunda) + "\n");

        all.addPhoto(dog);
        all.addPhoto(cat);
        all.addPhoto(rotunda);
        all.addPhoto(dardenCourt);

        System.out.println("getPhotos Method Test 2:" + all.getPhotos(1) + "\n");

        System.out.println("getPhotosInYear method test" + all.getPhotosInYear(2000) + "\n");

        System.out.println("getPhotosInMonth method test" + all.getPhotosInMonth(4, 1999) + "\n");

        System.out.println("getPhotosBetween method test" + all.getPhotosBetween("1999-04-21", "2000-04-31") + "\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    public int getId() {
        return id;
    }

    public HashSet<Album> getAlbums() {
        return albums;
    }

}
