package Z_4;

abstract class Spiewak {
    String nazwisko;
    static int ilosc;
    int nr = 0;

    Spiewak(String nazwisko)
    {
        this.nazwisko = nazwisko;
        nr = ++ilosc;
    }

    abstract String spiewaj();

    public String toString()
    {
        return ("(" + nr +") " + nazwisko + ": " + spiewaj());
    }

    public static Spiewak najglosniej(Spiewak... tab) {
        int ostatninajglosniejszy = 0;
        Spiewak ostatninajglosniejszySpiewak = null;
        for (Spiewak s : tab) {
            int najglosniej = najglosniej(s.spiewaj());
            if (najglosniej > ostatninajglosniejszy) {
                ostatninajglosniejszy = najglosniej;
                ostatninajglosniejszySpiewak = s;
            }
        }
        return ostatninajglosniejszySpiewak;
    }

    private static int najglosniej(String s) {
        int i = 0;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                i++;
            }
        }
        return i;
    }

}