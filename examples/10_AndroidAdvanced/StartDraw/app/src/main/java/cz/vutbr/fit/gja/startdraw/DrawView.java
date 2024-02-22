package cz.vutbr.fit.gja.startdraw;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class DrawView extends View {
  Paint paint = new Paint();
  float toX=100.0f;
  float toY=100.0f;
  Bitmap bm=null;
  int mode=8;
  boolean matrix8[][];
  boolean matrix6[][];
  boolean matrix4[][];
  Rect bounds = new Rect();
  ArrayList<Bitmap>images=new ArrayList<Bitmap>();
  int actualPointer=0;
  StartDraw activity;
  public DrawView(Context context) {
    super(context);
    activity=(StartDraw)context;
    matrix8=new boolean[8][8];
    matrix6=new boolean[6][6];
    matrix4=new boolean[4][4];
    coverMatrices();
  }
  
  private void coverMatrices(){
    for (int i=0;i<8;i++)
      for (int j=0;j<8;j++)
        matrix8[i][j]=false;
    for (int i=0;i<6;i++)
      for (int j=0;j<6;j++)
        matrix6[i][j]=false;
    for (int i=0;i<4;i++)
      for (int j=0;j<4;j++)
        matrix4[i][j]=false;
    invalidate();
  }
  
  private void clearMatrices(){
    for (int i=0;i<8;i++)
      for (int j=0;j<8;j++)
        matrix8[i][j]=true;
    for (int i=0;i<6;i++)
      for (int j=0;j<6;j++)
        matrix6[i][j]=true;
    for (int i=0;i<4;i++)
      for (int j=0;j<4;j++)
        matrix4[i][j]=true;
    invalidate();
  }

  @Override
  public void onDraw(Canvas canvas) {
      //canvas.drawLine(0, 0, toX, toY, paint);
    if (bm!=null){
      canvas.drawBitmap(bm, 0, 0, paint);
      boolean mat[][]=mode==8?matrix8:mode==6?matrix6:matrix4;
      int w=this.getWidth()/mode;
      int h=this.getHeight()/mode;
      for (int i=0;i<mode;i++){
        for (int j=0;j<mode;j++){
          if (!mat[i][j]){
            paint.setColor(Color.WHITE);
            paint.setStyle(Style.FILL);
            int x=i*w;
            int y=j*h;
            int toX=x+w;
            int toY=y+h;
            canvas.drawRect(x, y, toX, toY,paint);
            int letter='A'+i;
            int number='1'+j;
            String s=Character.toString((char)letter)+Character.toString((char)number);
            paint.getTextBounds(s,0,s.length(),bounds);
            int textHeight = bounds.height();
            int textWidth = bounds.width();
            int xOffset=(w-textWidth)/2;
            int yOffset=(h+textHeight)/2;
            paint.setColor(Color.BLACK);
            paint.setStyle(Style.STROKE);
            canvas.drawText(s, x+xOffset, y+yOffset,paint);
            canvas.drawRect(x, y, toX, toY,paint);
          }
        }
      }
    }
  }
  
  public void addImage(Bitmap bitmap){
    images.add(bitmap);
    invalidate();
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float eventX = event.getX();
    float eventY = event.getY();

    switch (event.getAction()) {
    case MotionEvent.ACTION_DOWN:
       boolean mat[][]=mode==8?matrix8:mode==6?matrix6:matrix4;
       int w=this.getWidth();
       int h=this.getHeight();
       int matX=(int)eventX/(w/mode);
       int matY=(int)eventY/(h/mode);
       mat[matX][matY]=true;
       invalidate();
       return true;
    case MotionEvent.ACTION_MOVE:
      break;
    case MotionEvent.ACTION_UP:
      // nothing to do 
      break;
    default:
      return false;
    }
    return false;
  }

  public void start(){
    if (images.isEmpty())
      Toast.makeText(activity, "Unable to start, no data loaded", Toast.LENGTH_LONG).show();
    else{
      bm=images.get(0);
      invalidate();
    }
  }
  
  private void move(){
    bm=images.get(actualPointer);
    coverMatrices();
    invalidate();
  }
  
  public void next(){
    if (!images.isEmpty()){
      actualPointer=(actualPointer+1)%images.size();
      move();
    }
  }
  
  public void prev(){
    if (!images.isEmpty()){
      actualPointer-=1;
      if (actualPointer<0)
        actualPointer=images.size()-1;
      move();
    }
  }
  
  public void clear(){
    clearMatrices();
    invalidate();
  }
  
  public void changeGrid(int val){
    mode=val;
    invalidate();
  }
  
}