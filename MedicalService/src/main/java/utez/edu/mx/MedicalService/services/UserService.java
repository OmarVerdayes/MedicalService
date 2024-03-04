package utez.edu.mx.MedicalService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.MedicalService.controllers.users.UsersDTO;
import utez.edu.mx.MedicalService.models.speciality.Speciality;
import utez.edu.mx.MedicalService.models.users.Users;
import utez.edu.mx.MedicalService.models.users.UsersRepository;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UsersRepository repository;

    @Transactional(readOnly=true)
    public CustomResponse<List<Users>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los usuarios");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Users> insert(UsersDTO usersDTO){
        try {
            if(this.repository.existsByEmail(usersDTO.getEmail()))
                return new CustomResponse<>(null, false,200,"Ya existe una usuario registrado con ese correo");

            return new CustomResponse<>(this.repository.saveAndFlush(usersDTO.castToOriginalObject()), false,200,"Usuario registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el usuario");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Users> update(UsersDTO usersDTO){
        try {

            if(!this.repository.existsById(usersDTO.getId())){
                return new CustomResponse<>(null,true,400,"El usuario no existe");
            }else if(this.repository.existsByEmail(usersDTO.getEmail())){
                return new CustomResponse<>(null, false,200,"Ya existe un usuario registrada con ese correo");
            }

            return new CustomResponse<>(this.repository.saveAndFlush(usersDTO.castToOriginalObject()),false,200,"Especialidad actualizada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar la especialidad");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Users> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El usuario no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"El usuario se elimino coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar el usuario");
        }
    }

}



