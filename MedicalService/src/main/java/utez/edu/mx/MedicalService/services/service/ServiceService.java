package utez.edu.mx.MedicalService.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.service.DTO.ServiceDTO;
import utez.edu.mx.MedicalService.models.service.ServiceM;
import utez.edu.mx.MedicalService.models.service.ServiceRepository;
import utez.edu.mx.MedicalService.utils.ActionsFiles;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository repository;
    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    ActionsFiles actionsFiles=new ActionsFiles();
    @Transactional(readOnly=true)
    public CustomResponse<List<ServiceM>> getAll(){

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los servicios");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<ServiceM> insert(ServiceDTO serviceDTO, BindingResult bindingResult){
        ServiceM serviceM=null;
        try {

            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }else if(serviceDTO.getImageFile()==null){
                return new CustomResponse<>(null, true, 400, "La imagen no puede estar vacia");

            }

            serviceM=serviceDTO.castToOriginalObject();

            return new CustomResponse<>(this.repository.saveAndFlush(serviceM), false,200,"Servicio registrado");

        } catch (Exception e) {
            actionsFiles.deleteFile(serviceM.getImage());
            return new CustomResponse<>(null, true, 500, "Error al registrar el servicios");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<ServiceM> update(ServiceDTO serviceDTO, BindingResult bindingResult){
        ServiceM serviceM=null;
        try {

            if(!this.repository.existsById(serviceDTO.getId())){
                return new CustomResponse<>(null,true,400,"El rol no existe");
            }else if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            String oldImage=this.repository.getOldImage(serviceDTO.getId());

            if (serviceDTO.getImageFile()==null){
                serviceM=serviceDTO.castToOriginalObjectNoImage(oldImage);
            }else {
                actionsFiles.deleteFile(oldImage);
                serviceM=serviceDTO.castToOriginalObject();
            }

            return new CustomResponse<>(this.repository.saveAndFlush(serviceM),false,200,"Servicio actualizado");

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
