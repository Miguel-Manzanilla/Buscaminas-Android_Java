package Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import es.riberadeltajo.buscaminasmiguelmanzanillaocana.MainActivity;
import es.riberadeltajo.buscaminasmiguelmanzanillaocana.R;

public class DialogoDif extends DialogFragment {
    private int dificultad;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View vistaDialogo = LayoutInflater.from(getActivity()).inflate(R.layout.radio_button_layaout, null);

        AlertDialog.Builder myBuilder = new AlertDialog.Builder(getActivity());
        myBuilder.setView(vistaDialogo);
        myBuilder.setTitle("Dificultad");
        RadioGroup radioGroup = vistaDialogo.findViewById(R.id.dificultades);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbPrincipiante) {
                    dificultad=1;
                } else if (checkedId == R.id.rbAmateur) {
                    dificultad=2;
                } else if (checkedId == R.id.rbAvanzado) {
                    dificultad=3;
                }
            }
        });
        myBuilder.setPositiveButton("VOLVER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity m=new MainActivity();
                m.setDificultad(dificultad);
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setDificultad(dificultad);
                    ((MainActivity) getActivity()).recrearTablero();
                }
                dialog.cancel();
            }
        });
        return myBuilder.create();
    }
}
