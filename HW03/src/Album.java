import java.util.*;

public class Album extends PhotographContainer {

    /**
     * album object constructor
     * 
     * @param nameInput
     */
    public Album(String nameInput) {
        super(nameInput);
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
