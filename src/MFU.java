/**
 * Created by Антон on 23.02.2018.
 */
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
    /* 3. *** Написать класс МФУ, на котором возможны одновременная печать и сканирование документов,
                при этом нельзя одновременно печатать два документа или сканировать (при печати в консоль
        выводится сообщения "отпечатано 1, 2, 3,... страницы", при сканировании тоже самое только "отсканировано...",
        вывод в консоль все также с периодом в 50 мс.)

*/	// write your code here
public class MFU {
    private final Object monitorPrint = new Object();
    private final Object monitorScan = new Object();
       MFU()
    {}

    private volatile int statePrint=0;
    private volatile int stateScan=0;

    private int  printPaper=0;
    private ArrayList<Integer> masPrintP;
    private int scanPaper=0;


    void printP ()
    {
        try {
            synchronized (monitorPrint)
            {
              if (statePrint==0)
              {
                  // принтер печаетает
                  statePrint=1;
                  this.printPaper++;
                  //masPrintP.add(this.printPaper);

                  System.out.println("\n Отпечатано " + this.printPaper + " Страниц");
                  TimeUnit.SECONDS.sleep(1);
                  statePrint=0;
                  monitorPrint.notifyAll();


              }else monitorPrint.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


        void scanP ()
        {
            try {
                synchronized (monitorScan)
                {
                    if (stateScan==0)
                    {
                        // принтер сканирует
                        stateScan=1;
                        this.scanPaper++;
                        //masPrintP.add(this.printPaper);

                        System.out.println("\n Отcканировано " + this.printPaper + " Страниц");
                        TimeUnit.SECONDS.sleep(5);
                        stateScan=0;
                        monitorScan.notifyAll();


                    }else monitorScan.wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
