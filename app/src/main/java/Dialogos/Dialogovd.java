package Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import es.riberadeltajo.buscaminasmiguelmanzanillaocana.MainActivity;

public class Dialogovd extends DialogFragment {
    private String mensaje;
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Dialogovd(String mensaje, String titulo) {
        this.mensaje = mensaje;
        this.titulo = titulo;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(getActivity());
        myBuilder.setTitle(this.getTitulo());
        myBuilder.setMessage(this.getMensaje());
        myBuilder.setPositiveButton("REINICIAR JUEGO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity m = new MainActivity();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).recrearTablero();
                }
                dialog.cancel();
            }
        });
        myBuilder.setNegativeButton("SALIR DEL JUEGO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
                dialog.cancel();
            }
        });
        return myBuilder.create();
    }
}