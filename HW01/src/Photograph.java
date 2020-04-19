/**
 * Homework 01
 * 
 * @author Caleb Neale, can4ku Sources:
 */
public class Photograph {

    /**
     * serves as caption for the photograph
     */
    private final String caption;

    /**
     * filename of the photograph to be uploaded
     */
    private final String filename;

    /**
     * Constructor method for the photograph class, creates an instance of a Photograph object
     * 
     * @param captionInput  caption for the photograph
     * @param filenameInput filename of the photograph to be uploaded
     */
    public Photograph(String captionInput, String filenameInput) { //
        this.caption = captionInput;
        this.filename = filenameInput;

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Photograph poo = new Photograph("Winnie!", "A. A. Milne");
        Photograph cat = new Photograph("a cute cat!", "kitty.jpg");
        Photograph rotunda = new Photograph("my favorite place!", "tundy.jpg");
        Photograph dardenCourt = new Photograph("a great study spot!", "dardy.jpg");
        
        System.out.println("equals Method Test 1:" + poo.equals(poo));
        System.out.println("equals Method Test 2:" + cat.equals(poo));
        
        System.out.println("toString Method Test 1:" + poo.toString());
        System.out.println("toString Method Test 2:" + rotunda.toString());
        
        System.out.println("getter Method Test 1:" + poo.getCaption());
        System.out.println("getter Method Test 2:" + poo.getFilename());




    }
    
    
    /**
     * equals method for photograph class, return true if both fields of given object are equal, false if not
     * 
     * @param o object to be compared with .this instance
     */
    public boolean equals(Object o) {
        if( (o instanceof Photograph) && o != null) {
            Photograph photo = (Photograph) o;
            return ( photo.caption.equals(this.caption) && photo.filename.equals(this.filename) );
            }
        else return false;
        }
    
    public String toString() {
        return this.caption + ", " + this.filename;
    }
    
    /**
     * 
     * @return caption of a specific photograph object
     */
    public String getCaption() {
        return this.caption;
    }
    
    /**
     * 
     * @return filename of a specific photograph object
     */
    public String getFilename() {
        return this.filename;
    }



}
