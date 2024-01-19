package Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoIns extends DialogFragment {

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder myBuilder=new AlertDialog.Builder(getActivity());
        myBuilder.setTitle("Instruciones");
        myBuilder.setMessage("El objetivo del Buscaminas es descubrir todo el área gris sin detonar las minas. Así que el jugador tiene que ser muy observador para no cometer ningún fallo. Al pulsar sobre las celdas, aparece una zona revelada y números que determinan la proximidad de una mina.\n");
        myBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return  myBuilder.create();
    }
}
