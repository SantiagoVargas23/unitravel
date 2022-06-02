package co.edu.uniquindio.unitravel.servicios;

import ch.qos.logback.core.encoder.EchoEncoder;
import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;

import java.util.List;

public interface AdministradorHotelServicio {
    AdministradorHotel validarLogin(String email, String password) throws Exception;
    void recuperarPassword(String email) throws Exception;

    Hotel crearHotel(Hotel hotel) throws Exception;
    void eliminarHotel(int codigo) throws Exception;

    List<Hotel> listarHoteles(String codigoAdmin);

    Hotel modificarHotel(Hotel hotel) throws Exception;

    Hotel obtenerHotel(Integer codigoHotel) throws Exception;

    Ciudad obtenerCiudad(int codCiudad) throws  Exception;

    AdministradorHotel obtenerAdminHotel(String cedulaAdmin) throws Exception;
}
