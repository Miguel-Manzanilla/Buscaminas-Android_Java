package es.riberadeltajo.buscaminasmiguelmanzanillaocana;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.Arrays;

public class Tablero {
    private int columnas;
    private int filas;
    private int minas;
    private int[][] cuadricula;

    public int[][] getCuadricula() {
        return cuadricula;
    }

    public void setCuadricula(int[][] cuadricula) {
        this.cuadricula = cuadricula;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getMinas() {
        return minas;
    }

    public void setMinas(int minas) {
        this.minas = minas;
    }

    public Tablero(int columnas, int filas, int minas) {
        this.columnas = columnas;
        this.filas = filas;
        this.minas = minas;
    }

    public void Inicializar() {
        cuadricula = new int[this.filas][this.columnas];
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                cuadricula[i][j] = 0;
            }
        }
        PonerMinas();
        calcularPosiciones();

    }

    private void calcularPosiciones() {
        for (int f = 0; f < this.filas; f++) {
            for (int c = 0; c < this.columnas; c++) {
                if (cuadricula[f][c] == -1) {
                    for (int fAux = f - 1; fAux < f + 2; fAux++) {
                        for (int cAux = c - 1; cAux < c + 2; cAux++) {
                            try {
                                if (cuadricula[fAux][cAux] != -1) {
                                    cuadricula[fAux][cAux]++;
                                }
                            } catch (Exception e) {
                                Log.i("Error", "La mina se ha salido fuera del tablero");
                            }
                        }
                    }
                }
            }
        }
    }

    public void Mostrar() {
        for (int i = 0; i < this.filas; i++) {
            System.out.println(Arrays.toString(cuadricula[i]));
        }

    }

    public void PonerMinas() {
        for (int i = 0; i < this.minas; i++) {
            int f = (int) (Math.random() * this.getFilas());
            int c = (int) (Math.random() * this.getColumnas());
            if (cuadricula[f][c] == -1) {
                i--;
            } else {
                cuadricula[f][c] = -1;
            }
        }
    }

    public boolean esMina(int i, int j) {
        if (cuadricula[i][j] == -1) {
            return true;
        }
        return false;
    }

    public int verPos(int i, int j) {
        for (int k = 0; k < this.filas; k++) {
            for (int l = 0; l < this.columnas; l++) {
                if (i == k && l == j) {
                    return cuadricula[i][j];
                }
            }
        }
        return 0;
    }
}
