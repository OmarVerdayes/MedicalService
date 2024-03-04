package utez.edu.mx.MedicalService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.MedicalService.controllers.roles.RolesDTO;
import utez.edu.mx.MedicalService.models.roles.Roles;
import utez.edu.mx.MedicalService.models.roles.RolesRepository;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class RolesService {

    @Autowired
    RolesRepository repository;

    @Transactional(readOnly=true)
    public CustomResponse<List<Roles>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los roles");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Roles> insert(RolesDTO rolesDTO){
        try {
            if(this.repository.existsByName(rolesDTO.getName()))
                return new CustomResponse<>(null, false,200,"Ya existe una roles registrado con ese nombre");

            return new CustomResponse<>(this.repository.saveAndFlush(rolesDTO.castToOriginalObject()), false,200,"roles registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el roles");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Roles> update(RolesDTO rolesDTO){
        try {

            if(!this.repository.existsById(rolesDTO.getId())){
                return new CustomResponse<>(null,true,400,"El rol no existe");
            }else if(this.repository.existsByName(rolesDTO.getName())){
                return new CustomResponse<>(null, false,200,"Ya existe una roles registrado con ese nombre");
            }

            return new CustomResponse<>(this.repository.saveAndFlush(rolesDTO.castToOriginalObject()),false,200,"Roles actualizada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el roles");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Roles> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El rol no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"Rol eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar el rol");
        }
    }

}
