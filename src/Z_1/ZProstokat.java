package Z_1;

public class ZProstokat {

    int w,h;
    char i,o;

    int counts_kwadrat = 0, counts_prostokat = 0;


    public ZProstokat(int w, char i, char o) {
        this (w, w, i, o);
    }

    public ZProstokat(int w, int h, char i, char o) {
        this.w = w;
        this.h = h;
        this.i = i;
        this.o = o;

    //    licz(w, h, i, o);
    }

    /*public licz(int w, int h, char i, char o){
        if (w == h && w >= 1 && (w == 1 && h == 1 && i != o ))
            counts_kwadrat += 1;
        else
            try {
                throw new KwadratWrongException("Blad w obsludze kwadratu");
            } catch (KwadratWrongException e) {
                e.printStackTrace();
            }

        if (w != h && w >= 1 && h >= 1 && (w == 1 && h == 1 && i != o ))
            counts_prostokat += 1;
        else
            try {
                throw new ProstokatWrongException("Blad w obsludze prostokata");
            } catch (ProstokatWrongException e) {
                e.printStackTrace();
            }
    }*/



    public void rysuj() {
        int policzone; //liczy ilość znaków na zewnątrz figury
        String figura; //przyjumuje nazwe odpowiedniej figury
        int count; //przyjmuje wartosc odpowiedniej figury

        //może jakoś setterami to powinienem złatwić?! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if (w == h) {
            figura = "Kwadrat";
            count = counts_kwadrat;
        }
        else{
            figura = "Prostokat";
            count = counts_prostokat;
        }

        policzone = (2*w + ((h-2)*2));

        System.out.println(figura + "(" + count + ")" + " rozmiaru " + w + " x " + h + ", " + policzone + "\n");

        //poniżej drukuję figurę o odpowiednich znakach i długościach
        for (int a = 0; a < w; a++){
            if (a == 0 || a == w)
                for (int b = 0; b < h; b++) {
                    System.out.println(i);
                }
            else
                for (int b = 0; b < h; b++){
                    if (b == 0 || b == h)
                        System.out.println(i);
                    else
                        System.out.println(o);
                }
        }
    }

    private class KwadratWrongException extends Throwable {
        public KwadratWrongException(String s) {
            return;
        }
    }

    private class ProstokatWrongException extends Throwable {
        public ProstokatWrongException(String s) {
            return;
        }
    }
}
