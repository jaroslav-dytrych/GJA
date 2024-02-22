package cz.vutbr.fit.gja.kufr.client;

import java.util.ArrayList;
import java.util.TreeMap;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widgetideas.client.ProgressBar;

/**
 * @author Jan Kouril
 */
public class Kufr implements EntryPoint, ResizeListener, FileListener {

  int mouseX = 0, mouseY = 0;
  boolean playing = false;
  boolean matrix8[][];
  boolean matrix6[][];
  boolean matrix4[][];
  int mode = 8;
  int actualIndex = 0;

  Image actualImage;
  Kufr kufr;
  ProgressBar progress;
  TreeMap<String, Image> data;
  ArrayList<Image> images;
  Canvas canvas;
  MyDialog myDialog;
  GridDialog gridDialog;
  MenuBar menu;

  @Override
  public void onModuleLoad() {
    kufr = this;
    data = new TreeMap<String, Image>();
    images = new ArrayList<Image>();
    actualImage = null;
    matrix8 = new boolean[8][8];
    matrix6 = new boolean[6][6];
    matrix4 = new boolean[4][4];

    myDialog = new MyDialog();
    gridDialog = new GridDialog();

    myDialog.setVisible(false);
    gridDialog.setVisible(false);

    myDialog.addFileListener(this);
    gridDialog.addResizeListener(this);

    createMenu();
    createCanvas();
    createProgressBar();
  }

  void createMenu() {
    Command addFiles = new Command() {
      @Override
      public void execute() {
        if (!playing) {
          myDialog.setVisible(true);
          myDialog.center();
        } else {
          Window.alert("Can't add images while playing - restart application");
        }
      }
    };
    MenuBar fileMenu = new MenuBar(true);
    fileMenu.addItem("Add files", addFiles);
    MenuBar gameMenu = new MenuBar(true);
    Command startGame = new Command() {
      @Override
      public void execute() {
        if (kufr.data.size() == 0) {
          Window.alert("Sorry - no images inserted");
        } else {
          playing = true;
          images.addAll(kufr.data.values());
          actualImage = images.get(0);
          actualIndex = 0;
          progress.setVisible(true);
          coverMatrices();
        }
      }
    };
    gameMenu.addItem("Start", startGame);
    gameMenu.addSeparator();
    Command next = new Command() {
      @Override
      public void execute() {
        if (images.isEmpty())
          return;
        actualIndex = (actualIndex + 1) % images.size();
        actualImage = images.get(actualIndex);
        coverMatrices();
      }
    };
    gameMenu.addItem("Next", next);
    Command previous = new Command() {
      @Override
      public void execute() {
        if (images.isEmpty())
          return;
        actualIndex--;
        if (actualIndex < 0)
          actualIndex = images.size() - 1;
        actualImage = images.get(actualIndex);
        coverMatrices();
      }

    };
    gameMenu.addItem("Previous", previous);
    gameMenu.addSeparator();
    Command cover = new Command() {
      @Override
      public void execute() {
        if (images.isEmpty())
          return;
        coverMatrices();
      }
    };
    gameMenu.addItem("Cover", cover);
    Command clear = new Command() {
      @Override
      public void execute() {
        if (images.isEmpty())
          return;
        clearMatrices();
      }
    };
    gameMenu.addItem("Clear", clear);
    MenuBar gridMenu = new MenuBar(true);
    Command set = new Command() {
      @Override
      public void execute() {
        gridDialog.setVisible(true);
        gridDialog.center();
      }
    };
    gridMenu.addItem("Resize", set);
    menu = new MenuBar();
    menu.addItem("File", fileMenu);
    menu.addItem("Game", gameMenu);
    menu.addItem("Grid", gridMenu);
    RootPanel.get().add(menu);
  }

  void createCanvas() {
    canvas = Canvas.createIfSupported();
    canvas.addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        if (playing) {
          mouseX = event.getRelativeX(canvas.getElement());
          mouseY = event.getRelativeY(canvas.getElement());
          boolean mat[][] = mode == 8 ? matrix8 : mode == 6 ? matrix6 : matrix4;
          int w = actualImage.getWidth();
          int h = actualImage.getHeight();
          int matX = mouseX / (w / mode);
          int matY = mouseY / (h / mode);
          mat[matX][matY] = true;
          repaint();
        }
      }
    });
    RootPanel.get().add(canvas);
  }

  void createProgressBar() {
    progress = new ProgressBar();
    progress.addStyleName("gwt-ProgressBar-shell");
    RootPanel.get().add(progress);
    progress.setVisible(false);
  }

  private void clearMatrices() {
    for (int i = 0; i < 8; i++)
      for (int j = 0; j < 8; j++)
        matrix8[i][j] = true;
    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 6; j++)
        matrix6[i][j] = true;
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++)
        matrix4[i][j] = true;
    repaint();
  }

  private void coverMatrices() {
    for (int i = 0; i < 8; i++)
      for (int j = 0; j < 8; j++)
        matrix8[i][j] = false;
    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 6; j++)
        matrix6[i][j] = false;
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++)
        matrix4[i][j] = false;
    repaint();
  }

  void repaint() {
    boolean mat[][] = mode == 8 ? matrix8 : mode == 6 ? matrix6 : matrix4;
    int width = actualImage.getWidth() / mode * mode;
    int height = actualImage.getHeight() / mode * mode;
    actualImage.setPixelSize(width, height);
    canvas.setPixelSize(width, height);
    canvas.setCoordinateSpaceHeight(height);
    canvas.setCoordinateSpaceWidth(width);
    ImageElement e = ImageElement.as(actualImage.getElement());
    Context2d dc = canvas.getContext2d();
    dc.drawImage(e, 0, 0);
    int w = width / mode;
    int h = height / mode;
    int count = 0;
    for (int i = 0; i < mode; i++) {
      for (int j = 0; j < mode; j++) {
        if (!mat[i][j]) {
          dc.setFillStyle("white");
          dc.fillRect(i * w, j * h, w, h);
          dc.setFillStyle("black");
          dc.strokeRect(i * w, j * h, w, h);
          dc.setFont("15px sans-serif");
          dc.setTextAlign("center");
          dc.setTextBaseline("middle");
          int letter = 'A' + i;
          int number = '1' + j;
          String s = Character.toString((char) letter) + Character.toString((char) number);
          dc.fillText(s, i * w + w / 2, j * h + h / 2);
        } else {
          count++;
        }
      }
    }
    double increment = 100.0 / (mode * mode);
    double percents = increment * count;
    progress.setProgress(percents);
    progress.setWidth(width + "px");
    menu.setWidth(width + "px");
  }

  void setMode(int m) {
    mode = m;
    if (playing)
      repaint();
  }

  @Override
  public void fileAdded(String a) {
    data.put(a, new Image(a));
  }

  @Override
  public void fileRemoved(String a) {
    data.remove(a);

  }

  @Override
  public void resized(int i) {
    setMode(i);
  }
}