package com.nantia.repartonantia.cliente;

import android.content.Context;

import com.nantia.repartonantia.R;
import com.nantia.repartonantia.adapters.ClienteInfoPOJO;
import com.nantia.repartonantia.data.DataHolder;

import java.util.ArrayList;

/**
 * Created by Emi on 28/5/2018.
 */

public class ClientePresenter {
    private final String TAG = ClientePresenter.class.getName();
    private ClienteView clienteView;
    private Context context;

    public ClientePresenter(ClienteView clienteView, Context context) {
        this.clienteView = clienteView;
        this.context = context;
    }

    public void cargarInfo(){
        Cliente cliente = clienteView.getCliente();
        ArrayList<ClienteInfoPOJO> clienteInfo = new ArrayList<>();

        String clienteNombre = "";
        if(cliente.getNombre1() != null) clienteNombre = cliente.getNombre1();
        if(cliente.getNombre2() != null) clienteNombre = clienteNombre + " " + cliente.getNombre2();

        ClienteInfoPOJO clienteInfoPOJOSaldo = new ClienteInfoPOJO(context, R.drawable.peso,
                String.valueOf(cliente.getSaldo()), R.string.cliente_saldo);
        clienteInfo.add(clienteInfoPOJOSaldo);

        if(cliente.getTipoDocumento().equals(TipoDocumento.CI.name())){
            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.cliente_icono_gris,
                    cliente.getNroDocumento(), R.string.cliente_CI);
            clienteInfo.add(clienteInfoPOJO);
        }else if (cliente.getTipoDocumento().equals(TipoDocumento.RUT.name())){
            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.factory,
                    cliente.getNroDocumento(), R.string.cliente_RUT);
            clienteInfo.add(clienteInfoPOJO);
        }

        if(cliente.getDireccion().getTelefono() != null && !cliente.getDireccion().getTelefono().isEmpty()){
            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.phone,
                    String.valueOf(cliente.getDireccion().getTelefono()), R.string.cliente_telefono);
            clienteInfo.add(clienteInfoPOJO);
        }

        if(cliente.getCelular()
                != null && !cliente.getCelular().isEmpty()){
            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.mobile_phone,
                    String.valueOf(cliente.getCelular()), R.string.cliente_celular);
            clienteInfo.add(clienteInfoPOJO);
        }

        if(cliente.getDias() != null && cliente.getDias().size() > 0){
            String dias = "";
            for (int i = 0; i < cliente.getDias().size(); i++){
                if(i == 0){
                    dias = cliente.getDias().get(i).name();
                }else {
                    dias = dias + " - " + cliente.getDias().get(i).name();
                }
            }

            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.calendar,
                    dias, R.string.cliente_dias_visita);
            clienteInfo.add(clienteInfoPOJO);
        }

        if(cliente.getDireccion() != null){
            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.home,
                    cliente.getDireccion().getDireccion(), R.string.cliente_direccion);
            clienteInfo.add(clienteInfoPOJO);
        }

        if(cliente.getMail() != null && cliente.getMail().compareTo("") != 0){
            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.mail,
                    cliente.getMail(), R.string.cliente_mail);
            clienteInfo.add(clienteInfoPOJO);
        }

        if(cliente.getIdLista() != 0){
            String nombreLista = "";
            boolean encontre = false;
            for (int i = 0; i < DataHolder.getListasDePrecios().size() && !encontre; i++){
                if(DataHolder.getListasDePrecios().get(i).getId() == cliente.getIdLista()){
                    nombreLista = DataHolder.getListasDePrecios().get(i).getNombreLista();
                    encontre = true;
                }
            }
            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.lista_icono_gris,
                    nombreLista, R.string.cliente_lista_precios);
            clienteInfo.add(clienteInfoPOJO);
        }

        if(cliente.getEnvasesEnPrestamo() != null){
            for(int i = 0; i < cliente.getEnvasesEnPrestamo().size(); i++){
                ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.producto_icono_gris,
                        cliente.getEnvasesEnPrestamo().get(i).getEnvase().getDescripcion()+ ": " +
                                cliente.getEnvasesEnPrestamo().get(i).getCantidad(), R.string.cliente_envases_prestamo);
                clienteInfo.add(clienteInfoPOJO);
            }
        }

        if(cliente.getObservaciones() != null && cliente.getObservaciones().compareTo("") != 0){
            ClienteInfoPOJO clienteInfoPOJO = new ClienteInfoPOJO(context, R.drawable.comments,
                    cliente.getObservaciones(), R.string.cliente_observaciones);
            clienteInfo.add(clienteInfoPOJO);
        }

        clienteView.setClienteInfo(clienteInfo, clienteNombre);
    }

}
