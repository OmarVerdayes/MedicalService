package utez.edu.mx.MedicalService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.MedicalService.controllers.roles.RolesDTO;
import utez.edu.mx.MedicalService.controllers.service.ServiceDTO;
import utez.edu.mx.MedicalService.models.roles.Roles;
import utez.edu.mx.MedicalService.models.service.ServiceM;
import utez.edu.mx.MedicalService.models.service.ServiceRepository;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository repository;

    @Transactional(readOnly=true)
    public CustomResponse<List<ServiceM>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los servicios");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<ServiceM> insert(ServiceDTO serviceDTO){
        try {

            return new CustomResponse<>(this.repository.saveAndFlush(serviceDTO.castToOriginalObject()), false,200,"Servicio registrado");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar el servicios");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<ServiceM> update(ServiceDTO serviceDTO){
        try {

            if(!this.repository.existsById(serviceDTO.getId())){
                return new CustomResponse<>(null,true,400,"El rol no existe");
            }

            return new CustomResponse<>(this.repository.saveAndFlush(serviceDTO.castToOriginalObject()),false,200,"Servicio actualizada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el servicio");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<ServiceM> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El servicio no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"Servicio eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar el servicio");
        }
    }

}
