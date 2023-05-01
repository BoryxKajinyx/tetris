package tetris;

public class Lik {

    public enum Liki {
        PrazenLik, Z_Oblika, S_Oblika, Črta,
        T_oblika, Kvadrat, L_Oblika, Obrnjen_L
    }

    private Liki trenutniLik;
    private final int[][] koordinati;

    public Lik() {
        koordinati = new int[4][2];
        setLik(Liki.PrazenLik);
    }

    void setLik(Liki lik) {
        int[][][] tabelaKoordinatov = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };

        for (int i = 0; i < 4; i++) {
            System.arraycopy(tabelaKoordinatov[lik.ordinal()], 0, koordinati, 0, 4);
        }

        trenutniLik = lik;
    }

    private void nastaviX(int index, int x) {
        koordinati[index][0] = x;
    }

    private void nastaviY(int index, int y) {
        koordinati[index][1] = y;
    }

    int x(int index) {
        return koordinati[index][0];
    }

    int y(int index) {
        return koordinati[index][1];
    }

    Liki dobiLik() {
        return trenutniLik;
    }

    void nastaviNaključenLik() {
        int x = (int)(Math.random()*7)+1;

        Liki[] values = Liki.values();
        setLik(values[x]);
    }
    int najnižjiY() {
        int m = koordinati[0][1];

        for (int i = 0; i < 4; i++) {
            m = Math.min(m, koordinati[i][1]);
        }

        return m;
    }

    Lik obrniLevo() {
        if (trenutniLik == Liki.Kvadrat) {
            return this;
        }
        var rezultat = new Lik();
        rezultat.trenutniLik = trenutniLik;
        for (int i = 0; i < 4; i++) {
            rezultat.nastaviX(i, y(i));
            rezultat.nastaviY(i, -x(i));
        }
        return rezultat;
    }

    Lik obrniDesno() {
        if (trenutniLik == Liki.Kvadrat) {
            return this;
        }
        var rezultat = new Lik();
        rezultat.trenutniLik = trenutniLik;
        for (int i = 0; i < 4; i++) {
            rezultat.nastaviX(i, -y(i));
            rezultat.nastaviY(i, x(i));
        }
        return rezultat;
    }
}