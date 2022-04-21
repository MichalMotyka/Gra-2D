package main;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;

public interface MapInterface {
    void addColiders();
    void drawColider();
    void draw(Graphics2D g2) throws IOException;
    void drawBackground(Graphics2D g2);
}
