import java.util.ArrayList;

/**
 * Homework 01
 * 
 * @author Caleb Neale, can4ku Sources: https://javascript.info/object-methods, https://stackoverflow.com/questions/13695547/arraylist-of-strings-to-one-single-string
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

    /**
     * constructor method for the photolibrary class
     * @param nameInput string to be set as the name for each instance
     * @param idInput int to be set as the id for each instance
     */
    public PhotoLibrary(String nameInput, int idInput) {
        this.name = nameInput;
        this.id = idInput;
        this.photos = new ArrayList<Photograph>(); 
    }

    /**
     * adds a photograph object to the library given the object is not already in the photolibrary photos object
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
     * method to erase a photograph from an photolibrary
     * @param p photograph to be erased
     * @return true if sucessfully erase, false if photo was not present
     */
    public boolean erasePhoto(Photograph p) {
        if (!this.hasPhoto(p)) {
            return false;
        } else if (this.hasPhoto(p)) {
            this.photos.remove(p);
            return true;
        } else
            return false;
    }

    /**
     * method to retrieve the number of photograph objects in a photolibrary
     * @return num photograph objects
     */
    public int numPhotographs() {
        return this.photos.size();
    }

    /**
     * method to determine equalit of two photolibrary objects based on id number
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
     * @return a string with the id, name and photograph objects 
     */
    public String toString() {
        String output = "id: " + this.id + "\n" + "name: " + this.name + "\n" + "filenames: ";
        ArrayList<String> strings = new ArrayList<String>();
        for(Photograph item : this.photos) {
            strings.add(item.toString());
        }
        String joined = String.join("; ", strings);
        String finalOutput = output + joined;
        return finalOutput;
    }

    /**
     * a method to find the common photograph objects between two photolibraries
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
        Photograph dog = new Photograph("a cute dog!", "doggie.jpg");
        Photograph cat = new Photograph("a cute cat!", "kitty.jpg");
        Photograph rotunda = new Photograph("my favorite place!", "tundy.jpg");
        Photograph dardenCourt = new Photograph("a great study spot!", "dardy.jpg");
        
        PhotoLibrary petsLib = new PhotoLibrary("pets", 01);
        PhotoLibrary uvaLib = new PhotoLibrary("UVA", 02);
        PhotoLibrary dogTundyLib = new PhotoLibrary("doggieTundy", 03);
        
        System.out.println("Add Photo Method Test 1:" + petsLib.addPhoto(dog));
        System.out.println("Add Photo Method Test 2:" + uvaLib.addPhoto(rotunda));
        System.out.println("Add Photo Method Test 3:" + uvaLib.addPhoto(rotunda) + "\n");
        
        System.out.println("hasPhoto Method Test 1:" + petsLib.hasPhoto(dog));
        System.out.println("hasPhoto Method Test 2:" + petsLib.hasPhoto(rotunda)+ "\n");
        
        System.out.println("erasePhoto Method Test 1:" + uvaLib.erasePhoto(dog));
        System.out.println("erasePhoto Method Test 2:" + uvaLib.erasePhoto(rotunda)+ "\n");
        
        uvaLib.addPhoto(rotunda);
        uvaLib.addPhoto(dardenCourt);
        petsLib.addPhoto(cat);
        dogTundyLib.addPhoto(dog);
        dogTundyLib.addPhoto(rotunda);
        
        System.out.println("numPhotographs Method Test 1:" + uvaLib.numPhotographs());
        System.out.println("numPhotographs Method Test 2:" + petsLib.numPhotographs());
        System.out.println("numPhotographs Method Test 3:" + dogTundyLib.numPhotographs()+ "\n");
        
        System.out.println("equals Method Test 1:" + uvaLib.equals(petsLib));
        System.out.println("equals Method Test 2:" + uvaLib.equals(uvaLib)+ "\n");
        
        System.out.println("toString Method Test 1:" + uvaLib.toString());
        System.out.println("toString Method Test 2:" + dogTundyLib.toString());
        System.out.println("toString Method Test 3:" + petsLib.toString() + "\n");

        System.out.println("commonPhotos Method Test 1:" + PhotoLibrary.commonPhotos(uvaLib, dogTundyLib));
        System.out.println("commonPhotos Method Test 2:" + PhotoLibrary.commonPhotos(uvaLib, petsLib) + "\n");
        
        System.out.println("similarity Method Test 1:" + PhotoLibrary.similarity(uvaLib, dogTundyLib));
        System.out.println("similarity Method Test 2:" + PhotoLibrary.similarity(uvaLib, petsLib) + "\n");
        

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

}
