import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Frame extends java.awt.Frame {
int x0, y0;//устанавливаем начальные координаты для курсора мыши

    class MouseClick extends MouseAdapter {
        public void mousePressed(MouseEvent e) { //метод срабатывает при нажатии и задержке левой кнопки мыши и запоминает начальные координаты
            x0 = e.getX();
            y0 = e.getY();
        }

        public void mouseDragged(MouseEvent e) {//метод отрисовывает линию между начальным и конеными координатами
            int x1 = e.getX(), y1 = e.getY();
            Line line = new Line(x0, y0, x1, y1); //отрисовка происходит в отдельном классе line
            linked.add(line);
            line.draw(getGraphics());
        }
    }
    private List<Line> linked = new LinkedList<>();
    public void paint(Graphics gr) { //метод для повторной отрисовки всех линий из сохраненного списка
        //требуется для сохранения рисунка после изменений размеров экрана программы
        for(var item : linked) item.draw(gr); //цикл для отрисовки
    }

    public Frame() {

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);}}); //скрытый класс для закрытия окна программы при кликании на крестик
        MouseClick mouseClick = new MouseClick();
        //методы запускают отрисовку линий между точками координат
        addMouseListener(mouseClick);
        addMouseMotionListener(mouseClick);

        setSize(1000, 800); //размер окна
        setTitle("Paint");//название окна
        setVisible(true); //видимость окна
    }



    public static void main(String[] args) {
        new Frame(); //запуск всей программы в главной нити
    }
}
