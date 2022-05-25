package tetris;

public class Lik {

    protected enum Liki {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    }

    private Liki trenutniLik;
    private int[][] koordinati;

    public Lik() {

        koordinati = new int[4][2];
        nastaviLik(Liki.NoShape);
    }

    void nastaviLik(Liki lik) {

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

    private void setX(int index, int x) {

        koordinati[index][0] = x;
    }

    private void setY(int index, int y) {

        koordinati[index][1] = y;
    }

    int x(int index) {

        return koordinati[index][0];
    }

    int y(int index) {

        return koordinati[index][1];
    }

    Liki getLik() {

        return trenutniLik;
    }

    void setRandomLik() {

        int x = (int)(Math.random()*7)+1;

        Liki[] values = Liki.values();
        nastaviLik(values[x]);
    }

    public int minX() {

        int m = koordinati[0][0];

        for (int i = 0; i < 4; i++) {

            m = Math.min(m, koordinati[i][0]);
        }

        return m;
    }


    int minY() {

        int m = koordinati[0][1];

        for (int i = 0; i < 4; i++) {

            m = Math.min(m, koordinati[i][1]);
        }

        return m;
    }

    Lik obrniLevo() {

        if (trenutniLik == Liki.SquareShape) {

            return this;
        }

        var result = new Lik();
        result.trenutniLik = trenutniLik;

        for (int i = 0; i < 4; i++) {

            result.setX(i, y(i));
            result.setY(i, -x(i));
        }

        return result;
    }

    Lik obrniDesno() {

        if (trenutniLik == Liki.SquareShape) {

            return this;
        }

        var result = new Lik();
        result.trenutniLik = trenutniLik;

        for (int i = 0; i < 4; i++) {

            result.setX(i, -y(i));
            result.setY(i, x(i));
        }

        return result;
    }
}