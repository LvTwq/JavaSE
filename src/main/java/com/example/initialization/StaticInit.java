package com.example.initialization;

public class StaticInit {
    public static void main(String[] args) {
        System.out.println("====创建新橱柜====");
        new Cupboard();
        System.out.println("====创建新橱柜====");
        new Cupboard();
        table.f2(1);
        cupboard.f3(1);
    }

    static Table table = new Table();
    static Cupboard cupboard = new Cupboard();


    static class Bowl {
        public Bowl(int marker) {
            System.out.println("Bowl(" + marker + ")");
        }

        void f1(int marker) {
            System.out.println("f1(" + marker + ")");
        }
    }

    static class Table {
        static Bowl bowl = new Bowl(1);

        public Table() {
            System.out.println("Table()");
            bowl2.f1(1);
        }

        void f2(int marker) {
            System.out.println("f2(" + marker + ")");
        }

        static Bowl bowl2 = new Bowl(2);
    }

    static class Cupboard {
        Bowl bowl3 = new Bowl(3);

        static Bowl bowl4 = new Bowl(4);

        public Cupboard() {
            System.out.println("Cupboard()");
            bowl4.f1(2);
        }

        void f3(int marker) {
            System.out.println("f3(" + marker + ")");
        }

        static Bowl bowl5 = new Bowl(5);
    }
}



