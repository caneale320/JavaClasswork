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

public class PhotoLibrary extends PhotographContainer {

    /**
     * immutable int id of each photolibray
     */
    private final int id;

    private HashSet<Album> albums;

    /**
     * constructor method for the photolibrary class
     * 
     * @param nameInput string to be set as the name for each instance
     * @param idInput   int to be set as the id for each instance
     */
    public PhotoLibrary(String nameInput, int idInput) {
        super(nameInput);
        this.id = idInput;
        this.photos = new ArrayList<Photograph>();
        this.albums = new HashSet<Album>();
    }

    /**
     * adds an album with the given name to a photolibrary if no album with the same name is present
     * 
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
     * 
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
     * 
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
     * 
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
     * 
     * @param albumName
     * @return the album if present, null if not
     */
    private Album getAlbumByName(String albumName) {
        for (Album a : this.albums) {
            if (a.getName().equals(albumName)) {
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
    public boolean removePhoto(Photograph p) {
        boolean result1;

        if (!this.hasPhoto(p)) {
            result1 = false;
        } else if (this.hasPhoto(p)) {
            this.photos.remove(p);
            result1 = true;
            return result1;
        } else
            result1 = false;

        boolean result2 = false;
        for (Album album : this.albums) {
            if (album.hasPhoto(p)) {
                album.removePhoto(p);
                result2 = true;
                return result2;
            }
        }
        return false;
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
        // System.out.println("hasPhoto Method Test 1:" + petsLib.hasPhoto(dog));
        // System.out.println("hasPhoto Method Test 2:" + petsLib.hasPhoto(rotunda)+ "\n");
        //
        System.out.println("erasePhoto Method Test 1:" + uvaLib.removePhoto(dog));
        System.out.println("erasePhoto Method Test 2:" + uvaLib.removePhoto(rotunda) + "\n");
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

        // PhotoLibrary all = new PhotoLibrary("all", 1);
        // System.out.println("Add Photo Method Test 2:" + all.addPhoto(rotunda));
        // System.out.println("Add Photo Method Test 3:" + all.addPhoto(rotunda) + "\n");
        //
        // all.addPhoto(dog);
        // all.addPhoto(cat);
        // all.addPhoto(rotunda);
        // all.addPhoto(dardenCourt);
        //
        // System.out.println("getPhotos Method Test 2:" + all.getPhotos(1) + "\n");
        //
        // System.out.println("getPhotosInYear method test" + all.getPhotosInYear(2000) + "\n");
        //
        // System.out.println("getPhotosInMonth method test" + all.getPhotosInMonth(4, 1999) + "\n");
        //
        // System.out.println("getPhotosBetween method test" + all.getPhotosBetween("1999-04-21", "2000-04-31") + "\n");
    }

    public int getId() {
        return id;
    }

    public HashSet<Album> getAlbums() {
        return albums;
    }

}
