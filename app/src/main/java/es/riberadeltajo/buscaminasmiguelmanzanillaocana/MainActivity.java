package es.riberadeltajo.buscaminasmiguelmanzanillaocana;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import Dialogos.DialogoCon;
import Dialogos.DialogoDif;
import Dialogos.DialogoIns;
import Dialogos.DialogoSeleciona;
import Dialogos.Dialogovd;

public class MainActivity extends AppCompatActivity {
    int filas = 0;
    int columnas = 0;
    int minas = 0;
    int[][] cuadricula;
    boolean[][] cuadriculaBoolean;
    Button[][] cuadriculaBotones;
    ImageButton[][] cuadriculaBotonesIM;
    int Dificultad;
    int Foto = 1;
    int ganar = 0;
    int size = 0;
    private Menu miMenu;
    private int contTiempo = 0;
    private int seg = 0;
    private CountDownTimer reloj;
    public void setDificultad(int dificultad) {
        Dificultad = dificultad;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout g = findViewById(R.id.Principal);
        g.post(new Runnable() {
            @Override
            public void run() {
                CrearTablero();
            }
        });
    }
    public void CrearTablero() {
        reloj(2);
        seg = 0;
        actualizarTiempo(seg);
        ganar = 0;
        contTiempo = 0;
        int color1 = 0;
        int color2 = 0;
        GridLayout g = new GridLayout(getApplicationContext());
        ConstraintLayout c = (ConstraintLayout) findViewById(R.id.Principal);
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.setMargins(0, 0, 0, 0);
        param.height = ViewGroup.LayoutParams.MATCH_PARENT;
        param.width = ViewGroup.LayoutParams.MATCH_PARENT;
        switch (Dificultad) {
            case 2:
                filas = 12;
                columnas = 12;
                minas = 30;
                size = 12;
                break;
            case 3:
                filas = 16;
                columnas = 16;
                minas = 60;
                size = 6;
                break;
            default:
                filas = 8;
                columnas = 8;
                minas = 10;
                size = 20;
                break;
        }
        actualizarContadorEnMenu(ganar);
        switch (Foto) {
            case 1:
                color1 = R.drawable.bomba_verde;
                color2 = R.drawable.bomba_verde_oscuro;
                break;
            case 2:
                color1 = R.drawable.bomba_azul;
                color2 = R.drawable.bomba_azul_oscuro;
                break;
            case 3:
                color1 = R.drawable.bomba_marron;
                color2 = R.drawable.bomba_marron_oscuro;
                break;
            case 4:
                color1 = R.drawable.bomba_amarilla;
                color2 = R.drawable.bomba_negra;
                break;
            case 5:
                color1 = R.drawable.bomba_negra;
                color2 = R.drawable.bomba_blanca;
                break;
        }
        g.setRowCount(filas);
        g.setColumnCount(columnas);
        g.setLayoutParams(param);
        int height = c.getHeight();
        int width = c.getWidth();
        Tablero t = new Tablero(filas, columnas, minas);
        t.Inicializar();
        t.Mostrar();
        cuadricula = t.getCuadricula();
        cuadriculaBoolean = new boolean[filas][columnas];
        cuadriculaBotones = new Button[filas][columnas];
        cuadriculaBotonesIM = new ImageButton[filas][columnas];
        int contColor = 1;
        for (int i = 0; i < columnas; i++) {
            contColor--;
            for (int j = 0; j < filas; j++) {
                cuadriculaBoolean[i][j] = false;
                if (t.esMina(i, j)) {
                    ImageButton ib = new ImageButton(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width / columnas, height / filas);
                    layoutParams.setMargins(0, 0, 0, 0);
                    ib.setLayoutParams(layoutParams);
                    ib.setId(t.verPos(i, j));
                    ib.setTag(i + "-" + j);
                    cuadriculaBotonesIM[i][j] = ib;
                    if (contColor % 2 == 0) {
                        ib.setBackgroundResource(color1);
                    } else {
                        ib.setBackgroundResource(color2);
                    }
                    g.addView(ib);
                    ib.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ElegirFoto(ib);
                            ib.setBackgroundResource(R.drawable.bomba_roja);
                            String mensaje = "Tocaste una mina.";
                            String titulo = "HAS PERDIDO!!";
                            descubrirMinas(t);
                            perderoGanar(titulo, mensaje);
                        }
                    });
                    ib.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            ib.setImageResource(R.drawable.flag);
                            ganar++;
                            actualizarContadorEnMenu(ganar);
                            if (ganar == minas) {
                                String titulo = "HAS GANADO!!!";
                                String mensaje = "¿Quieres volver a jugar?";
                                perderoGanar(titulo, mensaje);

                            }
                            return true;
                        }
                    });
                    contColor++;
                } else {
                    Button b = new Button(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width / columnas, height / filas);
                    layoutParams.setMargins(0, 0, 0, 0);
                    b.setLayoutParams(layoutParams);
                    b.setId(t.verPos(i, j));
                    b.setTag(i + "-" + j);
                    b.setTextSize(size);
                    cuadriculaBotones[i][j] = b;
                    if (contColor % 2 == 0) {
                        b.setBackgroundResource(color1);
                    } else {
                        b.setBackgroundResource(color2);
                    }

                    g.addView(b);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (contTiempo == 0) {
                                reloj(1);
                                contTiempo++;
                            }
                            if (view.getId() == 0) {
                                String tag = view.getTag().toString();
                                System.out.println(tag);
                                String[] partes = tag.split("-");
                                int fila = Integer.parseInt(partes[0]);
                                int columna = Integer.parseInt(partes[1]);
                                quitarCeros(fila, columna);
                                b.setBackgroundResource(R.drawable.bomba_numero_0);
                            } else {
                                b.setText(view.getId() + "");
                                b.setBackgroundResource(R.drawable.bomba_numero);
                            }

                        }
                    });
                    b.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            String titulo = "HAS PERDIDO!!!";
                            String mensaje = "Pusiste una bandera donde no correspondia";
                            perderoGanar(titulo, mensaje);
                            descubrirMinas(t);
                            return true;
                        }
                    });
                    contColor++;
                }
            }
        }
        c.addView(g);
    }
    private void reloj(int i) {
        if (reloj != null) {
            reloj.cancel();
        }
        reloj = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seg += 1;
                actualizarTiempo(seg);
            }
            @Override
            public void onFinish() {
            }
        };
        if (i == 1) {
            reloj.start();
        }
        if (i == 2) {
            reloj.cancel();
            actualizarTiempo(seg);
        }
    }
    private void descubrirMinas(Tablero t) {
        for (int i = 0; i < cuadriculaBotonesIM.length; i++) {
            for (int j = 0; j < cuadriculaBotonesIM[i].length; j++) {
                if (t.esMina(i, j)) {
                    cuadriculaBotonesIM[i][j].setBackgroundResource(R.drawable.bomba_roja);
                    ElegirFoto(cuadriculaBotonesIM[i][j]);
                }
            }
        }
    }
    private void ElegirFoto(ImageButton ib) {
        switch (Foto) {
            case 1:
                ib.setImageResource(R.drawable.bomba1);
                break;
            case 2:
                ib.setImageResource(R.drawable.bomba2);
                break;
            case 3:
                ib.setImageResource(R.drawable.bomba3);
                break;
            case 4:
                ib.setImageResource(R.drawable.bomba4);
                break;
            case 5:
                ib.setImageResource(R.drawable.bomba5);
                break;
        }
    }
    private void perderoGanar(String titulo, String mensaje) {
        reloj(2);
        if (titulo.equals("HAS GANADO!!!")) {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.victoria);
            mp.start();
            mensaje = mensaje + "\nHas tardado " + seg + " segundos";
        } else {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.derrota);
            mp.start();
        }
        Dialogovd midialog = new Dialogovd(mensaje, titulo);
        midialog.show(getSupportFragmentManager(), "");
    }
    private void quitarCeros(int f, int c) {
        if (cuadriculaBoolean[f][c] == false) {
            cuadriculaBoolean[f][c] = true;
            for (int y = -1; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    try {
                        cuadriculaBotones[f + y][c + x].performClick();
                    } catch (Exception e) {
                        Log.i("Error", "La mina se ha salido fuera del tablero");
                    }
                }
            }
        }
    }
    public void recrearTablero() {
        ConstraintLayout c = findViewById(R.id.Principal);
        c.removeAllViews();
        CrearTablero();
    }
    private void actualizarContadorEnMenu(int contador) {
        if (miMenu != null) {
            MenuItem contadorMenuItem = miMenu.findItem(R.id.minas);
            contadorMenuItem.setTitle("Minas restantes: " + (this.minas - contador));
        }
    }
    private void actualizarTiempo(int Seg) {
        if (miMenu != null) {
            MenuItem contadorMenuItem = miMenu.findItem(R.id.Tiempo);
            contadorMenuItem.setTitle("Llevas " + Seg + " S");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        miMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Ins) {
            DialogoIns midialog = new DialogoIns();
            midialog.show(getSupportFragmentManager(), "Dialogo Instruciones");
        } else if (item.getItemId() == R.id.Dif) {
            DialogoDif midialog2 = new DialogoDif();
            midialog2.show(getSupportFragmentManager(), "Dialogo Dificultad");
        } else if (item.getItemId() == R.id.Se) {
            DialogoSeleciona midialog3 = new DialogoSeleciona();
            midialog3.show(getSupportFragmentManager(), "Dialogo Bomba");
        } else if (item.getItemId() == R.id.btnReinciar) {
            DialogoCon midialog = new DialogoCon();
            midialog.show(getSupportFragmentManager(), "Dialogo Confirmar");
        }
        return super.onOptionsItemSelected(item);
    }
}