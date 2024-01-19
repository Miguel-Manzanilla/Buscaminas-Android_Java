package Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import es.riberadeltajo.buscaminasmiguelmanzanillaocana.MainActivity;
import es.riberadeltajo.buscaminasmiguelmanzanillaocana.R;

public class DialogoCon extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(getActivity());
        myBuilder.setTitle("¿ESTAS SEGURO QUE QUIERES REINICIAR?");
        myBuilder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity m=new MainActivity();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).recrearTablero();
                }
                dialog.cancel();
            }
        });
        myBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return myBuilder.create();
    }
}
