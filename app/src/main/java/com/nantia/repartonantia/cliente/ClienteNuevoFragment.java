package com.nantia.repartonantia.cliente;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.EnvaseSpinnerAdapter;
import com.nantia.repartonantia.map.ClienteMapaFragment;
import com.nantia.repartonantia.producto.Envase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ClienteNuevoFragment extends Fragment implements ClienteNuevoView, View.OnClickListener {
    private ClienteNuevoPresenter presenter;
    private Cliente cliente;
    private TextView nombre1;
    private TextView nombre2;
    private TextView nroDeDoc;
    private Spinner tipoDeDoc;
    private TextView fecDeNac;
    private TextView telefono1;
    private TextView telefono2;
    private TextView email;
    private TextView direccion;
    private TextView ciudad;
    private TextView departamento;
    private TextView codigoPostal;
    private TextView comentarios;
    private ArrayList<Spinner> envAPrestamoSPs = new ArrayList<>();
    private ArrayList<TextView> envAPrestamoCantETs = new ArrayList<>();
    private LinearLayout envAPrestamoLO;
    private ImageView usuarioImage;
    private ImageView casaImage;
    private FloatingActionButton fab;
    private LatLng posicionMapa;
    private ImageView agregarEnvPrestamo;

    private ArrayList<EnvaseEnPrestamo> envasesEnPrestamo;
    private ArrayList<Envase> envases;


    public ClienteNuevoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cliente_nuevo, container, false);
        presenter = new ClienteNuevoPresenter(this);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initializeViewObjects(view);
        setOnClickListeners();
        loadSpinner();

        //TODO: Traer envases posta
        envases = new ArrayList<>();
        envases.add(new Envase(0, "Nuevo envase a prestamo..."));
        for(int i=1; i < 50; i++){
            envases.add(new Envase(i, "Envase " + i));
        }
        addEditTexts();

        if(getArguments() != null){
            if(getArguments().getDouble("lat") != 0.0){
                posicionMapa = new LatLng(getArguments().getDouble("lat"), getArguments().getDouble("lng"));
            }
            if(getArguments().getSerializable("cliente") != null){
                cliente = (Cliente) getArguments().getSerializable("cliente");
                loadClienteData(cliente);
            }
        }

        return view;
    }


    private void initializeViewObjects(View view){
        nombre1 = view.findViewById(R.id.nombre_1_et);
        nombre2 = view.findViewById(R.id.nombre_2_et);
        nroDeDoc = view.findViewById(R.id.numero_doc_et);
        tipoDeDoc = view.findViewById(R.id.tipo_doc_spin);
        fecDeNac = view.findViewById(R.id.fecha_de_nac_et);
        telefono1 = view.findViewById(R.id.tel_1_et);
        telefono2 = view.findViewById(R.id.tel_2_et);
        email = view.findViewById(R.id.email_et);
        direccion = view.findViewById(R.id.direccion_et);
        ciudad = view.findViewById(R.id.ciudad_et);
        departamento = view.findViewById(R.id.departamento_et);
        codigoPostal = view.findViewById(R.id.cp_et);
        usuarioImage = view.findViewById(R.id.user_image);
        casaImage = view.findViewById(R.id.home_image);
        fab = view.findViewById(R.id.cliente_nuevo_fab);
        envAPrestamoLO = view.findViewById(R.id.env_prestamo_lo);
        agregarEnvPrestamo = view.findViewById(R.id.agreagr_env_prestamo);
        comentarios = view.findViewById(R.id.comentario_et);
    }

    private void setOnClickListeners(){
        fab.setOnClickListener(this);
        agregarEnvPrestamo.setOnClickListener(this);
        tipoDeDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUiRutoCI(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadSpinner(){
        List<String> list = new ArrayList<>();
        list.add(TipoDocumento.CI.name());
        list.add(TipoDocumento.RUT.name());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoDeDoc.setAdapter(adapter);
    }

    private void updateUiRutoCI(int pos){
        if(pos == 0){
            usuarioImage.setImageResource(R.drawable.cliente_icono_gris_2);
            casaImage.setImageResource(R.drawable.home_2);
            nombre1.setHint(R.string.cliente_nombre);
            nombre2.setHint(R.string.cliente_apellido);
            nroDeDoc.setHint(R.string.cliente_nro_CI);
        }else{
            usuarioImage.setImageResource(R.drawable.factory_2);
            casaImage.setImageResource(R.drawable.factory_2);
            nombre1.setHint(R.string.cliente_empresa_nombre_fantasia);
            nombre2.setHint(R.string.cliente_empresa_nombre_legal);
            nroDeDoc.setHint(R.string.cliente_nro_RUT);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cliente_nuevo_fab:
                navigateToClienteNuevoMapaFragment();
                break;
            case R.id.agreagr_env_prestamo:
                addEditTexts();
                break;
            default:
                break;
        }
    }

    private void navigateToClienteNuevoMapaFragment() {
        ClienteMapaFragment clienteMapaFragment = new ClienteMapaFragment();
        if(posicionMapa != null){
            Bundle b = new Bundle();
            b.putParcelable("latLng", posicionMapa);
            clienteMapaFragment.setArguments(b);
        }
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.cliente_lista_layout, clienteMapaFragment)
                .addToBackStack(null)
                .commit();
    }

    private void removeEditTextsDialog(final int viewId){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.cliente_eliminar_env_prestamo));
        builder.setMessage(getString(R.string.cliente_eliminar_env_prestamo_desc));
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removeEditTexts(viewId);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void addEditTexts(){
        EnvaseSpinnerAdapter envaseSpinnerAdapter = new EnvaseSpinnerAdapter(getActivity(), envases);

        Spinner envaseSp = new Spinner(getActivity());
        envaseSp.setAdapter(envaseSpinnerAdapter);
        envaseSp.setId(Integer.parseInt("1"+envAPrestamoSPs.size() + 1));
        envaseSp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeEditTextsDialog(v.getId());
                return false;
            }
        });

        EditText envaseCantET = new EditText(getActivity());
        envaseCantET.setId(Integer.parseInt("2"+envAPrestamoCantETs.size() + 1));
        envaseCantET.setInputType(InputType.TYPE_CLASS_NUMBER);
        envaseCantET.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        envaseCantET.setHint(R.string.cliente_cantidad);

        envAPrestamoSPs.add(envaseSp);
        envAPrestamoCantETs.add(envaseCantET);
        envAPrestamoLO.addView(envaseSp, 0);
        envAPrestamoLO.addView(envaseCantET, 1);
    }

    private void removeEditTexts(int viewId){
        String cantId = Integer.toString(viewId);
        cantId = cantId.substring(1);
        boolean encontre = false;
        int i = 0;
        while (!encontre && i < envAPrestamoSPs.size()){
            if(envAPrestamoSPs.get(i).getId() == viewId){
                envAPrestamoSPs.remove(i);
                encontre = true;
            }
            i++;
        }
        encontre = false;
        i = 0;
        while (!encontre && i < envAPrestamoCantETs.size()){
            if(envAPrestamoCantETs.get(i).getId() == Integer.parseInt("2"+cantId)){
                envAPrestamoCantETs.remove(i);
                encontre = true;
            }
            i++;
        }
        envAPrestamoLO.removeView(getActivity().findViewById(viewId));
        envAPrestamoLO.removeView(getActivity().findViewById(Integer.parseInt("2"+cantId)));
    }

    private void loadClienteData(Cliente cliente) {
        nombre1.setText(cliente.getNombre1());
        nombre2.setText(cliente.getNombre2());
        nroDeDoc.setText(cliente.getNroDocumento());
        if(cliente.getTipoDocumento() == TipoDocumento.RUT){
            tipoDeDoc.setSelection(1);
            usuarioImage.setImageResource(R.drawable.factory_2);
            casaImage.setImageResource(R.drawable.factory_2);
        }
        fecDeNac.setText(cliente.getFechaNacimiento().toString());
        telefono1.setText(String.valueOf(cliente.getCelular()));
        telefono2.setText(String.valueOf(cliente.getDireccion().getTelefono()));
        email.setText(cliente.getMail());
        direccion.setText(cliente.getDireccion().getDireccion());

        posicionMapa = new LatLng(cliente.getDireccion().getCordLat(), cliente.getDireccion().getCordLon());
        //TODO: Actualizar esto con el nuevo modelo
//        ciudad;
//        departamento;
//        codigoPostal;
    }
}
