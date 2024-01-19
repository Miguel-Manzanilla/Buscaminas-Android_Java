package Dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import es.riberadeltajo.buscaminasmiguelmanzanillaocana.Bomba;
import es.riberadeltajo.buscaminasmiguelmanzanillaocana.MainActivity;
import es.riberadeltajo.buscaminasmiguelmanzanillaocana.R;

public class DialogoSeleciona extends DialogFragment {
    private int foto = 1;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View vistaDialogo = LayoutInflater.from(getActivity()).inflate(R.layout.listview_seleccion_boma, null);
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(getActivity());
        myBuilder.setView(vistaDialogo);
        myBuilder.setTitle("Selecciona Bomba");
        Bomba[] myArrayBombas = new Bomba[5];
        myArrayBombas[0] = new Bomba("Bomba Terrestre",  R.drawable.bomba1);
        myArrayBombas[1] = new Bomba("Bomba Submarina",  R.drawable.bomba2);
        myArrayBombas[2] = new Bomba("Bomba Casera",  R.drawable.bomba3);
        myArrayBombas[3] = new Bomba("Bomba Atomica",  R.drawable.bomba4);
        myArrayBombas[4] = new Bomba("Bomba Normal",  R.drawable.bomba5);
        ListView lst = vistaDialogo.findViewById(R.id.lstv);
        lst.setAdapter(new MiAdapatdorBombas(getActivity().getApplicationContext(), R.layout.mi_bomba_fila, myArrayBombas));
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                foto=position+1;
            }
        });
        myBuilder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity m = new MainActivity();
                m.setFoto(foto);
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setFoto(foto);
                    ((MainActivity) getActivity()).recrearTablero();
                }
                dialog.cancel();
            }
        });
        return myBuilder.create();
    }
    public class MiAdapatdorBombas extends ArrayAdapter<Bomba> {
        Bomba[] misObjectos;

        public MiAdapatdorBombas(@NonNull Context context, int resource, @NonNull Bomba[] objects) {
            super(context, resource, objects);
            misObjectos = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position, convertView, parent);
        }

        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return crearFila(position, convertView, parent);
        }

        public View crearFila(int position, View convertView, ViewGroup parent) {

            LayoutInflater miInflador = getLayoutInflater();
            View mivista = miInflador.inflate(R.layout.mi_bomba_fila, parent, false);
            TextView txtTi = mivista.findViewById(R.id.txtV);
            ImageView img = mivista.findViewById(R.id.iv);

            txtTi.setText(misObjectos[position].Nombre);
            img.setImageResource(misObjectos[position].Imagen);
            return mivista;
        }

    }
}