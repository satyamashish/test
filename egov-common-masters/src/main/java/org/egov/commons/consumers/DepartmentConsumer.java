package org.egov.commons.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.egov.commons.model.DepartmentCreationException;
import org.egov.commons.service.DepartmentService;
import org.egov.commons.web.contract.DepartmentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class DepartmentConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(DepartmentConsumer.class);
    @Autowired
    DepartmentService departmentService;
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = {"egov-common-department-create", "egov-common-department-update"})
    /*public void processMessage(Map<String, Object> consumerRecord, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("key:" + topic + ":" + "value:" + consumerRecord);
        try {consumerRecord.values();
            objectMapper.readValue(record.value(), ServiceGroupRequest.class)
           // Department department=objectMapper.convertValue(consumerRecord.get("Department"),Department.class);
         //   RequestInfo requestInfo= objectMapper.convertValue(consumerRecord.get("RequestInfo"),RequestInfo.class);

           // department.setLastModifiedDate(new Date());
           // department.setLastModifiedBy(requestInfo.getUserInfo().getIds());
            if (topic.equals("egov-common-department-create")) {

             //   department.setCreatedDate(new Date());
               // department.setCreatedBy(requestInfo.getUserInfo().getIds());
                departmentService.createDepartment(            objectMapper.readValue(record.value(), ServiceGroupRequest.class)
                );
            }
            else if(topic.equals("egov-common-department-update")){

                departmentService.updateDepartment(department);
            }
        }
        catch (Exception exception)
        {
            log.debug("DepartmentConsumer:processMessage:" + exception);
            throw exception;
        }
    }*/
    @KafkaListener(topics = {"egov-common-department-create", "egov-common-department-update"})
    public void listen(final ConsumerRecord<String, String> record) {
        LOGGER.info("RECORD: " + record.toString());
        LOGGER.info("key:" + record.key() + ":" + "value:" + record.value() + "thread:" + Thread.currentThread());
        try {
            if (record.topic().equals("egov-common-department-create")) {
                departmentService.createDepartment((objectMapper.readValue(record.value(), DepartmentRequest.class)).toDomainModel());

            }
            if (record.topic().equals("egov-common-department-update")) {
                departmentService.updateDepartment((objectMapper.readValue(record.value(), DepartmentRequest.class)).toDomainModel());
            }
            } catch (IOException ioe) {
            log.debug("DepartmentConsumer:processMessage:" + ioe);
            throw new DepartmentCreationException(ioe);
        }
    }
}