package com.rocket.vitalis.services;

import com.rocket.vitalis.dto.MonitoringRequest;
import com.rocket.vitalis.model.*;
import com.rocket.vitalis.repositories.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.StreamSupport;

/**
 * Created by Ailin on 20/10/2016.
 */
@Service
@Log4j
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private MonitoringRepository monitoringRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public Collection<Module> findModules(User user){
        return moduleRepository.findByOwner(user);
    }

    public Module addModule(User user, String serial){
        Module module = new Module(serial, user);
        moduleRepository.save(module);
        return module;
    }

    public Monitoring getMonitoring(Long moduleId){
        Monitoring monitoring = monitoringRepository.findByModuleIdAndFinishDateIsNull(moduleId);
        return monitoring;
    }

    public Module getModule(Long moduleId){
        Module module = moduleRepository.findOne(moduleId);
        return module;
    }

    public Module deleteModule(Long moduleId){
        Module module = moduleRepository.findOne(moduleId);
        moduleRepository.delete(module);
        return module;
    }


    public Monitoring initMonitoring(Long moduleId, MonitoringRequest.PatientDto patientId,  Collection<MonitoringRequest.FollowerDto> followers,
                                     Collection<MonitoringRequest.SensorDto> sensors ){

        Collection<Sensor> mySensors = new ArrayList<Sensor>();
        for (MonitoringRequest.SensorDto item : sensors) {
            Sensor sensor = new Sensor(item.getType(), item.getStatus());
            sensorRepository.save(sensor);
            mySensors.add(sensor);
        }

        Module module = moduleRepository.findOne(moduleId);
        User patient = userRepository.findOne(patientId.getId() );
        Monitoring monitoring = new Monitoring(module,patient, mySensors);
        monitoringRepository.save(monitoring);

        for (MonitoringRequest.FollowerDto item : followers) {
            User user = userRepository.findOne(item.getId());
            Follower follower = new Follower(user, monitoring, item.isAdmin());
            followerRepository.save(follower);
        }

        return monitoring;
    }

}
