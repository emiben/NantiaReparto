package com.nantia.repartonantia.reparto;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.cliente.ClienteActivity;
import com.nantia.repartonantia.data.AppDatabase;
import com.nantia.repartonantia.data.DataHolder;
import com.nantia.repartonantia.map.MapActivity;
import com.nantia.repartonantia.utils.RetrofitClientInstance;
import com.nantia.repartonantia.venta.Venta;
import com.nantia.repartonantia.venta.VentaService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nantia.repartonantia.utils.Constantes.HTTP_OK;
import static com.nantia.repartonantia.utils.Constantes.KEY_CLIENTE_LISTA;
import static com.nantia.repartonantia.utils.Constantes.KEY_DB_NOMBRE;
import static com.nantia.repartonantia.utils.Constantes.KEY_REPARTO;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepartoFragment extends Fragment implements View.OnClickListener {
    private final String TAG = RepartoFragment.class.getName();
    private ProgressBar progressBar;
    private TextView repartoTv;
    private TextView vendedor1Tv;
    private TextView vendedor2Tv;
    private TextView rutaTv;
    private TextView estadoTv;
    private View rutaLo;
    private View clientesLo;
    private Reparto reparto;
    private Button comenzarRepartoBtn;
    private Button finalizarRepartoBtn;

    public RepartoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reparto, container, false);

        if(getArguments().getSerializable(KEY_REPARTO) != null){
            reparto = (Reparto) getArguments().getSerializable(KEY_REPARTO);
        }

        initializeViewObjects(view);
        loadRepartoData(reparto);
        setListeners();
        return view;
    }

    private void initializeViewObjects(View view){
        progressBar = view.findViewById(R.id.reparto_progressBar);
        repartoTv = view.findViewById(R.id.reparto_desc_tv);
        vendedor1Tv = view.findViewById(R.id.reparto_vend_1_tv);
        vendedor2Tv = view.findViewById(R.id.reparto_vend_2_tv);
        rutaTv = view.findViewById(R.id.reparto_ruta_tv);
        rutaLo = view.findViewById(R.id.reparto_ruta_lo);
        clientesLo = view.findViewById(R.id.reparto_clientes_lo);
        estadoTv = view.findViewById(R.id.reparto_estado_tv);
        comenzarRepartoBtn = view.findViewById(R.id.comenzar_reparto_btn);
        finalizarRepartoBtn = view.findViewById(R.id.finalizar_reparto_btn);
    }

    private void setListeners(){
        rutaLo.setOnClickListener(this);
        clientesLo.setOnClickListener(this);
        comenzarRepartoBtn.setOnClickListener(this);
        finalizarRepartoBtn.setOnClickListener(this);
    }

    private void loadRepartoData(Reparto reparto){
        if(reparto != null){
            if(reparto.getDescripcion() != null && !reparto.getDescripcion().isEmpty())
                repartoTv.setText(reparto.getDescripcion());
            if(reparto.getVendedor1().getNombreCompleto() != null &&
                    !reparto.getVendedor1().getNombreCompleto().isEmpty())
                vendedor1Tv.setText(reparto.getVendedor1().getNombreCompleto());
            if(reparto.getVendedor2().getNombreCompleto() != null &&
                    !reparto.getVendedor1().getNombreCompleto().isEmpty())
                vendedor2Tv.setText(reparto.getVendedor2().getNombreCompleto());
            if(reparto.getRuta().getNombre() != null && !reparto.getRuta().getNombre().isEmpty())
                rutaTv.setText(reparto.getRuta().getNombre());
            if(reparto.getEstado() != null && !reparto.getEstado().isEmpty())
                estadoTv.setText(reparto.getEstado());
            if(reparto.getEstado().equals(RepartoEstado.INICIADO.name())){
                comenzarRepartoBtn.setVisibility(View.GONE);
                finalizarRepartoBtn.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comenzar_reparto_btn:
                comenzarReparto();
                break;
            case R.id.finalizar_reparto_btn:
                finalizarReparto();
                break;
            case R.id.reparto_ruta_lo:
                navigateToRepartoMap();
                break;
            case R.id.reparto_clientes_lo:
                navigateToRepartoClientes();
                break;
            default:
                break;
        }
    }

    private void navigateToRepartoClientes() {
        Intent i = new Intent(getActivity(), ClienteActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(KEY_CLIENTE_LISTA, reparto.getRuta().getClientes());
        i.putExtra(KEY_CLIENTE_LISTA, b);
        startActivity(i);
    }

    private void navigateToRepartoMap() {
        Intent i = new Intent(getActivity(), MapActivity.class);
        i.putExtra(KEY_REPARTO, true);
        startActivity(i);
    }

    private void comenzarReparto(){
        reparto.setEstado(RepartoEstado.INICIADO.name());
        guardarReparto(reparto);
        actualizarEstadoReparto(reparto.getId(), RepartoEstado.INICIADO.name());
        comenzarRepartoBtn.setVisibility(View.GONE);
        finalizarRepartoBtn.setVisibility(View.VISIBLE);
        estadoTv.setText(reparto.getEstado());
    }

    private void finalizarReparto(){
        progressBar.setVisibility(View.VISIBLE);
        DataHolder.getReparto().setEstado(RepartoEstado.FINALIZADO.name());
        reparto.setEstado(RepartoEstado.FINALIZADO.name());
        estadoTv.setText(reparto.getEstado());
        enviarData();
    }

    private void enviarData(){
        List<Venta> ventas = DataHolder.getVentasSinEnviar();
        if(ventas.size() > 0){
            VentaService ventaService = RetrofitClientInstance.getRetrofitInstance().create(VentaService.class);
            Call<ResponseBody> call = ventaService.enviarVentas(ventas);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.code() == HTTP_OK){
                        borrarData();
                        actualizarEstadoReparto(reparto.getId(), RepartoEstado.FINALIZADO.name());
                        getActivity().finish();
                    }else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(),
                                "Error al enviar las ventas" + response.message(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),
                            "Error al enviar las ventas" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }else{
            borrarData();
            actualizarEstadoReparto(reparto.getId(), RepartoEstado.FINALIZADO.name());
            getActivity().finish();
        }
    }

    private void actualizarEstadoReparto(long id, String estado){
        RepartoService repartoService = RetrofitClientInstance.getRetrofitInstance().create(RepartoService.class);
        Call<ResponseBody> call = repartoService.updateEstadoReparto(id, estado);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void borrarData(){
        DataHolder.setRuta(null);
        DataHolder.setReparto(null);
        DataHolder.getVentas().clear();
        final AppDatabase db = Room.databaseBuilder(getActivity(),
                AppDatabase.class, KEY_DB_NOMBRE).build();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.vehiculoDao().nukeTable();
                db.stockDao().nukeTable();
                db.usuarioDao().nukeTable();
                db.rutaDao().nukeTable();
                db.repartoDao().nukeTable();
                db.ventaDao().nukeTable();
            }
        });
        progressBar.setVisibility(View.GONE);
    }

    private void guardarReparto(final Reparto reparto){
        DataHolder.setReparto(reparto);
        final AppDatabase db = Room.databaseBuilder(getActivity(),
                AppDatabase.class, KEY_DB_NOMBRE).build();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
//                db.repartoDao().deleteById(reparto.getId());
                db.repartoDao().nukeTable();
                db.repartoDao().insertAll(reparto);
            }
        });
    }
}
