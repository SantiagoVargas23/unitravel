package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;

import java.util.List;

public interface UnitravelServicio {


    Usuario validarLogin(String correo, String password) throws Exception;
    void recuperarPassword(String email) throws Exception;


    public Ciudad obtenerCiudad(int codCiudad) throws Exception;

    public List<Ciudad> obtenerCiudades();

    AdministradorHotel obtenerAdminHotel(String cedula) throws Exception;

    List<Hotel> buscarHotelesNombre(String nombre)throws Exception;
}
