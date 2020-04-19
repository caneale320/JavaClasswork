/**
 * Homework 02
 * 
 * @author Caleb Neale, can4ku Sources:
 *         https://javarevisited.blogspot.com/2015/07/how-to-convert-string-or-char-to-ascii-example.html
 */
public class Photograph {

    /**
     * serves as caption for the photograph
     */
    private String caption;

    /**
     * filename of the photograph to be uploaded
     */
    private final String filename;

    /**
     * date photograph was taken
     */
    private String dateTaken;

    /**
     * rating of the photograph from 0 to 5
     */
    private int rating;

    /**
     * Constructor method for the photograph class, creates an instance of a Photograph object
     * 
     * @param captionInput  caption for the photograph
     * @param filenameInput filename of the photograph to be uploaded
     */
    public Photograph(String captionInput, String filenameInput) { //
        this.caption = captionInput;
        this.filename = filenameInput;
        this.dateTaken = "1901-01-01";
        this.rating = 0;

    }
    
    /**
     * Constructor method for photograph class, if datetaken and rating are given
     * @param caption
     * @param filename
     * @param dateTaken
     * @param rating
     */
    public Photograph(String caption, String filename, String dateTaken, int rating) {
        this.caption = caption;
        this.filename = filename;

        if (dateTaken.matches("^[0-9]{4}-((0[1-9])|(1[0-2]))-(([0-2][0-9])|(3[0-1]))$")) {
            this.dateTaken = dateTaken;
        } else
            this.dateTaken = "1901-01-01";

        if (0 <= rating && 5 >= rating) {
            this.rating = rating;
        } else
            this.rating = 0;
    }

    /**
     * hashcode method override for photograph, generates hashcode using only filename
     */
    public int hashCode() {
        return this.filename.hashCode();
    }

    /**
     * main mehtod testing
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Photograph poo = new Photograph("Winnie!", "A. A. Milne");
        Photograph cat = new Photograph("a cute cat!", "kitty.jpg");
        Photograph rotunda = new Photograph("my favorite place!", "tundy.jpg");
        Photograph dardenCourt = new Photograph("a great study spot!", "dardy.jpg");
        
        Photograph dog = new Photograph("a cute dog!", "doggie.jpg", "2000-2-20", 5);
        System.out.println(dog);


        System.out.println("equals Method Test 1:" + poo.equals(poo));
        System.out.println("equals Method Test 2:" + cat.equals(poo));

        System.out.println("toString Method Test 1:" + poo.toString());
        System.out.println("toString Method Test 2:" + rotunda.toString());

        System.out.println("getter Method Test 1:" + poo.getCaption());
        System.out.println("getter Method Test 2:" + poo.getFilename());

        System.out.println(cat.hashCode());

    }

    /**
     * equals method for photograph class, return true if both fields of given object are equal, false if not
     * 
     * @param o object to be compared with .this instance
     */
    public boolean equals(Object o) {
        if ((o instanceof Photograph) && o != null) {
            Photograph photo = (Photograph) o;
            return (photo.caption.equals(this.caption) && photo.filename.equals(this.filename));
        } else
            return false;
    }

    public String toString() {
        return this.caption + ", " + this.filename + ";" + this.dateTaken + ";" + this.rating;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        if (dateTaken.matches("^[0-9]{4}-((0[1-9])|(1[0-2]))-(([0-2][0-9])|(3[0-2]))$")) {
            this.dateTaken = dateTaken;
        } else
            this.dateTaken = "1901-01-01";
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (0 <= rating && 5 >= rating) {
            this.rating = rating;
        } else
            this.rating = 0;
    }

    /**
     * @return caption of a specific photograph object
     */
    public String getCaption() {
        return this.caption;
    }
    
    public void setCaption(String caption) {
        this.caption = caption;
    }
    

    /**
     * @return filename of a specific photograph object
     */
    public String getFilename() {
        return this.filename;
    }

}
