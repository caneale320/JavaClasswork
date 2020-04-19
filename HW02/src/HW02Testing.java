import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class HW02Testing {

    Photograph dog = new Photograph("a cute dog!", "doggie.jpg", "2000-04-20", 5);
    Photograph cat;
    Photograph rotunda;
    Photograph dardenCourt;
    
    PhotoLibrary animals;
    PhotoLibrary grounds;
    PhotoLibrary allLib;
    
    Album uva;
    Album pets;
    Album all;
    
    @Before
    public void setUp() {
        dog = new Photograph("a cute dog!", "doggie.jpg", "2000-04-20", 5);
        cat = new Photograph("a cute cat!", "kitty.jpg", "2000-04-22", 4);
        rotunda = new Photograph("my favorite place!", "tundy.jpg", "1999-04-20", 3);
        dardenCourt = new Photograph("a great study spot!", "dardy.jpg", "1999-04-21", 2);
        
        uva = new Album("UVA");
        uva.addPhoto(rotunda);
        uva.addPhoto(dardenCourt);
        
        pets = new Album("pets");
        pets.addPhoto(dog);
        pets.addPhoto(cat);

        all = new Album("all");
        all.addPhoto(dog);
        all.addPhoto(cat);
        all.addPhoto(rotunda);
        all.addPhoto(dardenCourt);
        
        grounds = new PhotoLibrary("grounds", 1);
        grounds.addPhoto(rotunda);
        grounds.addPhoto(dardenCourt);
        
        animals = new PhotoLibrary("animals", 2);
        animals.addPhoto(dog);
        animals.addPhoto(cat);
        
        allLib = new PhotoLibrary("allLib", 3);
        allLib.addPhoto(dog);
        allLib.addPhoto(cat);
        allLib.addPhoto(rotunda);
        allLib.addPhoto(dardenCourt);
    }
    
    @Test
    public void testGetPhotos() {
        ArrayList<Photograph> temp = new ArrayList<Photograph>();
        temp.add(dog);
        
        assertTrue(temp.equals(animals.getPhotos(5)));
    }
    
    @Test
    public void testGetPhotos2() {  
        
        ArrayList<Photograph> temp = new ArrayList<Photograph>();
        temp.add(dog);
        
        assertTrue(grounds.getPhotos(4).isEmpty());

    }
    
    @Test
    public void testGetPhotosInMonth() {  
        
        ArrayList<Photograph> temp = new ArrayList<Photograph>();
        temp.add(dog);
        temp.add(cat);
       
        assertTrue(allLib.getPhotosInMonth(4, 2000).equals(temp));

    }
    
    @Test
    public void testGetPhotosInMonth1() {  
        
        ArrayList<Photograph> temp = new ArrayList<Photograph>();
        temp.add(dog);
        temp.add(cat);
       
        assertTrue(allLib.getPhotosInMonth(5, 2000).isEmpty());

    }
    
    @Test
    public void testGetPhotosBetween() {  
        
        ArrayList<Photograph> temp = new ArrayList<Photograph>();
        temp.add(dog);
       
        assertTrue(allLib.getPhotosBetween("2000-01-01", "2000-04-21").equals(temp));

    }
    
    @Test
    public void testGetPhotosBetween1() {  
        
        ArrayList<Photograph> temp = new ArrayList<Photograph>();
        temp.add(cat);
        temp.add(dog);

        assertTrue(allLib.getPhotosBetween("2000-01-01", "2000-05-01").equals(temp));

    }
    
 
    

}
