package utez.edu.mx.MedicalService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.MedicalService.controllers.roles.RolesDTO;
import utez.edu.mx.MedicalService.controllers.userStatus.UsersStatusDTO;
import utez.edu.mx.MedicalService.models.roles.Roles;
import utez.edu.mx.MedicalService.models.roles.RolesRepository;
import utez.edu.mx.MedicalService.models.userStatus.UserStatus;
import utez.edu.mx.MedicalService.models.userStatus.UserStatusRepository;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserStatusService {

    @Autowired
    UserStatusRepository repository;

    @Transactional(readOnly=true)
    public CustomResponse<List<UserStatus>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los estatus de los usuarios");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<UserStatus> insert(UsersStatusDTO usersStatusDTO){
        try {
            if(this.repository.existsByName(usersStatusDTO.getName()))
                return new CustomResponse<>(null, false,200,"Ya existe una estatus de los usuarios  registrado con ese nombre");

            return new CustomResponse<>(this.repository.saveAndFlush(usersStatusDTO.castToOriginalObject()), false,200,"roles registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el estatus de los usuarios");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<UserStatus> update(UsersStatusDTO usersStatusDTO){
        try {

            if(!this.repository.existsById(usersStatusDTO.getId())){
                return new CustomResponse<>(null,true,400,"El estatus de los usuarios no existe");
            }else if(this.repository.existsByName(usersStatusDTO.getName())){
                return new CustomResponse<>(null, false,200,"Ya existe una estatus de los usuarios registrado con ese nombre");
            }

            return new CustomResponse<>(this.repository.saveAndFlush(usersStatusDTO.castToOriginalObject()),false,200,"Estatus de los usuarios actualizada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el estatus de los usuarios");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<UserStatus> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El estatus de los usuarios no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"El estatus de los usuarios eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar el estatus de los usuarios");
        }
    }

}
