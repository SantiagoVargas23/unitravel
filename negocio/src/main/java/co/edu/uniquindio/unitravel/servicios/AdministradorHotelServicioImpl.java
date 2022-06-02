package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorHotelServicioImpl implements AdministradorHotelServicio{

    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private EmailServicio emailServicio;
    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Override
    public AdministradorHotel validarLogin(String email, String password) throws Exception {
        return administradorHotelRepo.validarLogin(email,password);
    }

    @Override
    public void recuperarPassword(String email) throws Exception {
        Optional<AdministradorHotel> adminHotel= administradorHotelRepo.findByCorreo(email);
        if(adminHotel.isEmpty()){
            throw new Exception("el correo no pertenece a ningun administrador de hotel ");
        }
        String password = adminHotel.get().getPassword();
        emailServicio.enviarEmail("recuperacion de la contraseña ", " hola su contraseña  es"+password,email );


    }

    @Override
    public Hotel crearHotel(Hotel hotel) throws Exception {
        Hotel buscado = obtenerHotel(hotel.getCodigo());

        if(buscado!= null){
            throw new Exception("El hotel del usuario ya esta registrado");
        }
        if(hotel.getNumEstrellas()>5 && hotel.getNumEstrellas()<0)
        {
            throw new Exception("el número de estrellas es inválido");
        }
        else {
            System.out.println("Hotelrepo save");
            return hotelRepo.save(hotel);
        }
    }

    @Override
    public void eliminarHotel(int codigo) throws Exception {
        Hotel buscado = obtenerHotel(codigo);
        if(buscado==null)
        {
            throw new Exception("El hotel no existe ");
        }
        hotelRepo.delete(buscado);
    }

    @Override
    public List<Hotel> listarHoteles(String codigoAdmin) {

        return hotelRepo.findAll();
    }

    @Override
    public Hotel modificarHotel(Hotel hotel) throws Exception {
        Hotel buscado = obtenerHotel(hotel.getCodigo());
        if(buscado==null)
        {
            throw new Exception("El hotel no existe");
        }
        if(hotel.getNumEstrellas()>5 && hotel.getNumEstrellas()<0)
        {
            throw new Exception("el número de estrellas es inválido");
        }
        return hotelRepo.save(hotel);

    }

    @Override
    public Hotel obtenerHotel(Integer codigoHotel) throws Exception {
        return hotelRepo.findById(codigoHotel).orElse(null);
    }

    @Override
    public Ciudad obtenerCiudad(int codCiudad) throws Exception {
        return ciudadRepo.findById(codCiudad).orElse(null);
    }

    @Override
    public AdministradorHotel obtenerAdminHotel(String cedulaAdmin) throws Exception {
        return  administradorHotelRepo.findById(cedulaAdmin).orElse(null);
    }
}
