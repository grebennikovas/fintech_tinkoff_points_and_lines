package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // HashMap для хранения пар: (линия, количество точек на ней)
        HashMap<Line,Integer> map = new HashMap<>();
        // Список точек, поступающий на вход
        List<Point> points = new ArrayList<>();
        // Линии, которым принадлежит данная точка
        List<Line> lines = new ArrayList<>();
        // Получить количество точек на плоскости
        int count = s.nextInt();
        // Для каждой точки
        for (int i=0;i<count;i++){
            // Создать объект Point
            Point new_point = new Point(s.nextInt(),s.nextInt());
            // Для каждой известной точки
            points.forEach(
                    (point) -> {
                        // Создать прямую по двум точкам: текущей и новой
                        Line l = new Line(new_point,point);
                        // Если эта линия не учтена для данной точки
                        if (!lines.contains(l)) {
                            // Добавить линию в HashMap. Если новая, то число точек = 2
                            // Если линия в HasMap уже есть, увеличить количество точек на ней на 1
                            map.put(l, map.getOrDefault(l, 1) + 1);
                            // Добавить линию в список рассмотренных для данной точки
                            lines.add(l);
                        }
                    }
            );
            // Добавить точку в список точек
            points.add(new_point);
            // Очистить список линий, смежных текущей точке
            lines.clear();
        }
        // Количество способов
        int sum = 0;
        // Для каждой линии
        for (int a : map.values()){
            // Сложить количество способов раскрасить линию
            sum += (a-1)*2;
        }
        System.out.println(sum);
    }
}
// Линия на плоскости, y=kx+b
class Line{
    private float k=0;
    // Смещение от начала координат
    private float b=0;
    // TRUE, если линия параллельна ординате
    private boolean vertical = false;
    // Конструктор линии по двум точкам
    public Line(Point a,Point b){
        // Если k<>0
        if(a.x!=b.x && a.y!=b.y){
            this.k=(a.y-b.y)/(a.x-b.x);
            this.b = (-1)*(k*a.x) + a.y;
        } // Горизонтальная линия
        else if (a.x==b.x){
            this.b=a.y;
        } // Вертикальная линия
        else {
            this.vertical = true;
            this.b = a.x;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Float.compare(line.k, k) == 0 && b == line.b && vertical == line.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(k, b, vertical);
    }
}
// Точка на плоскости (x,y)
class Point{
    int x;
    int y;
    Point(int x,int y){
        this.x=x;
        this.y=y;
    }
}