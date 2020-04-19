import java.util.*;

public class Album {

    private String name;
    private HashSet<Photograph> photos;

    /**
     * album object constructor
     * @param nameInput
     */
    public Album(String nameInput) {
        this.name = nameInput;
        this.photos = new HashSet<Photograph>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<Photograph> getPhotos() {
        return photos;
    }

    public void setPhotos(HashSet<Photograph> photos) {
        this.photos = photos;
    }

    /**
     * adds photos to album
     * @param p
     * @return true if successful, false otherwise
     */
    public boolean addPhoto(Photograph p) {
        if (this.photos.contains(p)) {
            return false;
        } else if (!this.photos.contains(p) && !(p==null) ) {
            this.photos.add(p);
            return true;
        } else
            return false;

    }

    /**
     * determines whether an album contains the given photograph
     * @param p
     * @return true if p is contained, false if not
     */
    public boolean hasPhoto(Photograph p) {
        return this.photos.contains(p);
    }

    /**
     * removes photo p if present
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
     * @return int of photos in library
     */
    public int numPhotographs() {
        return this.photos.size();
    }

    
    /**
     * equals method override
     */
    public boolean equals(Object o) {
        if ((o instanceof Album) && o != null) {
            Album album = (Album) o;
            return (this.name.equals(album.name));
        } else
            return false;
    }

    public String toString() {
        return this.name + "\n" + this.photos.toString();
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public static void main(String[] args) {
        Album dickPics = new Album("dickPics");
        Photograph poo = new Photograph("Winnie!", "A. A. Milne");
        System.out.println(dickPics.addPhoto(poo));

        Photograph cat = new Photograph("a cute cat!", "kitty.jpg");
        System.out.println(dickPics.addPhoto(cat));
        System.out.println(dickPics.hasPhoto(poo));

        System.out.println(dickPics.numPhotographs());

        System.out.println(dickPics.toString());

        System.out.println(dickPics.hashCode());

    }

}
