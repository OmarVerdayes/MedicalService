package utez.edu.mx.MedicalService.services.service_purchase_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import utez.edu.mx.MedicalService.controllers.service_purchase_history.DTO.Service_purchase_historyDTO;
import utez.edu.mx.MedicalService.models.service_purchase_history.Service_purchase_history;
import utez.edu.mx.MedicalService.models.service_purchase_history.Service_purchase_historyRepository;
import utez.edu.mx.MedicalService.utils.ConvertErrorsValidationToString;
import utez.edu.mx.MedicalService.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;
@Service
public class Service_purchase_historyService {

    @Autowired
    Service_purchase_historyRepository repository;

    ConvertErrorsValidationToString convertErrors=new ConvertErrorsValidationToString();

    @Transactional(readOnly=true)
    public CustomResponse<List<Service_purchase_history>> getAll() {

        try {
            return new CustomResponse<>(this.repository.findAll(),false,200, "OK");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al obtener los el hisotrial");
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse <List<Service_purchase_history>> userHistory(String email){

        try{
            return  new CustomResponse<>(this.repository.getUserHistory(email), false, 200,"Ok");
        }catch (Exception e){
            return  new CustomResponse<>(null, true, 400, "Error al obtener el historial");
        }

    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Service_purchase_history> insert(Service_purchase_historyDTO servicePurchaseHistoryDTO, BindingResult bindingResult){
        try {
             if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);
                return new CustomResponse<>(null, true,400,errorMessage);
            }

            return new CustomResponse<>(this.repository.saveAndFlush(servicePurchaseHistoryDTO.castToOriginalObject()), false,200,"Compra registrada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al registrar la compra del servicio");
        }
    }

    @Transactional(rollbackFor =SQLException.class )
    public CustomResponse<Service_purchase_history> update(Service_purchase_historyDTO servicePurchaseHistoryDTO, BindingResult bindingResult){
        try {

            if (bindingResult.hasErrors()) {
                // Si hay errores de validación, devuelve una respuesta con los mensajes de error
                String errorMessage = convertErrors.convertErrorsValidationToString(bindingResult);

                return new CustomResponse<>(null, true,400,errorMessage);
            }else if(!this.repository.existsById(servicePurchaseHistoryDTO.getId())){
                return new CustomResponse<>(null,true,400,"El registro no existe");
            }

            return new CustomResponse<>(this.repository.saveAndFlush(servicePurchaseHistoryDTO.castToOriginalObject()),false,200,"Historial de compra actualizada");

        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al actualizar el el historial de la compra");
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Service_purchase_history> delete(Long id){
        try {
            if(!this.repository.existsById(id))
                return new CustomResponse<>(null,true,400,"El regisntro no existe");

            this.repository.deleteById(id);
            return new CustomResponse<>(null,false,200,"Registro eliminado coreectamente");
        } catch (Exception e) {
            return new CustomResponse<>(null, true, 500, "Error al eliminar");
        }
    }




}
