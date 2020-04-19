
// https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

public class PhotoViewer extends JFrame {

    PhotographContainer imageLibrary;

    JFrame mainFrame;
    JPanel northPanel;
    JPanel westPanel;
    JPanel centerPanel;
    JPanel southPanel;
    static JPanel imageCountPanel;
    JPanel northButtonsPanel;
    JPanel sortByPanel;

    JButton next;
    JButton previous;
    JButton exit;

    ArrayList<JLabel> thumbnailCaptions = new ArrayList<JLabel>();

    JLabel centerImage;

    ButtonGroup sortBy;
    JRadioButton sortByDate;
    JRadioButton sortByCaption;
    JRadioButton sortByRating;

    ButtonGroup rating;
    JRadioButton one;
    JRadioButton two;
    JRadioButton three;
    JRadioButton four;
    JRadioButton five;

    Photograph currentImage;

    static JLabel imageCount;

    String imageOnDisplay;
    int imagePositionOnDilplay;

    public PhotoViewer(PhotographContainer imageLibrary) {
        this.imageLibrary = imageLibrary;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.setSize(width, height);

        mainFrame = new JFrame("PhotoViewer");
        mainFrame.setLayout(new BorderLayout());

        makeCentralPanel(mainFrame, imageLibrary.getPhotos().get(0));
        makeNorthPanel(mainFrame, imageLibrary);
        makeSouthPanel(mainFrame, 2);
        makeWestPanel(mainFrame, imageLibrary);

        mainFrame.setSize(height, width);
        // mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private class exitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("click")) {
                System.exit(0);
            }
        }
    }

    private class sortByListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Photograph> photosTemp = imageLibrary.getPhotos();

            if (e.getActionCommand().equals("date")) {
                Collections.sort(photosTemp);
                imageLibrary.setPhotos(photosTemp);
            }

            else if (e.getActionCommand().equals("rating")) {
                CompareByRating cr = new CompareByRating();
                Collections.sort(photosTemp, cr);
                imageLibrary.setPhotos(photosTemp);
            }

            else if (e.getActionCommand().equals("caption")) {
                CompareByCaption cc = new CompareByCaption();
                Collections.sort(photosTemp, cc);
                imageLibrary.setPhotos(photosTemp);
            }

            westPanel = new JPanel();
            westPanel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.weightx = 0.5;
            c.weighty = 0.5;

            for (int i = 0; i < imageLibrary.getPhotos().size(); i++) {
                Photograph temp = imageLibrary.getPhotos().get(i);
                BufferedImage image = temp.getImageData();
                Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

                JLabel img = new JLabel(new ImageIcon(dimg));
                JLabel caption = new JLabel("<html>" + temp.getFilename() + ", " + temp.getCaption() + "<br>"
                        + temp.getDateTaken() + "<br>Rated: " + Integer.toString(temp.getRating()) + "</html>");

                img.setPreferredSize(new Dimension(100, 100));

                Mouse m = new Mouse(i);
                img.addMouseListener(m);
                caption.addMouseListener(m);

                thumbnailCaptions.set(i, caption);

                c.gridx = 0;
                c.gridy = i;

                westPanel.add(img, c);

                c.gridx = 1;

                westPanel.add(caption, c);
            }

            mainFrame.remove(westPanel);
            mainFrame.add(westPanel, "West");
            mainFrame.revalidate();

            currentImage = imageLibrary.getPhotos().get(0);
            int currentIndex = 0;

            BufferedImage image = currentImage.getImageData();
            int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            Image dimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            centerImage.setIcon(new ImageIcon(dimg));

            // update image count label
            imageCount.setText(
                    "Image " + Integer.toString(currentIndex + 1) + " of " + Integer.toString(imageLibrary.getPhotos().size()));

            // update rating
            int ratingNUM = currentImage.getRating();

            if (ratingNUM == 1) {
                one.setSelected(true);
            }

            if (ratingNUM == 2) {
                two.setSelected(true);
            }

            if (ratingNUM == 3) {
                three.setSelected(true);

            }

            if (ratingNUM == 4) {
                four.setSelected(true);

            }

            if (ratingNUM == 5) {
                five.setSelected(true);

            }
        }
    }

    class ratingButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentImage.setRating(Integer.parseInt(e.getActionCommand()));
            ArrayList<Photograph> tempPhotos = imageLibrary.getPhotos();
            int currentIndex = tempPhotos.indexOf(currentImage);
            thumbnailCaptions.get(currentIndex)
                    .setText(("<html>" + currentImage.getFilename() + ", " + currentImage.getCaption() + "<br>"
                            + currentImage.getDateTaken() + "<br>Rated: " + Integer.toString(currentImage.getRating())
                            + "</html>"));
        }
    }

    public void makeNorthPanel(JFrame main, PhotographContainer imageLibrary) {
        northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        class nextButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("click")) {
                    int currentIndex = imageLibrary.getPhotos().indexOf(currentImage);
                    if (currentIndex + 1 < imageLibrary.getPhotos().size()) {

                        // update current image
                        currentImage = imageLibrary.getPhotos().get(currentIndex + 1);
                        currentIndex = currentIndex += 1;

                        BufferedImage image = currentImage.getImageData();
                        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
                        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                        Image dimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                        centerImage.setIcon(new ImageIcon(dimg));

                        // update image count label
                        imageCount.setText("Image " + Integer.toString(currentIndex + 1) + " of "
                                + Integer.toString(imageLibrary.getPhotos().size()));

                        // update rating
                        int ratingNUM = currentImage.getRating();

                        if (ratingNUM == 1) {
                            one.setSelected(true);
                        }

                        if (ratingNUM == 2) {
                            two.setSelected(true);
                        }

                        if (ratingNUM == 3) {
                            three.setSelected(true);

                        }

                        if (ratingNUM == 4) {
                            four.setSelected(true);

                        }

                        if (ratingNUM == 5) {
                            five.setSelected(true);

                        }

                    } else {

                        imageCount.setText("Image 1 of " + Integer.toString(imageLibrary.getPhotos().size()));

                        // update current image
                        currentImage = imageLibrary.getPhotos().get(0);
                        currentIndex = 0;

                        BufferedImage image = currentImage.getImageData();
                        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
                        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                        Image dimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        centerImage.setIcon(new ImageIcon(dimg));

                        // update image count label
                        imageCount.setText(
                                "Image " + Integer.toString(1) + " of " + Integer.toString(imageLibrary.getPhotos().size()));

                        // update rating
                        int ratingNUM = currentImage.getRating();

                        if (ratingNUM == 1) {
                            one.setSelected(true);
                        }

                        if (ratingNUM == 2) {
                            two.setSelected(true);
                        }

                        if (ratingNUM == 3) {
                            three.setSelected(true);

                        }

                        if (ratingNUM == 4) {
                            four.setSelected(true);

                        }

                        if (ratingNUM == 5) {
                            five.setSelected(true);

                        }
                    }
                }
            }
        }

        class previousButtonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equals("click")) {
                    int currentIndex = imageLibrary.getPhotos().indexOf(currentImage);
                    if (currentIndex - 1 >= 0) {

                        // update current image
                        currentImage = imageLibrary.getPhotos().get(currentIndex - 1);
                        currentIndex = currentIndex -= 1;

                        BufferedImage image = currentImage.getImageData();
                        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
                        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                        Image dimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        centerImage.setIcon(new ImageIcon(dimg));

                        // update image count label
                        imageCount.setText("Image " + Integer.toString(currentIndex + 1) + " of "
                                + Integer.toString(imageLibrary.getPhotos().size()));

                        // update rating
                        int ratingNUM = currentImage.getRating();

                        if (ratingNUM == 1) {
                            one.setSelected(true);
                        }

                        if (ratingNUM == 2) {
                            two.setSelected(true);
                        }

                        if (ratingNUM == 3) {
                            three.setSelected(true);

                        }

                        if (ratingNUM == 4) {
                            four.setSelected(true);

                        }

                        if (ratingNUM == 5) {
                            five.setSelected(true);

                        }

                    } else {

                        imageCount.setText("Image 1 of " + Integer.toString(imageLibrary.getPhotos().size()));

                        // update current image
                        currentImage = imageLibrary.getPhotos().get(imageLibrary.getPhotos().size() - 1);
                        currentIndex = imageLibrary.getPhotos().size() - 1;

                        BufferedImage image = currentImage.getImageData();
                        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
                        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                        Image dimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        centerImage.setIcon(new ImageIcon(dimg));

                        // update image count label
                        imageCount.setText("Image " + Integer.toString(imageLibrary.getPhotos().size()) + " of "
                                + Integer.toString(imageLibrary.getPhotos().size()));

                        // update rating
                        int ratingNUM = currentImage.getRating();

                        if (ratingNUM == 1) {
                            one.setSelected(true);
                        }

                        if (ratingNUM == 2) {
                            two.setSelected(true);
                        }

                        if (ratingNUM == 3) {
                            three.setSelected(true);

                        }

                        if (ratingNUM == 4) {
                            four.setSelected(true);

                        }

                        if (ratingNUM == 5) {
                            five.setSelected(true);

                        }
                    }
                }
            }
        }

        // fix to add image count
        imageCount = new JLabel("Image 1 of " + Integer.toString(imageLibrary.getPhotos().size()));
        // this.imageLibrary.getName() + " Image " + "__" + " of " + Integer.toString(this.imageLibrary.getPhotos().size())

        exit = new JButton("Exit");
        exit.setActionCommand("click");
        exit.addActionListener(new exitButtonListener());

        next = new JButton("Next");
        next.setActionCommand("click");
        next.addActionListener(new nextButtonListener());

        previous = new JButton("Previous");
        previous.setActionCommand("click");
        previous.addActionListener(new previousButtonListener());

        sortBy = new ButtonGroup();
        sortByRating = new JRadioButton("Sort By Rating");
        sortByRating.addActionListener(new sortByListener());
        sortByRating.setActionCommand("rating");

        sortByDate = new JRadioButton("Sort By Date");
        sortByDate.addActionListener(new sortByListener());
        sortByDate.setActionCommand("date");

        sortByCaption = new JRadioButton("Sort By Caption");
        sortByCaption.addActionListener(new sortByListener());
        sortByCaption.setActionCommand("caption");

        sortBy.add(sortByRating);
        sortBy.add(sortByCaption);
        sortBy.add(sortByDate);

        c.weightx = 0.5;

        c.gridx = 0;
        c.gridy = 0;
        northPanel.add(imageCount, c);

        c.gridx = 1;
        c.gridy = 0;
        northPanel.add(exit, c);

        c.gridx = 3;
        c.gridy = 0;
        northPanel.add(next, c);

        c.gridx = 2;
        c.gridy = 0;
        northPanel.add(previous, c);

        c.gridx = 4;
        c.gridy = 0;
        northPanel.add(sortByRating, c);

        c.gridx = 5;
        c.gridy = 0;
        northPanel.add(sortByDate, c);

        c.gridx = 6;
        c.gridy = 0;
        northPanel.add(sortByCaption, c);

        main.getContentPane().add(northPanel, "North");
    }

    class Mouse implements MouseListener {

        boolean entered = false;
        int i;

        Mouse(int i) {
            this.i = i;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            if (entered) {
                int currentIndex = imageLibrary.getPhotos().indexOf(currentImage);

                // update current image
                currentImage = imageLibrary.getPhotos().get(i);
                currentIndex = i;

                BufferedImage image = currentImage.getImageData();

                int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
                int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
                Image dimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                centerImage.setIcon(new ImageIcon(dimg));

                // update image count label
                imageCount.setText("Image " + Integer.toString(currentIndex + 1) + " of "
                        + Integer.toString(imageLibrary.getPhotos().size()));

                // update rating
                int ratingNUM = currentImage.getRating();

                if (ratingNUM == 1) {
                    one.setSelected(true);
                }

                if (ratingNUM == 2) {
                    two.setSelected(true);
                }

                if (ratingNUM == 3) {
                    three.setSelected(true);

                }

                if (ratingNUM == 4) {
                    four.setSelected(true);

                }

                if (ratingNUM == 5) {
                    five.setSelected(true);

                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            entered = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            entered = false;
        }

    }

    public void makeWestPanel(JFrame main, PhotographContainer imageLibrary) {
        westPanel = new JPanel();
        westPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.5;
        c.weighty = 0.5;

        for (int i = 0; i < imageLibrary.getPhotos().size(); i++) {
            Photograph temp = imageLibrary.getPhotos().get(i);
            BufferedImage image = temp.getImageData();
            Image dimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            JLabel img = new JLabel(new ImageIcon(dimg));
            JLabel caption = new JLabel("<html>" + temp.getFilename() + ", " + temp.getCaption() + "<br>" + temp.getDateTaken()
                    + "<br>Rated: " + Integer.toString(temp.getRating()) + "</html>");

            img.setPreferredSize(new Dimension(100, 100));

            Mouse m = new Mouse(i);
            img.addMouseListener(m);
            caption.addMouseListener(m);

            try {
                thumbnailCaptions.add(i, caption);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println(e);
                e.printStackTrace();
            }

            c.gridx = 0;
            c.gridy = i;

            westPanel.add(img, c);

            c.gridx = 1;

            westPanel.add(caption, c);
        }

        main.add(westPanel, "West");

    }

    public void makeSouthPanel(JFrame main, int ratingNUM) {
        southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        rating = new ButtonGroup();
        one = new JRadioButton("1");
        one.setActionCommand("1");
        one.addActionListener(new ratingButtonListener());

        two = new JRadioButton("2");
        two.setActionCommand("2");
        two.addActionListener(new ratingButtonListener());

        three = new JRadioButton("3");
        three.setActionCommand("3");
        three.addActionListener(new ratingButtonListener());

        four = new JRadioButton("4");
        four.setActionCommand("4");
        four.addActionListener(new ratingButtonListener());

        five = new JRadioButton("5");
        five.setActionCommand("5");
        five.addActionListener(new ratingButtonListener());

        if (ratingNUM == 1) {
            one = new JRadioButton("1", true);
            one.setActionCommand("1");
            one.addActionListener(new ratingButtonListener());
        }

        if (ratingNUM == 2) {
            two = new JRadioButton("2", true);
            two.setActionCommand("2");
            two.addActionListener(new ratingButtonListener());
        }

        if (ratingNUM == 3) {
            three = new JRadioButton("3", true);
            three.setActionCommand("3");
            three.addActionListener(new ratingButtonListener());
        }

        if (ratingNUM == 4) {
            four = new JRadioButton("4", true);
            four.setActionCommand("4");
            four.addActionListener(new ratingButtonListener());
        }

        if (ratingNUM == 5) {
            five = new JRadioButton("5");
            five.setActionCommand("5");
            five.addActionListener(new ratingButtonListener());
        }

        rating.add(one);
        rating.add(two);
        rating.add(three);
        rating.add(four);
        rating.add(five);

        JLabel ratingTXT = new JLabel("Rating");

        c.gridx = 0;
        southPanel.add(ratingTXT);

        c.gridx = 1;
        southPanel.add(one);

        c.gridx = 2;
        southPanel.add(two);

        c.gridx = 3;
        southPanel.add(three);

        c.gridx = 4;
        southPanel.add(four);

        c.gridx = 5;
        southPanel.add(five);

        main.add(southPanel, "South");
    }

    public void makeCentralPanel(JFrame main, Photograph p) {
        centerPanel = new JPanel();

        currentImage = p;

        BufferedImage image = p.getImageData();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Image dimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        centerImage = new JLabel(new ImageIcon(dimg));

        centerPanel.add(centerImage);

        main.add(centerPanel, "Center");
    }

    public PhotographContainer getImageLibrary() {
        return imageLibrary;
    }

    public void setImageLibrary(PhotographContainer imageLibrary) {
        this.imageLibrary = imageLibrary;
    }

    public static void main(String args[]) {
        PhotographContainer test = new PhotographContainer("test");
        Photograph sabrina = new Photograph("A good friend.", "Sareena", "2017-07-16", 3);
        Photograph harris = new Photograph("A best friend.", "Harris", "2018-07-16", 4);
        Photograph friends = new Photograph("Some great friends.", "Friends", "2019-07-16", 5);
        Photograph morning = new Photograph("A lone red leaf.", "The Steps of the Rotunda", "2018-02-16", 1);
        Photograph range = new Photograph("A pretty fall day.", "The Range", "2019-07-20", 2);

        sabrina.loadImageData("Sareena.jpg");
        harris.loadImageData("Harris.jpg");
        friends.loadImageData("Friends.jpg");
        morning.loadImageData("RotundaMorning.jpg");
        range.loadImageData("RotundaNight.jpg");

        test.addPhoto(sabrina);
        test.addPhoto(harris);
        test.addPhoto(friends);
        test.addPhoto(morning);
        test.addPhoto(range);

        new PhotoViewer(test);

    }

}
