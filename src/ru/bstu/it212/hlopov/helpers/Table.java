package ru.bstu.it212.hlopov.helpers;

public class Table {
    public static void showTable(int n, boolean sum) {
        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= n; ++j) {
                if(!sum) {
                    System.out.printf("%d x %d = %d\t",j,i,j*i);
                }
                else if(sum) {
                    System.out.printf("%d + %d = %d\t",j,i,j+i);
                }
            }
            System.out.print("\n");
        }
    }
    public static void showTableWhile(int n, boolean sum) {
        int i = 1;
        int j = 1;
        while (i <= n) {
            j = 1;
            while (j <= n) {
                if(!sum) {
                    System.out.printf("%d x %d = %d\t",j,i,j*i);
                }
                else if(sum) {
                    System.out.printf("%d + %d = %d\t",j,i,j+i);
                }
                ++j;
            }
            System.out.print("\n");
            ++i;
        }
    }
}
